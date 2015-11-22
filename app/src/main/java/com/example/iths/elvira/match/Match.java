package com.example.iths.elvira.match;

import com.example.iths.elvira.Helper;
import com.example.iths.elvira.event.Event;
import com.example.iths.elvira.player.Player;

/**
 * Created by Bartek Svaberg on 15-11-18.
 */
public abstract class Match {
    private int periodLength, periodLengthExtraTime, amountOfPeriods, amountOfExtraPeriods, suspensionLength;
    private String nameOfPeriod = "period", nameOfExtraPeriod = "extra period";
    private boolean elviraHasOfficialTime, clockGoesup, clockIsResetEachPeriod;

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    private int period;

    public boolean isClockGoesup() {
        return clockGoesup;
    }

    public void setClockGoesup(boolean clockGoesup) {
        this.clockGoesup = clockGoesup;
    }

    public boolean isClockIsResetEachPeriod() {
        return clockIsResetEachPeriod;
    }

    public void setClockIsResetEachPeriod(boolean clockIsResetEachPeriod) {
        this.clockIsResetEachPeriod = clockIsResetEachPeriod;
    }

    public int getPeriodLength() {
        return periodLength;
    }

    public void setPeriodLength(int periodLength) {
        this.periodLength = periodLength;
    }

    public int getPeriodLengthExtraTime() {
        return periodLengthExtraTime;
    }

    public void setPeriodLengthExtraTime(int periodLengthExtraTime) {
        this.periodLengthExtraTime = periodLengthExtraTime;
    }

    public int getAmountOfPeriods() {
        return amountOfPeriods;
    }

    public void setAmountOfPeriods(int amountOfPeriods) {
        this.amountOfPeriods = amountOfPeriods;
    }

    public int getAmountOfExtraPeriods() {
        return amountOfExtraPeriods;
    }

    public void setAmountOfExtraPeriods(int amountOfExtraPeriods) {
        this.amountOfExtraPeriods = amountOfExtraPeriods;
    }

    public String getNameOfPeriod() {
        return nameOfPeriod;
    }

    public void setNameOfPeriod(String nameOfPeriod) {
        this.nameOfPeriod = nameOfPeriod;
    }

    public String getNameOfExtraPeriod() {
        return nameOfExtraPeriod;
    }

    public void setNameOfExtraPeriod(String nameOfExtraPeriod) {
        this.nameOfExtraPeriod = nameOfExtraPeriod;
    }

    public int getSuspensionLength() {
        return suspensionLength;
    }

    public void setSuspensionLength(int suspensionLength) {
        this.suspensionLength = suspensionLength;
    }

    public boolean isElviraHasOfficialTime() {
        return elviraHasOfficialTime;
    }

    public void setElviraHasOfficialTime(boolean elviraHasOfficialTime) {
        this.elviraHasOfficialTime = elviraHasOfficialTime;
    }

    public void addEvent(int minutes, int seconds, Player player, Event event) {
        long calculatedTimeStamp = Helper.inputToMillis(minutes, seconds);

        if (elviraHasOfficialTime) {
            calculatedTimeStamp = Helper.inputToMillis(minutes, seconds);
        }
        else if (!clockGoesup) {
            long timeStamp = Helper.inputToMillis(minutes, seconds);
            calculatedTimeStamp = Helper.countDownCalculator(period, periodLength, timeStamp);
        }
        else if (clockGoesup) {
            long timestamp = Helper.inputToMillis(minutes, seconds);
            calculatedTimeStamp = Helper.countUpCalculator(period, periodLength, timestamp);
        }

        // Todo: Figure a way out to connect this with adding a new event. Maybe this method should be moved?
    }

    public abstract void init();

}


