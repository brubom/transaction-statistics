package com.n26.data;

import com.n26.model.Statistics;
import com.n26.model.Transaction;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * @author brunomoreira
 * NOT a real db, of course, just a quick in memory collection/cache implementation. Although it says
 * on exercise that this should be a prod ready code, using a nosql database or at least a
 * caching mechanism is a must.
 */
public class TransactionDb {


    @Inject
    private StatisticsDb statisticsDb;

    public void clear() {

        statisticsDb.clear();
    }


    public void add(Transaction transaction) {

        statisticsDb.updateStatistics(transaction);

    }
}
