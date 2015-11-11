package com.example.iths.elvira.Events;

import com.example.iths.elvira.player.Player;

/**
 * Created by Robin on 2015-11-11.
 */
public class Events {
    private long time;
    private Player player;

    public Events(long time, Player player){
        this.time = time;
        this.player = player;
    }
    public Events(long time){
        this.time = time;
    }
}
