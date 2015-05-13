/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chuchkhe.websocialservice;

import chuchkhe.domain.DAO;
import chuchkhe.entity.Customer;
import chuchkhe.entity.CustomerOrder;
import chuchkhe.entity.Item;
import chuchkhe.entity.Status;
import chuchkhe.entity.StatusHistory;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.util.DefaultQueryModifierDelegate;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.apache.commons.jexl2.parser.JexlNode;

/**
 *
 * @author alex
 */
public class OperatorCreateComponent extends CustomComponent implements Window.CloseListener {

    private CustomerOrder order = new CustomerOrder();
    private BeanItemContainer<Item> it = new BeanItemContainer<Item>(Item.class);
    private UpdateForm uform;
    JPAContainer<CustomerOrder> co;
    private Table items;
    private static final Logger LOG = Logger.getLogger(OperatorCreateComponent.class.getName());

    public OperatorCreateComponent(String action, Customer customer) {

        HorizontalLayout hl = new HorizontalLayout();
        order.setCustomer(customer);

        order.setCustomer(customer);
        it.addAll(order.getItems());

        uform = new UpdateForm(it, order);
        hl.addComponent(uform);
        VerticalLayout vl1 = new VerticalLayout();
        HorizontalLayout hl1 = new HorizontalLayout();


        Button delete = new Button("Удалить из заказа");
        Button submit = new Button("Сформировать заказ");



        items = new Table("Содержимое заказа", it);


        items.setImmediate(true);



        items.setColumnHeader("id", "№");
        items.setColumnHeader("item_name", "Название товара");
        items.setColumnHeader("quantity", "Количество");
        items.setColumnHeader("uom", "Единицы измерения");
        items.setColumnHeader("price", "Цена");
        items.setColumnHeader("manufacturer", "Производитель");
        items.setColumnHeader("shop_name", "Магазин");
        items.setSelectable(true);
        items.setVisibleColumns(new String[]{"id", "item_name", "quantity", "uom", "price", "manufacturer", "shop_name"});


        hl.addComponent(vl1);

        vl1.addComponent(items);
        vl1.addComponent(hl1);
        hl1.addComponent(delete);
        hl1.addComponent(submit);
        setCompositionRoot(hl);

        final Table orders = new Table("Заказы");
        co = JPAContainerFactory.make(CustomerOrder.class, DAO.PERSISTENCE_UNIT);
        co.getEntityProvider().setQueryModifierDelegate(new DefaultQueryModifierDelegate() {
            @Override
            public void filtersWillBeAdded(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> query, List<Predicate> predicates) {
                Root<?> fromOrder = query.getRoots().iterator().next();
                Subquery<Long> subQuery = query.subquery(Long.class);
            
            //    List<Long> stList = Arrays.asList(Status.ST_Draft, Status.ST_ReadyToAssign);

                Root<StatusHistory> sth = subQuery.from(StatusHistory.class);
                subQuery = subQuery.where(criteriaBuilder.equal(sth.get("object_name"), criteriaBuilder.literal("customerorder")), criteriaBuilder.or(
                        criteriaBuilder.equal(sth.get("status").get("id"), criteriaBuilder.literal(Status.ST_Draft)), criteriaBuilder.equal(sth.get("status").get("id"), criteriaBuilder.literal(Status.ST_ReadyToAssign)))
                        , criteriaBuilder.isNull(sth.get("endDate")));
                subQuery = subQuery.select(sth.get("objectID").as(Long.class));

                predicates.add(criteriaBuilder.in(fromOrder.get("id")).value(subQuery));

                super.filtersWillBeAdded(criteriaBuilder, query, predicates);
            }

        });
        Container.Filter custFilter = new Compare.Equal("customer.id", customer.getId());
        co.addContainerFilter(custFilter);
        co.setApplyFiltersImmediately(true);

        orders.setContainerDataSource(co);
        orders.setColumnHeader("id", "№ заказа");
        orders.setSelectable(true);
        orders.setVisibleColumns(new String[]{"id"});
        orders.setImmediate(true);
        hl.addComponent(orders);

        items.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                BeanItem<Item> itemValue = it.getItem(items.getValue());
                uform.setItemDataSource(itemValue);
                uform.setIsAddMode(itemValue == null);

            }
        });
        delete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Property itms = co.getContainerProperty(orders.getValue(), "items"); 
               LOG.severe("itemds: " +items.getValue());
               DAO.removeItem((Item) items.getValue());
               it.removeItem(items.getValue());
               co.refreshItem(orders.getValue());
                
                uform.setIsAddMode(true);
            }
        });

        submit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                items.commit();
                order.setItems(it.getItemIds());
                DAO.saveOrder(order);
                co.refresh();

            }
        });


        orders.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                it.removeAllItems();

                Property itms = co.getContainerProperty(orders.getValue(), "items");
                if (itms == null) {
                    Customer cust = order.getCustomer();
                    order = new CustomerOrder();
                    order.setCustomer(cust);
                } else {
                    it.addAll((List<Item>) itms.getValue());
                    CustomerOrder curOrder = co.getItem(orders.getValue()).getEntity();
                    if (curOrder != null) {
                        order = curOrder;
                    }
                }
                uform.setItemDataSource(new BeanItem<Item>(new Item().withOrder(order)));
                uform.setIsAddMode(true);
            }
        });

    }

    @Override
    public void windowClose(CloseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
