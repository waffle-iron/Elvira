package com.example.iths.elvira.team;

import com.example.iths.elvira.player.FutsalPlayer;
import com.example.iths.elvira.player.Player;

import java.util.HashMap;

/**
 * Created by Bartek Svaberg on 15-11-10.
 */
public abstract class Team implements ITeam {

    private String name;

    public HashMap<Integer, Player> playerList = new HashMap<>();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void addPlayer(int number, String firstName, String lastName){
        new FutsalPlayer(number, firstName, lastName, this);
        playerList.put(number, new FutsalPlayer (number, firstName,lastName, this));
    }

    public void addPlayer (int number) {
        addPlayer(number, "", "");
    }
}
