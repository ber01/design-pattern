package me.minkh.factory.v3;

import me.minkh.factory.v3.chart.*;

public class ChartFactory {

    public static Chart createChart() {
        // return new LineChart();
        return new PieChart();
    }

    public static Chart createChart(ChartType type) {
        switch (type) {
            case BAR -> {
                return new BarChart();
            }
            case LINE -> {
                return new LineChart();
            }
            case PIE -> {
                return new PieChart();
            }
            case ROW_BAR -> {
                return new RowBarChart();
            }
            default -> throw new IllegalArgumentException(type + "는 올바르지 않은 요청입니다.");
        }
    }

}
