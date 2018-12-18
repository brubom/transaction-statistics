package com.n26.data;

import com.n26.model.StatisticsAggregate;
import com.n26.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsDbTest {


    @Inject
    private StatisticsDb statisticsDb;


    @Test
    public void shouldHave_StatisticsDbInstance() {

        assertNotNull(statisticsDb);
    }



    @Test
    public void shouldNotShowExpiredTransaction(){

        StatisticsAggregate aggregate = statisticsDb.getStatistics();
        statisticsDb.updateStatistics(new Transaction(BigDecimal.valueOf( 16.98), System.currentTimeMillis() - 60000));
        assertEquals(aggregate, statisticsDb.getStatistics() );

    }

    @Test
    public void whenValidTransaction_UpdateStatistics(){

        statisticsDb.updateStatistics(new Transaction(BigDecimal.valueOf(100), System.currentTimeMillis()));
        statisticsDb.updateStatistics(new Transaction(BigDecimal.valueOf(15.87877), System.currentTimeMillis() - 1000));
        statisticsDb.updateStatistics(new Transaction(BigDecimal.valueOf(8), System.currentTimeMillis() - 3000));
        statisticsDb.updateStatistics(new Transaction(BigDecimal.valueOf(188), System.currentTimeMillis() - 2000));
        statisticsDb.updateStatistics(new Transaction(BigDecimal.valueOf(1), System.currentTimeMillis() - 20000));

        StatisticsAggregate statistics = statisticsDb.getStatistics();
        assertTrue(statistics.getMax().compareTo( BigDecimal.valueOf(188.00)) == 0);
        assertTrue(statistics.getAvg().compareTo( BigDecimal.valueOf(62.58)) == 0);
        assertTrue(statistics.getMin().compareTo(BigDecimal.valueOf(1)) == 0);
        assertTrue(statistics.getSum().compareTo(BigDecimal.valueOf(312.88)) == 0);
        assertEquals(statistics.getCount(), Long.valueOf(5));

    }
}
