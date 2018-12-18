package com.n26.repository;

import com.n26.model.StatisticsAggregate;
import com.n26.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;


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

        assertEquals(new BigDecimal(425).setScale(2, BigDecimal.ROUND_HALF_UP), statistics.getAvg());
        assertEquals(new BigDecimal(1000).setScale(2, BigDecimal.ROUND_HALF_UP), statistics.getMax());
        assertEquals(new BigDecimal(100).setScale(2, BigDecimal.ROUND_HALF_UP), statistics.getMin());
        assertEquals(new BigDecimal(1700).setScale(2, BigDecimal.ROUND_HALF_UP), statistics.getSum());
        assertEquals(Long.valueOf(4), statistics.getCount());


    }

    private void addSomeTransactions(){

        transactionRepository.create(new Transaction(new BigDecimal(100),System.currentTimeMillis() ));
        transactionRepository.create(new Transaction(new BigDecimal(1000),System.currentTimeMillis()));
        transactionRepository.create(new Transaction(new BigDecimal(500),System.currentTimeMillis()));

        transactionRepository.create(new Transaction(new BigDecimal(500),
                LocalDateTime.now().minusSeconds(70).atZone(ZoneId.systemDefault()).toEpochSecond()));
    }


}
