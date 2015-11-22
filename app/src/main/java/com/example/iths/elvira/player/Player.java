package com.example.iths.elvira.player;

import com.example.iths.elvira.team.Team;

/**
 * Created by Bartek Svaberg on 15-11-10.
 */
public abstract class Player extends Person {

    // Keeps track of player number
    private String number;
    // Player team
    private Team team;

    // Constructor instantiating a new player
    public Player(String firstName, String lastName, String id, Team team, String number) {
        super(firstName, lastName, id);
        this.number = number;
        this.team = team;
    }
    //Konstruktor som Robin skapat för att kunna köra med match.xml. Alla värden är String! (2015-11-19)
    public Player(String firstName, String lastName, String number, String id) {
        super(firstName, lastName, id);
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}