package com.n26.repository;

import com.n26.data.StatisticsDb;
import com.n26.model.StatisticsAggregate;
import com.n26.model.Transaction;

import javax.inject.Inject;

public class StatisticsRepository {

    @Inject
    private StatisticsDb statisticsDb;

    public void updateStatistics(Transaction transaction) {

        statisticsDb.updateStatistics(transaction);

    }


    public StatisticsAggregate getStatistics() {

        return statisticsDb.getStatistics();
    }
}
