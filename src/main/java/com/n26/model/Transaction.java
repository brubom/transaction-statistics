package com.n26.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author brunomoreira
 */
@JsonSerialize
public class Transaction implements Serializable{


    private BigDecimal amount;

    private Long timestamp;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Transaction(BigDecimal amount, Long timestamp){
        this.amount = amount;
        this.timestamp = timestamp;
    }
}
