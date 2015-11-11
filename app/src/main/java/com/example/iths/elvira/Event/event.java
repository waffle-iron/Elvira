package com.example.iths.elvira.Event;

import com.example.iths.elvira.player.Player;

/**
 * Created by Bartek Svaberg on 15-11-10.
 */
public class event {

    private long time;
    private int typeInt, subTypeInt;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private Player player;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getTypeInt() {
        return typeInt;
    }

    public void setTypeInt(int typeInt) {
        this.typeInt = typeInt;
    }

    public int getSubTypeInt() {
        return subTypeInt;
    }

    public void setSubTypeInt(int subTypeInt) {
        this.subTypeInt = subTypeInt;
    }

    @Override
    public String toString() {
        return typeInt+" "+player.getNumber()+". "+player.getFirstName()+" "+player.getLastName()+", "+player.getTeam();
    }
}
