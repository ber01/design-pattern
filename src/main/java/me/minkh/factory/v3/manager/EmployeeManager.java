package me.minkh.factory.v3.manager;

import me.minkh.factory.v3.ChartFactory;
import me.minkh.factory.v3.chart.Chart;
import me.minkh.factory.v3.chart.ChartType;

public class EmployeeManager {

    public void rend() {
        Chart chart = ChartFactory.createChart(ChartType.PIE);
        chart.render();
    }

}
