package com.example.iths.elvira.event;

import com.example.iths.elvira.player.Player;

/**
 * Created by Robin on 2015-11-11.
 */
public class homeFreekick extends Event{
    private int htFoul;
    static int htCounter;

    public homeFreekick(long time, Player player){
        super(time, player);
    }
    public homeFreekick(){
        htCounter++;
        htFoul = htCounter;
    }
    public int getHtFoul() {
        return htFoul;
    }
}
