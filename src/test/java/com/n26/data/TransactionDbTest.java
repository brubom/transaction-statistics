package com.n26.data;

import com.n26.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionDbTest {

    @Inject
    private TransactionDb db;

    @Test
    public void shouldHave_TransactionDbInstance(){


        assertNotNull(db);
    }

    @Test
    public void shouldAddItem(){

        db.add(new Transaction(new BigDecimal(100), System.currentTimeMillis()));
    }

    @Test
    public void shouldClearTransactions(){

        db.clear();
    }
}
