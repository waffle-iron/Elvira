package com.example.iths.elvira.player;

import com.example.iths.elvira.team.Team;

/**
 * Created by Bartek Svaberg on 15-11-10.
 */
public abstract class Player implements IPlayer {

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
    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Getter method for player number
     * @return player number
     */
    @Override
    public int getNumber() {
        return this.number;
    }

    /**
     * Setter method for player first name
     * @param firstName, String containing first name
     */
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter method for player first name
     * @return String containing player first name
     */
    @Override
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Setter method for player last name
     * @param lastName String conatining player last name
     */
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter method for player last name
     * @return a String conatining player last name
     */
    @Override
    public String getLastName() {
        return this.lastName;
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