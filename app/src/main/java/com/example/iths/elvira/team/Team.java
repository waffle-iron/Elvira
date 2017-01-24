package com.example.iths.elvira.team;

import com.example.iths.elvira.player.FutsalPlayer;
import com.example.iths.elvira.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Bartek Svaberg on 15-11-10.
 */
public abstract class Team implements ITeam {

    // Keeps track of Team Name
    private String name;

    // Keeps track of player within team
    public ArrayList<Player> playerList = new ArrayList<>();

    /**
     * Getter method for team name
     * @return a String containing Team name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Setter method for Team name
     * @param name String conatining team name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }
    public void addPlayer(String firstName, String lastName, int id, int number){
        addPlayer(firstName, lastName, id, number, true);
    }

    public void addPlayer(String firstName, String lastName, int id, int number, boolean substitute) {
        FutsalPlayer player = new FutsalPlayer(firstName, lastName, id, this, number);
        player.setSubstitute(substitute);
        addPlayer(player);
    }

    public Player getPlayer(Integer number) {
        return playerList.get(number);
    }

    /**
     * Stringed method to be used if player does not exist but event needs to be added.
     * Accepts number only. Adds empty firs name and last name.
     * Passes parameters on to addPlayer(number, firstName lastName)
     * @param number, int with player number
     */
    public void addPlayer (int number) {
        addPlayer("", "", 0, number);
    }
}
