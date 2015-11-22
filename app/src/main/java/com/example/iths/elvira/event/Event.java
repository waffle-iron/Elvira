package com.example.iths.elvira.event;

import com.example.iths.elvira.player.Player;

/**
 * Created by Robin on 2015-11-11.
 */
public class Event {
    private long time;
    private Player player;

    public Event(long time, Player player){
        this.time = time;
        this.player = player;
    }
    public Event(long time){
        this.time = time;
    }
    //default constructor
    public Event(){}
}
