package com.n26.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * @author brunomoreira
 */
@JsonSerialize
public class Transaction implements Serializable{


    private BigDecimal amount;


    private Date timestamp;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Transaction(BigDecimal amount, Date timestamp){
        this.amount = amount;
        this.timestamp = timestamp;
    }
}
