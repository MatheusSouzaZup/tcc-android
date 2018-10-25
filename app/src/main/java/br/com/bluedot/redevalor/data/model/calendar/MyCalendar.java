package br.com.bluedot.redevalor.data.model.calendar;

import java.util.List;

/**
 * Created by gadal on 12/12/17.
 */

public class MyCalendar {
    private List<Integer> usedDays;
    private Integer firstDay;
    private Integer monthSize;

    public List<Integer> getUsedDays() {
        return usedDays;
    }

    public void setUsedDays(List<Integer> usedDays) {
        this.usedDays = usedDays;
    }

    public Integer getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(Integer firstDay) {
        this.firstDay = firstDay;
    }

    public Integer getMonthSize() {
        return monthSize;
    }

    public void setMonthSize(Integer monthSize) {
        this.monthSize = monthSize;
    }
}
