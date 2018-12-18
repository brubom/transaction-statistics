package com.n26.data;

import com.n26.model.Statistics;

public class StatisticsDb {

    private static final StatisticsDb INSTANCE = new StatisticsDb();
    public static StatisticsDb getInstance() {
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
