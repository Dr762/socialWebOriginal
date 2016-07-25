/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.abondar.uniservity.websocial.websocialservice;

import org.abondar.uniservity.websocial.domain.DAO;
import org.abondar.uniservity.websocial.entity.Customer;
import org.abondar.uniservity.websocial.entity.CustomerOrder;
import org.abondar.uniservity.websocial.entity.Employee;
import org.abondar.uniservity.websocial.entity.Item;
import org.abondar.uniservity.websocial.entity.OrderEmployee;
import org.abondar.uniservity.websocial.entity.Status;
import org.abondar.uniservity.websocial.entity.StatusHistory;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.addon.jpacontainer.util.DefaultQueryModifierDelegate;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

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
public class ManagerComponent extends CustomComponent {

    private JPAContainer<Employee> emp;
    private JPAContainer<CustomerOrder> order;
    private JPAContainer<CustomerOrder> assigned;
    private BeanItemContainer<Item> it = new BeanItemContainer<Item>(Item.class);
    private CustomerOrder orderob = new CustomerOrder();

    public ManagerComponent() {
        VerticalLayout vl = new VerticalLayout();
        HorizontalLayout hl = new HorizontalLayout();



        order = JPAContainerFactory.make(CustomerOrder.class, DAO.PERSISTENCE_UNIT);
        order.getEntityProvider().setQueryModifierDelegate(new DefaultQueryModifierDelegate() {
            @Override
            public void filtersWillBeAdded(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> query, List<Predicate> predicates) {
                Root<?> fromOrder = query.getRoots().iterator().next();
                Subquery<Long> subQuery = query.subquery(Long.class);

                //    List<Long> stList = Arrays.asList(Status.ST_Draft, Status.ST_ReadyToAssign);

                Root<StatusHistory> sth = subQuery.from(StatusHistory.class);
                subQuery = subQuery.where(
                        criteriaBuilder.equal(sth.get("object_name"), criteriaBuilder.literal("customerorder")), criteriaBuilder.equal(sth.get("status").get("id"), criteriaBuilder.literal(Status.ST_ReadyToAssign)), criteriaBuilder.isNull(sth.get("endDate")));
                subQuery = subQuery.select(sth.get("objectID").as(Long.class));

                predicates.add(criteriaBuilder.in(fromOrder.get("id")).value(subQuery));

                super.filtersWillBeAdded(criteriaBuilder, query, predicates);
            }
        });

        //     order.setApplyFiltersImmediately(true);

        assigned = JPAContainerFactory.make(CustomerOrder.class, DAO.PERSISTENCE_UNIT);
        assigned.getEntityProvider().setQueryModifierDelegate(new DefaultQueryModifierDelegate() {
            @Override
            public void filtersWillBeAdded(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> query, List<Predicate> predicates) {
                Root<?> fromOrder = query.getRoots().iterator().next();
                Subquery<Long> subQuery = query.subquery(Long.class);

                //    List<Long> stList = Arrays.asList(Status.ST_Draft, Status.ST_ReadyToAssign);

                Root<StatusHistory> sth = subQuery.from(StatusHistory.class);
                subQuery = subQuery.where(
                        criteriaBuilder.equal(sth.get("object_name"), criteriaBuilder.literal("customerorder")), criteriaBuilder.equal(sth.get("status").get("id"), criteriaBuilder.literal(Status.ST_Assigned)), criteriaBuilder.isNull(sth.get("endDate")));
                subQuery = subQuery.select(sth.get("objectID").as(Long.class));

                predicates.add(criteriaBuilder.in(fromOrder.get("id")).value(subQuery));

                super.filtersWillBeAdded(criteriaBuilder, query, predicates);
            }
        });



        final Table orders = new Table("Нензначенные заказы");
        orders.setContainerDataSource(order);
        orders.setColumnHeader("id", "№ заказа");
        orders.setColumnHeader("customer", "Заказчик");
        orders.setColumnHeader("employee", "Работник");
        //orders.setColumnHeader("customerId", "№");

        orders.addGeneratedColumn("customer", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(Table source, Object itemId, Object columnId) {
                String a = ((CustomerOrder) ((JPAContainerItem) source.getItem(itemId)).getEntity()).getCustomer().getName();

                return a;
            }
        });
        orders.addGeneratedColumn("employee", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(Table source, Object itemId, Object columnId) {
                String a = ((CustomerOrder) ((JPAContainerItem) source.getItem(itemId)).getEntity()).getCustomer().getEmployee().getPerson().getFIO();

                return a;
            }
        });
        orders.setSelectable(true);

        orders.setVisibleColumns(new String[]{"id", "customer", "employee"});
        orders.setImmediate(true);

        final Table assignedOrders = new Table("Назначенные заказы");
        assignedOrders.setContainerDataSource(assigned);
        assignedOrders.setColumnHeader("id", "№ заказа");
        assignedOrders.setSelectable(true);
        assignedOrders.setVisibleColumns(new String[]{"id"});
        assignedOrders.setImmediate(true);

        final Table assignedOrderItems = new Table("Содержимое заказа");
        assignedOrderItems.setContainerDataSource(it);
        assignedOrderItems.setColumnHeader("id", "№");
        assignedOrderItems.setColumnHeader("item_name", "Название товара");
        assignedOrderItems.setColumnHeader("quantity", "Количество");
        assignedOrderItems.setColumnHeader("uom", "Единицы измерения");
        assignedOrderItems.setColumnHeader("price", "Цена");
        assignedOrderItems.setColumnHeader("manufacturer", "Производитель");
        assignedOrderItems.setColumnHeader("shop_name", "Магазин");
        assignedOrderItems.setVisibleColumns(new String[]{"id", "item_name", "quantity", "uom"});
        assignedOrderItems.setSelectable(true);

        assignedOrderItems.setImmediate(true);

        Button assign = new Button("Назначить заказ на исполнение");


        vl.addComponent(hl);
        hl.addComponent(orders);
        hl.addComponent(assignedOrderItems);

        vl.addComponent(assign);

        hl.addComponent(assignedOrders);
        setCompositionRoot(vl);

        assignedOrderItems.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                BeanItem<Item> itemValue = it.getItem(assignedOrderItems.getValue());

            }
        });



        orders.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                it.removeAllItems();

                Property itms = order.getContainerProperty(orders.getValue(), "items");
                if (itms == null) {
                    Customer cust = orderob.getCustomer();
                    orderob = new CustomerOrder();
                    orderob.setCustomer(cust);
                } else {
                    it.addAll((List<Item>) itms.getValue());
                    CustomerOrder curOrder = order.getItem(orders.getValue()).getEntity();
                    if (curOrder != null) {
                        orderob = curOrder;
                    }
                }

            }
        });



        assign.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Object curOrderItem = orders.getValue();
                if (curOrderItem != null) {
                    OrderEmployee oe = new OrderEmployee();
                    oe.setEmp(order.getItem(curOrderItem).getEntity().getCustomer().getEmployee());
                    oe.setOrder(order.getItem(curOrderItem).getEntity());
                    DAO.saveOrderEmloyee(oe);
                    assigned.refresh();
                    it.removeAllItems();
                    order.refresh();
                }
            }
        });


    }
}
