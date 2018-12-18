package com.n26.repository;

import com.n26.model.Statistics;
import com.n26.model.StatisticsAggregate;
import com.n26.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsRepositoryTest {

    @Inject
    private StatisticsRepository statisticsRepository;

    @Inject
    private TransactionRepository transactionRepository;

    @Test
    public void shouldCreateStatistics(){

        Transaction transaction =  new Transaction(BigDecimal.valueOf(100), System.currentTimeMillis());

        statisticsRepository.updateStatistics(transaction);

        assertNotNull(transaction);
    }

    @Test
    public void shouldReturnStatisticsFromLastSixtySeconds(){

        addSomeTransactions();
        StatisticsAggregate statistics = statisticsRepository.getStatistics();

        assertNotNull(statistics);

        assertEquals(new BigDecimal(533.33), statistics.getAggregateAvg());
        assertEquals(new BigDecimal(1000), statistics.getAggregateMax());
        assertEquals(new BigDecimal(100), statistics.getAggregateMin());
        assertEquals(new BigDecimal(1600), statistics.getAggregateSum());
        assertEquals(Long.valueOf(3), statistics.getCount());


    }

    private void addSomeTransactions(){

        transactionRepository.create(new Transaction(new BigDecimal(100),System.currentTimeMillis() ));
        transactionRepository.create(new Transaction(new BigDecimal(1000),System.currentTimeMillis()));
        transactionRepository.create(new Transaction(new BigDecimal(500),System.currentTimeMillis()));

        transactionRepository.create(new Transaction(new BigDecimal(500),
                LocalDateTime.now().minusSeconds(70).atZone(ZoneId.systemDefault()).toEpochSecond()));
    }


}
