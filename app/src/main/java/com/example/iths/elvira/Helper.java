package com.example.iths.elvira;

/**
 * Created by Bartek Svaberg on 15-11-22.
 */
public class Helper {

    /**
     * Converts inputted time in minutes and/or seconds to millis
     * @param minutes - input time in minutes
     * @param seconds - input time in seconds
     * @return millis, a long with the given time formatted into millis
     */
    public static long inputToMillis(int minutes, int seconds){
        long millis = (minutes*60000)+(seconds*1000);
        return millis;
    }

    /** Calculates the correct time in milliseconds for when an event has occurred.
     * Used when Elvira is not the official time keeper and the arena clock counts from
     * [periodLength] to zero each period.
     * @param period - the period that is being played
     * @param periodLength - length of period in minutes
     * @param timeStamp - the time at which the event has occurred
     * @return millis - a long containing the accumulated time stamp for an event.
     */
    public static long countdownCalculator(int period, int periodLength, long timeStamp){
        long periodInMillis = periodLength * 60000;
        return (periodInMillis*(period-1))+(periodLength-timeStamp);
    }

    /** Calculates the correct time in milliseconds for when an event has occurred.
     * Used when the clock counts from zero to [periodLength] each period.
     * @param period - the period that is being played
     * @param periodLength - length of period in minutes
     * @param timeStamp - the time at which the event has occurred
     * @return millis - a long containing the accumulated time stamp for an event.
     */
    public static long countUpCalculator(int period, int periodLength, long timeStamp){
        long periodInMillis = periodLength * 60000;
        return (periodInMillis*(period-1))+timeStamp;
    }

}
