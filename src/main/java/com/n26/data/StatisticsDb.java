package com.n26.data;

import com.n26.model.Statistics;
import com.n26.model.StatisticsAggregate;
import com.n26.model.Transaction;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StatisticsDb {

    private static final int MAX_SECONDS_QUERY = 60;

    private static final Map<Integer, Statistics> statistics = new ConcurrentHashMap<>(MAX_SECONDS_QUERY);


    public StatisticsAggregate getStatistics() {
        StatisticsAggregate aggregate = statistics.values().stream()
                .filter(s -> (System.currentTimeMillis() - s.getTimestamp()) / 1000 < MAX_SECONDS_QUERY)
                .map(StatisticsAggregate::new)
                .reduce(new StatisticsAggregate(), (first, second) -> {
                    first.setSum(first.getSum().add(second.getSum()));
                    first.setCount( first.getCount() + second.getCount());

                    if (first.getMax() == null || first.getMax().compareTo(second.getMax()) < 0)
                        first.setMax(second.getMax());

                    if (first.getMin() == null || first.getMin().compareTo(second.getMin()) > 0)
                        first.setMin(second.getMin());

                    return first;
                });



        if (aggregate.getCount() > 0 && aggregate.getMax() != null
                && aggregate.getMax().compareTo(BigDecimal.valueOf(0)) != 0) {

            aggregate.setAvg(
                    aggregate.getSum().divide(BigDecimal.valueOf(aggregate.getCount()),
                            BigDecimal.ROUND_HALF_UP));
        }
        else
        {
            aggregate.setAvg(BigDecimal.valueOf(0));
        }


        return aggregate;
    }





    public void clear() {

        statistics.clear();
    }

    public void updateStatistics(Transaction transaction) {

        if ((System.currentTimeMillis() - transaction.getTimestamp()) / 1000 < MAX_SECONDS_QUERY) {
            int second = LocalDateTime.ofInstant(Instant.ofEpochMilli(transaction.getTimestamp()), ZoneId.systemDefault()).getSecond();
            statistics.compute(second, (k, v) -> {
                if (v == null || (System.currentTimeMillis() - v.getTimestamp()) / 1000 >= MAX_SECONDS_QUERY) {
                    v = new Statistics();
                    v.setTimestamp(transaction.getTimestamp());
                    v.setSum(transaction.getAmount());
                    v.setMax(transaction.getAmount());
                    v.setMin(transaction.getAmount());
                    v.setCount(1l);
                    return v;
                }

                v.setCount(v.getCount() + 1);
                v.setSum(v.getSum().add(transaction.getAmount()));
                if (transaction.getAmount().compareTo(v.getMax()) > 0) v.setMax(transaction.getAmount());
                if (transaction.getAmount().compareTo( v.getMin()) < 0) v.setMin(transaction.getAmount());
                return v;
            });
        }

    }
}
