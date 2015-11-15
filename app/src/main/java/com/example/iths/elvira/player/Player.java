package com.example.iths.elvira.player;

import com.example.iths.elvira.team.Team;

/**
 * Created by Bartek Svaberg on 15-11-10.
 */
public abstract class Player extends Person {

    // Keeps track of player number
    private int number;
    // Player team
    private Team team;

    // Constructor instantiating a new player
    public Player(String firstName, String lastName, int id, Team team, int number) {
        super(firstName, lastName, id);
        this.number = number;
        this.team = team;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}