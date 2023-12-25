package me.minkh.factory.v3;

import me.minkh.factory.v3.chart.Chart;
import me.minkh.factory.v3.chart.ChartType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ChartFactoryTest {

    @Test
    void test() {
        List<Chart> charts = new ArrayList<>();
        charts.add(ChartFactory.createChart(ChartType.LINE));
        charts.add(ChartFactory.createChart(ChartType.PIE));
        charts.add(ChartFactory.createChart(ChartType.BAR));
        charts.add(ChartFactory.createChart(ChartType.ROW_BAR));

        for (Chart chart : charts) {
            chart.render();
        }
    }

}