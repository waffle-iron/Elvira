package com.example.iths.elvira.match;

/**
 * Created by Bartek Svaberg on 15-11-18.
 */
public abstract class Match {
    private int periodLength, periodLengthExtraTime, amountOfPeriods, amountOfExtraPeriods, suspensionLength;
    private String nameOfPeriod, nameOfExtraPeriod;
    private boolean elviraIsTimeKeeper;

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

    public boolean isElviraIsTimeKeeper() {
        return elviraIsTimeKeeper;
    }

    public void setElviraIsTimeKeeper(boolean elviraIsTimeKeeper) {
        this.elviraIsTimeKeeper = elviraIsTimeKeeper;
    }

    public abstract void init();

}


