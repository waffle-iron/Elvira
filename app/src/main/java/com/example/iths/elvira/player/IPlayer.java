package com.example.iths.elvira.player;

import com.example.iths.elvira.team.Team;

/**
 * Created by Bartek Svaberg on 15-11-08.
 */
public interface IPlayer {
    void setNumber(int number);
    int getNumber();
    void setFirstName(String firstName);
    String getFirstName();
    void setLastName(String lastName);
    String getLastName();
    void setTeam();
    Team getTeam();
}
