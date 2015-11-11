package com.example.iths.elvira.team;

import com.example.iths.elvira.player.FutsalPlayer;
import com.example.iths.elvira.player.Player;

import java.util.HashMap;

/**
 * Created by Bartek Svaberg on 15-11-10.
 */
public abstract class Team implements ITeam {

    // Keeps track of Team Name
    private String name;

    // Keeps track of player within team
    public HashMap<Integer, Player> playerList = new HashMap<>();

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

    /**
     * Creates a new player by sending parameters to constructor as below
     * @param number - player number
     * @param firstName - player first name
     * @param lastName - player last name
     * this - adds team to player
     */
    public void addPlayer(int number, String firstName, String lastName){
        new FutsalPlayer(number, firstName, lastName, this);
        playerList.put(number, new FutsalPlayer (number, firstName,lastName, this));
    }

    /**
     * Stringed method to be used if player does not exist but event needs to be added.
     * Accepts number only. Adds empty firs name and last name.
     * Passes parameters on to addPlayer(number, firstName lastName)
     * @param number, int with player number
     */
    public void addPlayer (int number) {addPlayer(number, "", "");
    }
}
