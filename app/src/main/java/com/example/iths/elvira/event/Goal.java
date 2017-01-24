package com.example.iths.elvira.event;

import android.util.Log;

import com.example.iths.elvira.player.Player;

/**
 * Created by Robin on 2015-11-11.
 */
public class Goal extends Event {

    public Goal(long time, Player player){
        super(time, player);
    }
}
