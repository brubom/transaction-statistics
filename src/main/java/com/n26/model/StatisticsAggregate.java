package com.n26.model;

import java.math.BigDecimal;

public class StatisticsAggregate {

    public StatisticsAggregate(){

    }

    public StatisticsAggregate(Statistics statistics){

        this.count = statistics.getCount();
        this.max = statistics.getMax();
        this.min = statistics.getMin();
        this.sum = statistics.getSum();
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

    private BigDecimal sum = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
    private BigDecimal avg = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
    private BigDecimal max = null;
    private BigDecimal min = null;
    private Long count = 0l;




    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        StatisticsAggregate summary = (StatisticsAggregate) obj;

        if ( sum.compareTo(summary.sum) != 0) return false;
        if (count != summary.count) return false;
        if (max.compareTo( summary.max) != 0) return false;
        if (min.compareTo( summary.min) != 0) return false;
        return avg.compareTo(summary.avg)  == 0;

    }
}
