package com.n26.model;

import java.math.BigDecimal;

public class StatisticsAggregate {

    public StatisticsAggregate(){

    }

    public StatisticsAggregate(Statistics statistics){

        this.count = statistics.getCount();
        this.aggregateMax = statistics.getMax();
        this.aggregateMin = statistics.getMin();
        this.aggregateSum = statistics.getSum();
    }


    private BigDecimal aggregateSum = new BigDecimal(0);

    public BigDecimal getAggregateSum() {
        return aggregateSum;
    }

    public void setAggregateSum(BigDecimal aggregateSum) {

        this.aggregateSum = aggregateSum.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getAggregateAvg() {
        return aggregateAvg;
    }

    public void setAggregateAvg(BigDecimal aggregateAvg) {

        this.aggregateAvg = aggregateAvg.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getAggregateMax() {
        return aggregateMax;
    }

    public void setAggregateMax(BigDecimal aggregateMax) {

        this.aggregateMax = aggregateMax.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getAggregateMin() {
        return aggregateMin;
    }

    public void setAggregateMin(BigDecimal aggregateMin) {

        this.aggregateMin = aggregateMin.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {

        this.count = count;
    }

    private BigDecimal aggregateAvg = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
    private BigDecimal aggregateMax = new BigDecimal(Double.MIN_VALUE).setScale(2, BigDecimal.ROUND_HALF_UP);
    private BigDecimal aggregateMin = new BigDecimal(Double.MAX_VALUE).setScale(2, BigDecimal.ROUND_HALF_UP);
    private Long count = 0l;




    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        StatisticsAggregate summary = (StatisticsAggregate) obj;

        if ( aggregateSum.compareTo(summary.aggregateSum) != 0) return false;
        if (count != summary.count) return false;
        if (aggregateMax.compareTo( summary.aggregateMax) != 0) return false;
        if (aggregateMin.compareTo( summary.aggregateMin) != 0) return false;
        return aggregateAvg.compareTo(summary.aggregateAvg)  == 0;

    }
}
