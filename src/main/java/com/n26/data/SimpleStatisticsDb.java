package com.n26.data;

import com.n26.model.Statistics;

import java.math.BigDecimal;

public class SimpleStatisticsDb {

    private static final SimpleStatisticsDb INSTANCE = new SimpleStatisticsDb();
    public static SimpleStatisticsDb getInstance() {
        return INSTANCE;
    }

    private Statistics statistics;

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }
}
