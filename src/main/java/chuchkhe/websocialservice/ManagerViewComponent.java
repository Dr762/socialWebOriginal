/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chuchkhe.websocialservice;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TabSheet;

/**
 *
 * @author alex
 */
public class ManagerViewComponent extends CustomComponent{
    
    public ManagerViewComponent (){
    
    TabSheet ts1 = new TabSheet();
    ts1.addTab(new ManagerComponent(),"Назначение заказов");
    ts1.addTab(new ManagerStatComponent(),"Cтатистика");
    setCompositionRoot(ts1);
    
    }
    
}
