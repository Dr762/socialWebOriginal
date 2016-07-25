/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.abondar.uniservity.websocial.websocialservice;

import org.abondar.uniservity.websocial.domain.DAO;
import org.abondar.uniservity.websocial.entity.Customer;
import org.abondar.uniservity.websocial.entity.Person;
import com.google.gwt.event.dom.client.ClickEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class OperatorComponent extends CustomComponent {

    private TextField findSocialID = new TextField("Номер соц.карты");
    private TextField findName = new TextField("ФИО");
    private TextField findDate = new TextField("Дата рождения");
    private Panel p = new Panel("Данные о заказчике");
    private Label result = new Label("Green Borsch");
    
    private Customer foundCustomer = null ;
    public OperatorComponent() {
        VerticalLayout vlayout = new VerticalLayout();

        FormLayout fl = new FormLayout();
        fl.setSizeUndefined();

        Button find = new Button("Найти");

        result.setSizeUndefined();
        Panel orders = new Panel("Заказы");
        HorizontalLayout hlayout = new HorizontalLayout();
       
        Button active = new Button("Активные заказы");
        Button history = new Button("История заказов");

        fl.addComponent(findSocialID);
        fl.addComponent(findName);
        fl.addComponent(findDate);
        fl.addComponent(find);
//    vlayout.setSizeUndefined();
        vlayout.addComponent(fl);
        p.setContent(result);
        vlayout.addComponent(p);
        vlayout.addComponent(orders);
        orders.setContent(hlayout);
        hlayout.setSizeUndefined();
        orders.setSizeUndefined();

        hlayout.addComponent(active);
        hlayout.addComponent(history);
//    hlayout1.addComponent(vlayout);
        for (int i = 0; i < vlayout.getComponentCount(); i++) {
            vlayout.setComponentAlignment(vlayout.getComponent(i), Alignment.MIDDLE_CENTER);
        }
        vlayout.setSizeFull();
        setCompositionRoot(vlayout);
      
        find.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Customer customer = new Customer();
                Person person = new Person();
                customer.setPerson(person);
                person.setSocialID(findSocialID.getValue());
                person.setFIO(findName.getValue());
                try {
                    foundCustomer = DAO.findCustomer(customer);
                    result.setValue(foundCustomer == null ? "Не нашли мы ничего" : foundCustomer.toString());
                }catch (IllegalArgumentException ex) {
                    Logger.getLogger(OperatorComponent.class.getName()).log(Level.SEVERE, null, ex);
                    result.setValue("Не нашли мы ничего");
                    new Notification("Не нашли", "Не нашли мы ничего", Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
                    
                    
                    
                }
            }
        });
          active.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                  if (foundCustomer!=null){
                Window mainWindow = new Window("Активные заказы");
                mainWindow.setContent(new OperatorCreateComponent("active", foundCustomer));
                UI.getCurrent().addWindow(mainWindow);
                  }
            }
        });
     
          
      history.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
               
                  if (foundCustomer!=null){
                Window mainWindow = new Window("История заказов");
                mainWindow.setContent(new OperatorViewComponent("history", foundCustomer));
                UI.getCurrent().addWindow(mainWindow);
                  }
            }
        });
      
      
     
         
       
    }
}
