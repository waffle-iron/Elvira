package com.example.iths.elvira.match;

import com.example.iths.elvira.event.Event;
import com.example.iths.elvira.team.FutsalTeam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bartek Svaberg on 15-11-11.
 */
public class FutsalMatch extends Match{
    public FutsalTeam homeTeam;
    public FutsalTeam awayTeam;
    public List<Event> events = new ArrayList<>();

    @Override
    public void init() {
        setNameOfPeriod("half");
        setNameOfExtraPeriod("half of extra time");
        homeTeam = new FutsalTeam();
        awayTeam = new FutsalTeam();
    }
}
