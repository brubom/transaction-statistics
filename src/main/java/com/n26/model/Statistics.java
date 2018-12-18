package com.n26.model;

import java.math.BigDecimal;

/**
 * @author brunomoreira
 */
public class Statistics {


    private BigDecimal sum;
    private BigDecimal avg;
    private BigDecimal max;
    private BigDecimal min;
    private Long count;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    private Long timestamp;

    public Statistics(){

    }

    public Statistics(BigDecimal sum, BigDecimal avg, BigDecimal max, BigDecimal min, Long count) {

        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.count = count;

    }


    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
