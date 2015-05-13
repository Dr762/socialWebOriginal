/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chuchkhe.websocialservice;

import chuchkhe.domain.DAO;
import chuchkhe.entity.CustomerOrder;
import chuchkhe.entity.Item;
import chuchkhe.entity.Status;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class UpdateForm extends CustomComponent {

    @PropertyId("item_name")
    TextField itemName = new TextField("Название товара");
    @PropertyId("quantity")
    TextField quantity = new TextField("Количество");
    @PropertyId("uom")
    TextField uom = new TextField("Единицы измерения");
    @PropertyId("price")
    TextField price = new TextField("Цена");
    @PropertyId("manufacturer")
    TextField manufacturer = new TextField("Производитель");
    @PropertyId("shop_name")
    TextField shopName = new TextField("Магазин");
    final FieldGroup binder = new FieldGroup();
    Boolean isAddMode = true;
    Button add = new Button("Добавить в заказ");
    private BeanItemContainer<Item> it;
    
    private static final Logger LOG = Logger.getLogger(UpdateForm.class.getName());
    
    

    public UpdateForm(BeanItemContainer<Item> itv, CustomerOrder order) {

        VerticalLayout vl = new VerticalLayout();
        vl.addComponent(itemName);
        vl.addComponent(quantity);
        vl.addComponent(uom);
        vl.addComponent(price);
        vl.addComponent(manufacturer);
        vl.addComponent(shopName);
        vl.addComponent(add);
        this.it = itv;
        binder.setItemDataSource(
                new BeanItem<Item>(new Item().withOrder(order)));

        setCompositionRoot(vl);
        binder.bindMemberFields(this);

        add.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    binder.commit();
                    CustomerOrder order = ((BeanItem<Item>) binder.getItemDataSource()).getBean().getOrder();
                    if (order.getId() != null && !DAO.changeOrderStat(order, Status.ST_Draft)) {
                          LOG.severe("Item is not added");
                          return;
                    }
                    if (isAddMode) {
                        BeanItem<Item> itm = (BeanItem<Item>) binder.getItemDataSource();
                        it.addBean(itm.getBean());
                    }
                    setIsAddMode(true);
                    BeanItem<Item> bi = new BeanItem<Item>(new Item().withOrder(order));
                    binder.setItemDataSource(bi);
                } catch (FieldGroup.CommitException ex) {
                    Logger.getLogger(OperatorCreateComponent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void setItemDataSource(BeanItem<Item> ds) {
        binder.setItemDataSource(ds);
    }

    public Boolean getIsAddMode() {
        return isAddMode;
    }

    public void setIsAddMode(Boolean isAddMode) {
        this.isAddMode = isAddMode;
        if (isAddMode == false) {
            add.setCaption("Редактировать");
        } else {
            add.setCaption("Добавить в заказ");
        }
    }
}
