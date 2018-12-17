package com.n26.repository;

import com.n26.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionRepositoryTest {

    @Inject
    private TransactionRepository transactionRepository;

    @Test
    public void shouldGetNoTransaction(){

        // Count all
        assertEquals(Long.valueOf(0), transactionRepository.countAll());


    }

    @Test
    public void shouldCreateOneTransaction(){

        Transaction transaction = new Transaction(new BigDecimal(100),new Date());
        transaction = transactionRepository.create(transaction);
        // Checks the created transaction
        assertNotNull(transaction);
        assertNotNull(transaction.getTimestamp());

    }



}
