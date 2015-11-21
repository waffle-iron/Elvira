package com.example.iths.elvira.match;

import com.example.iths.elvira.team.FutsalTeam;

/**
 * Created by Bartek Svaberg on 15-11-11.
 */
public class FutsalMatch extends Match{


    @Override
    public void init() {
        FutsalTeam homeTeam = new FutsalTeam();
        FutsalTeam awayTeam = new FutsalTeam();
    }
}
