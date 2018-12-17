package com.n26.repository;

import com.n26.data.SimpleStatisticsDb;
import com.n26.data.SimpleTransactionDb;
import com.n26.model.Statistics;
import com.n26.model.Transaction;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static java.time.temporal.ChronoUnit.SECONDS;

import java.util.stream.*;

public class StatisticsRepository {

    private final SimpleStatisticsDb statisticsDb = SimpleStatisticsDb.getInstance();
    private final SimpleTransactionDb simpleTransactionDb = SimpleTransactionDb.getInstance();

    public Statistics updateStatistics(Statistics statistics) {

        if(statisticsDb.getMax() == null ||
                statisticsDb.getMax().compareTo(statistics.getMax()) < 0)
            statisticsDb.setMax(statistics.getMax());

        if(statisticsDb.getAvg() == null ||
                statisticsDb.getAvg().compareTo(statistics.getAvg()) < 0)
            statisticsDb.setAvg(statistics.getAvg());

        if(statisticsDb.getMin() == null ||
                statisticsDb.getMin().compareTo(statistics.getMin()) < 0)
            statisticsDb.setMin(statistics.getMin());

        if(statisticsDb.getSum() == null ||
                statisticsDb.getSum().compareTo(statistics.getSum()) < 0)
            statisticsDb.setSum(statistics.getSum());

        

    }

    public Statistics fromLast(int seconds) {


        LocalDateTime currentTime =
                LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        currentTime.minusSeconds(seconds);

        Map<Date, Transaction> map = simpleTransactionDb.getAllTransactions().entrySet()
                .stream()
                .filter(transaction -> SECONDS.between(currentTime, LocalDateTime.now()) <= seconds)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

        Statistics statistics = new Statistics();

        map.values().stream().mapToInt(BigDecimal::parse).sum();



    }
}
