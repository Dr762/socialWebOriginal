/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chuchkhe.websocialservice;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author alex
 */
public class ManagerStatComponent extends CustomComponent {

    public ManagerStatComponent() {
        VerticalLayout vl = new VerticalLayout();
        Chart chart = new Chart(ChartType.COLUMN);
        chart.setWidth("400px");
        chart.setHeight("300px");

// Modify the default configuration a bit
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Статистика");
        conf.setSubTitle("Количество исполенных за месяц заказов");
        conf.getLegend().setEnabled(false); // Disable legend

// The data
        ListSeries series = new ListSeries("Количество");
        series.setData(100, 300, 200,
                900, 400, 600,
                700, 800,500,100);
        conf.addSeries(series);

// Set the category labels on the axis correspondingly
        XAxis xaxis = new XAxis();
        xaxis.setCategories("Январь", "Февраль", "Март",
                "Апрель", "Май", "Июнь",
                "Июль", "Август","Сентябрь","Октябрь","Ноябрь","Декабрь");
        xaxis.setTitle("Месяцы");
        conf.addxAxis(xaxis);

// Set the Y axis title
        YAxis yaxis = new YAxis();
        yaxis.setTitle("Количество");
        yaxis.getLabels().setFormatter(
                "function() {return Math.floor(this.value + \'Mm\') }");
        yaxis.getLabels().setStep(2);
        conf.addyAxis(yaxis);

        vl.addComponent(chart);
setCompositionRoot(vl);
    }
}
