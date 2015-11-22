package com.example.iths.elvira.event;

import com.example.iths.elvira.player.Player;

/**
 * Created by Robin on 2015-11-11.
 */
public class awayFreekick extends Event{
    private int atFoul;
    static int atCounter;

    public awayFreekick(long time, Player player){
        super(time, player);
    }
    public awayFreekick(){
        atCounter++;
        atFoul = atCounter;
    }
    public int getAtFoul() {
        return atFoul;
    }
}
