package com.n26.repository;

import com.n26.data.SimpleStatisticsDb;
import com.n26.model.Statistics;

public class StatisticsRepository {

    private final SimpleStatisticsDb statisticsDb = SimpleStatisticsDb.getInstance();

    public void updateStatistics(Statistics statistics) {

        statisticsDb.setStatistics(statistics);

    }

    public Statistics fromLast(int seconds) {

        return statisticsDb.getStatistics();

    }
}
