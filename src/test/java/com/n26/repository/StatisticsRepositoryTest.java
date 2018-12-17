package com.n26.repository;

import com.n26.model.Statistics;
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

        Statistics statistics = statisticsRepository.create(new Statistics(
                new BigDecimal(1000.00),
                new BigDecimal(100.53),
                new BigDecimal(200000.49),
                new BigDecimal(50.23),
                10L

        ));

        assertNotNull(statistics);
    }

    @Test
    public void shouldReturnStatisticsFromLastSixtySeconds(){

        addSomeTransactions();
        Statistics statistics = statisticsRepository.fromLast(60);

        assertNotNull(statistics);

        assertEquals(new BigDecimal(533.33), statistics.getAvg());
        assertEquals(new BigDecimal(1000), statistics.getMax());
        assertEquals(new BigDecimal(100), statistics.getMin());
        assertEquals(new BigDecimal(1600), statistics.getSum());
        assertEquals(Long.valueOf(3), statistics.getCount());


    }

    private void addSomeTransactions(){

        transactionRepository.create(new Transaction(new BigDecimal(100),new Date()));
        transactionRepository.create(new Transaction(new BigDecimal(1000),new Date()));
        transactionRepository.create(new Transaction(new BigDecimal(500),new Date()));

        transactionRepository.create(new Transaction(new BigDecimal(500),
                Date.from(
                        LocalDateTime.of(2015,
                                Month.FEBRUARY,
                                20, 06, 30)
                                .atZone(ZoneId.systemDefault())
                                .toInstant() )));
    }


}
