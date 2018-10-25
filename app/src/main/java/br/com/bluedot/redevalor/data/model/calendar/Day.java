package br.com.bluedot.redevalor.data.model.calendar;

/**
 * Created by gadal on 12/12/17.
 */

public class Day {

    private String day;
    private boolean usedDay;
    private boolean enable = true;
    private String consuptionDayKey;

    public Day(String day, boolean usedDay, boolean enable, String consuptionDayKey) {
        this.day = day;
        this.usedDay = usedDay;
        this.enable = enable;
        this.consuptionDayKey = consuptionDayKey;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean isUsedDay() {
        return usedDay;
    }

    public void setUsedDay(boolean usedDay) {
        this.usedDay = usedDay;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getConsuptionDayKey() {
        return consuptionDayKey;
    }

    public void setConsuptionDayKey(String consuptionDayKey) {
        this.consuptionDayKey = consuptionDayKey;
    }
}
