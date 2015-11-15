package com.example.iths.elvira.player;

import com.example.iths.elvira.team.Team;

/**
 * Created by Bartek Svaberg on 15-11-10.
 */
public abstract class Player extends Person {

    // Keeps track of player number
    private int number;
    // Player first and last name
    private String firstName, lastName;
    // Player team
    private Team team;

    // Constructor instantiating a new player
    public Player(int number, String firstName, String lastName, Team team) {
        this.number = number;
        this.firstName=firstName;
        this.lastName=lastName;
        this.team = team;
    }

    /**
     * Setter method for player number
     * @param number int with player number
     */

    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Getter method for player number
     * @return player number
     */

    public int getNumber() {
        return this.number;
    }

    /**
     * Getter method for player team
     * @return player Team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Setter method for player team
     * @param team player Team
     */
    public void setTeam(Team team) {
        this.team = team;
    }
}