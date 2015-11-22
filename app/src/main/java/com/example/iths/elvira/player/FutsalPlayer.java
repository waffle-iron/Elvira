package com.example.iths.elvira.player;

import com.example.iths.elvira.team.Team;

/**
 * Created by Bartek Svaberg on 15-11-08.
 */
public class FutsalPlayer extends Player{

    public FutsalPlayer(String firstName, String lastName, String number, Team team, String id) {
        super(firstName, lastName, id, team, number);
    }

    //Konstruktor som Robin skapat för att kunna köra med match.xml. Alla värden är String! (2015-11-19)
    public FutsalPlayer(String firstName, String lastName, String number, String id){
        super(firstName, lastName, number, id);
    }

    @Override
    public String toString(){
        return getNumber()+": "+getFirstName()+" "+getLastName();
    }

}
