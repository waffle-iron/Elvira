package com.example.iths.elvira.match;

import com.example.iths.elvira.Helper;
import com.example.iths.elvira.event.Event;
import com.example.iths.elvira.player.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by Bartek Svaberg on 15-11-18.
 */
public abstract class Match {
    private int periodLength, periodLengthExtraTime, amountOfPeriods, amountOfExtraPeriods, suspensionLength;
    private String nameOfPeriod = "period", nameOfExtraPeriod = "extra period";
    private boolean elviraHasOfficialTime, clockGoesup, clockIsResetEachPeriod, betweenPeriods;
    private ArrayList<Event> eventList = new ArrayList<>();
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

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public boolean isBetweenPeriods() {
        return betweenPeriods;
    }

    public void setBetweenPeriods(boolean betweenPeriods) {
        this.betweenPeriods = betweenPeriods;
    }

    /**
     * Calculates event time in milliseconds from minutes and seconds.
     * Calls the appropriate Helper methods depending on how match clock behaves.
     * @param minutes - int of minutes
     * @param seconds - int of seconds
     * @return long with calculated time in millis
     */
    public long calculateEventTime(int minutes, int seconds) {
        long calculatedTimeStamp = Helper.inputToMillis(minutes, seconds);

        if (elviraHasOfficialTime) {
            calculatedTimeStamp = Helper.inputToMillis(minutes, seconds);
        } else if (!clockGoesup) {
            long timeStamp = Helper.inputToMillis(minutes, seconds);
            calculatedTimeStamp = Helper.countDownCalculator(period, periodLength, timeStamp);
        } else if (clockGoesup) {
            long timestamp = Helper.inputToMillis(minutes, seconds);
            calculatedTimeStamp = Helper.countUpCalculator(period, periodLength, timestamp);
        }
        return calculatedTimeStamp;
    }

    /**
     * Adds a new event from user input.
     * @param eventName - a String containing the correct name of the Event subclass to be created.
     * @param timeStamp - time in millis when the event occurred.
     * @param player - The player to be added to the event.
     */
    public void addEvent (String eventName, long timeStamp, Player player) {
        Object o;
        if (betweenPeriods){
            timeStamp = period*periodLength*60000;
        }
        try {
            o = Class.forName(eventName).getConstructor(long.class, Player.class).newInstance(timeStamp, player);
            eventList.add((Event)o);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public abstract void init();

}


