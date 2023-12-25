package me.minkh.factory.v3.manager;

import me.minkh.factory.v3.ChartFactory;
import me.minkh.factory.v3.chart.Chart;
import me.minkh.factory.v3.chart.ChartType;

public class SalesManager {

    public void rend() {
        // Chart chart = ChartFactory.createChart();
        Chart chart = ChartFactory.createChart(ChartType.BAR);
        chart.render();
    }

}
