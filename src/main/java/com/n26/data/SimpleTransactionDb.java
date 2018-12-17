package com.n26.data;

import com.n26.model.Transaction;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * @author brunomoreira
 * NOT a real db, of course, just a quick singleton implementation. Although it says
 * on exercise that this should be a prod ready code, using a nosql database or at least a
 * caching mechanism is a must.
 */
public class SimpleTransactionDb {

    private static final SimpleTransactionDb INSTANCE = new SimpleTransactionDb();
    public static SimpleTransactionDb getInstance() {
        return INSTANCE;
    }

    private LinkedList<Transaction> transactions = new LinkedList<>();

    private static final int CACHE_TTL = 60;

    private SimpleTransactionDb(){

        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(CACHE_TTL * 1000);
                } catch (InterruptedException ex) {
                }
                cleanup();
            }
        });

        t.setDaemon(true);
        t.start();
    }

    public void add(Transaction transaction){
        transactions.add(transaction);
    }

    public void clear(){

        transactions.clear();
    }

    public Transaction get(int index){
        return transactions.get(index);
    }

   public Long count(){
        return Long.valueOf(transactions.size());
   }
   
   public LinkedList<Transaction>  getAllTransactions(){
        return this.transactions;
   }


   private void cleanup(){


       synchronized (transactions) {

           while (true) {

               Transaction transaction = this.transactions.getFirst();
               if (transaction == null) {
                   break;
               }

               LocalDateTime currentTime = LocalDateTime.now();

               LocalDateTime transactionTime =
                       LocalDateTime.ofInstant(transaction.getTimestamp().toInstant(), ZoneId.systemDefault());

               if (SECONDS.between(currentTime, transactionTime) > CACHE_TTL) {
                   transactions.removeFirst();
                   updateStatistics();
               } else {
                   break;
               }

               Thread.yield();

           }
       }


   }

    private void updateStatistics() {


    }

}
