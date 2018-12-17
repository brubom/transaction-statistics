package com.n26.data;

import java.math.BigDecimal;

public class SimpleStatisticsDb {

    private static final SimpleStatisticsDb INSTANCE = new SimpleStatisticsDb();
    public static SimpleStatisticsDb getInstance() {
        return INSTANCE;
    }

    private BigDecimal max = null;
    private BigDecimal avg = null;
    private BigDecimal min = null;
    private BigDecimal sum = null;


    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        if(this.max == null)
            this.max = max;

       if (this.max.compareTo(max) < 0)
            this.max = max;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        if(this.avg == null)
            this.avg = avg;

        if (this.avg.compareTo(avg) < 0)
            this.avg = avg;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        if(this.min == null)
            this.min = min;

        if (this.min.compareTo(min) < 0)
            this.min = min;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        if(this.sum == null)
            this.sum = sum;

        if (this.sum.compareTo(sum) < 0)
            this.sum = sum;
    }
}
