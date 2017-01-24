package com.example.iths.elvira.event;

import com.example.iths.elvira.player.Player;
import com.example.iths.elvira.team.Team;

/**
 * Created by barteksvaberg on 2017-01-17.
 */

public class FreeKick extends Event {

    private Team team;

    public FreeKick(long time, Player player) {
        super(time, player);
        team = player.getTeam();
    }

    public FreeKick(long time) {
        super(time);
    }

    public FreeKick(long time, Team team) {
        super(time);
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
