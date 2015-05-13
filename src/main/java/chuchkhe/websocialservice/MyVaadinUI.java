package chuchkhe.websocialservice;

import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{
     @Override
    protected void init(VaadinRequest request) {
        final HorizontalLayout hor_layout = new HorizontalLayout();
        final VerticalLayout ver_layout = new VerticalLayout(); 
        Button manager_button = new Button("Окно Менеджера");
        Button operator_button = new Button("Окно оператора");
        Label  greet = new Label("Borsch");
        
        manager_button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
              setContent(new ManagerViewComponent());
            }
        });
         operator_button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
               setContent(new OperatorComponent());
            }
        });
        ver_layout.addComponent(greet);
       ver_layout.addComponent(operator_button);
        ver_layout.addComponent(manager_button);
     
        
     
        ver_layout.setSizeFull();
//        hor_layout.setSizeFull();
        hor_layout.addComponent(ver_layout);
//        hor_layout.setComponentAlignment(ver_layout, Alignment.MIDDLE_CENTER);
//        hor_layout.setHeight(300, Unit.PIXELS);
        setContent(hor_layout);
       
        
        
    }

}
