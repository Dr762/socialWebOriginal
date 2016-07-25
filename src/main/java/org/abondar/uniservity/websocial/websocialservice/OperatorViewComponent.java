/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.abondar.uniservity.websocial.websocialservice;

import org.abondar.uniservity.websocial.domain.DAO;
import org.abondar.uniservity.websocial.entity.Customer;
import com.google.gwt.user.client.WindowCloseListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import org.abondar.uniservity.websocial.entity.CustomerOrder;
import org.abondar.uniservity.websocial.entity.Item;
import org.abondar.uniservity.websocial.entity.Status;
import org.abondar.uniservity.websocial.entity.StatusHistory;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.util.DefaultQueryModifierDelegate;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Property;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.ui.UI;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

/**
 *
 * @author alex
 */
public class OperatorViewComponent extends CustomComponent implements Window.CloseListener {

    JPAContainer<CustomerOrder> co;
    BeanItemContainer<Item> itemco;

    public OperatorViewComponent(String action, Customer customer) {
        HorizontalLayout hl = new HorizontalLayout();
        final Table orders = new Table("Заказы");
        co = JPAContainerFactory.make(CustomerOrder.class, DAO.PERSISTENCE_UNIT);
           co.getEntityProvider().setQueryModifierDelegate(new DefaultQueryModifierDelegate() {
            @Override
            public void filtersWillBeAdded(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> query, List<Predicate> predicates) {
                Root<?> fromOrder = query.getRoots().iterator().next();
                Subquery<Long> subQuery = query.subquery(Long.class);
            
            //    List<Long> stList = Arrays.asList(Status.ST_Draft, Status.ST_ReadyToAssign);

                Root<StatusHistory> sth = subQuery.from(StatusHistory.class);
                subQuery = subQuery.where(criteriaBuilder.equal(sth.get("object_name"), criteriaBuilder.literal("customerorder")),
                        criteriaBuilder.equal(sth.get("status").get("id"), criteriaBuilder.literal(Status.ST_Closed))
                        , criteriaBuilder.isNull(sth.get("endDate")));
                subQuery = subQuery.select(sth.get("objectID").as(Long.class));

                predicates.add(criteriaBuilder.in(fromOrder.get("id")).value(subQuery));

                super.filtersWillBeAdded(criteriaBuilder, query, predicates);
            }

        });
        Filter custFilter = new Compare.Equal("customer.id", customer.getId());
       // Filter histFilter = new C
        co.addContainerFilter(custFilter);
        co.setApplyFiltersImmediately(true);

        itemco = new BeanItemContainer<Item>(Item.class);
        
        orders.setContainerDataSource(co);
        orders.setColumnHeader("id", "№ заказа");
        orders.setSelectable(true);
        orders.setVisibleColumns(new String[]{"id"});
        orders.setImmediate(true);

        final Table items = new Table("Содержимое");


        items.setContainerDataSource(itemco);
        items.setColumnHeader("item_name", "Название продукта");
        items.setColumnHeader("quantity", "Количество");
        items.setColumnHeader("uom", "Мера");
        items.setSelectable(true);
        items.setImmediate(true);

        items.setVisibleColumns(new String[]{"id","item_name" ,"quantity", "uom"});
        items.setSelectable(true);
        hl.addComponent(orders);
        hl.addComponents(items);
        setCompositionRoot(hl);


        orders.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                Property itms = co.getContainerProperty(orders.getValue(), "items");

                itemco.removeAllItems();
                if (itms!=null){
                itemco.addAll((List<Item>) itms.getValue());
                }
            }
        });
 items.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
         UpdateForm uform = new UpdateForm(itemco, co.getItem(orders.getValue()).getEntity() );
         uform.setIsAddMode(Boolean.FALSE);
         uform.setItemDataSource(itemco.getItem(items.getValue()));
         uform.setEnabled(false);
          Window mainWindow = new Window("Подробная информация");
                mainWindow.setContent(uform);
               mainWindow.setModal(true);
                UI.getCurrent().addWindow(mainWindow);
                
            }
        });
        

    }

    @Override
    public void windowClose(CloseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
