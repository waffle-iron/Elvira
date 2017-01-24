package com.example.iths.elvira.player;

import com.example.iths.elvira.team.Team;

/**
 * Created by Bartek Svaberg on 15-11-08.
 */
public class FutsalPlayer extends Player{

    public FutsalPlayer(String firstName, String lastName, int id, Team team, int number) {
        super(firstName, lastName, id, team, number);
    }

    private boolean substitute = false;
    private boolean captain = false;

    public boolean isSubstitute() {
        return substitute;
    }

    public void setSubstitute(boolean substitute) {
        this.substitute = substitute;
    }

    public boolean isCaptain() {
        return captain;
    }

    public void setCaptain(boolean captain) {
        this.captain = captain;
    }
}
