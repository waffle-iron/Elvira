package com.example.iths.elvira;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.iths.elvira.event.Event;
import com.example.iths.elvira.event.Goal;
import com.example.iths.elvira.event.RedCard;
import com.example.iths.elvira.event.YellowCard;
import com.example.iths.elvira.match.FutsalMatch;
import com.example.iths.elvira.player.Player;
import com.example.iths.elvira.event.*;
import com.example.iths.elvira.team.Team;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FutsalMatch match;

    TextView homeTeamName, awayTeamName, homeTeamScore, awayTeamScore, homeTeamFoul, awayTeamFoul;

    private String htName, atName;
    private int htScore, atScore, htFoul, atFoul;
    private int period = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeTeamName = (TextView) findViewById(R.id.tv_home_team_name);
        awayTeamName = (TextView) findViewById(R.id.tv_away_team_name);
        homeTeamScore = (TextView) findViewById(R.id.tv_home_team_score);
        awayTeamScore = (TextView) findViewById(R.id.tv_away_team_score);
        homeTeamFoul = (TextView) findViewById(R.id.tv_home_team_free_kicks);
        awayTeamFoul = (TextView) findViewById(R.id.tv_away_team_free_kicks);

        //set both teams score and foul to default (0) when created
        homeTeamScore.setText(String.valueOf(htScore));
        awayTeamScore.setText(String.valueOf(atScore));
        homeTeamFoul.setText(String.valueOf(htFoul));
        awayTeamFoul.setText(String.valueOf(atFoul));

        match = new FutsalMatch();
        match.init();
        match.homeTeam.setName("Hemma");
        match.awayTeam.setName("Borta");
        match.awayTeam.addPlayer("Bartek", "Svaberg", 0, 13);
    }

    public void homeTeamGoal(View view) {
    }

    public void homeTeamFoul(View view) {
        htName = homeTeamName.getText().toString();
        int f = 1;
        showConfirmDialog(htName, f);
    }

    public void homeTeamYellowCard(View view) {
    }

    public void homeTeamRedCard(View view) {
    }

    public void awayTeamGoal(View view) {
        addEvent(0, match.awayTeam.playerList.get(13), EventType.GOAL);
        awayTeamScore.setText(String.valueOf(getScore(match.awayTeam)));
    }

    public void awayTeamFoul(View view) {
        atName = awayTeamName.getText().toString();
        int f = 2;
        showConfirmDialog(atName, f);
    }

    public void awayTeamYellowCard(View view) {
    }

    public void awayTeamRedCard(View view) {
    }
    private void showConfirmDialog(String team, final int f){
        //shows confirmationsdialog
        new AlertDialog.Builder(this)
                .setTitle("Foul " + team + "?")
                .setMessage("Add freekick to "+ team+ ":"+"\n\n")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //add foul to chosen team
                        if(f==1){
                            htFoul++;
                            homeTeamFoul.setText(String.valueOf(htFoul));
                        }
                        if(f==2){
                            atFoul++;
                            awayTeamFoul.setText(String.valueOf(atFoul));
                        }
                    }
                })
                .setNegativeButton("Avbryt", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private enum EventType {
        GOAL ("Goal"),
        CAUTION ("YellowCard"),
        SENDOFF ("RedCard"),
        FREEKICK ("FreeKick");

        private final String stringValue;

        private EventType(String stringValue) {
            this.stringValue = stringValue;
        }

        public String stringValue() {
            return stringValue;
        }
    }

    private void addEvent(long time, Player player, EventType eventType) {
        switch (eventType) {
            case GOAL:
                match.events.add(new Goal(time, player));
                updateScore(player.getTeam());
                break;
            case FREEKICK:
                match.events.add(new FreeKick(time, player));
                updateFreeKicks(player.getTeam());
                break;
            case CAUTION:
                match.events.add(new YellowCard(time, player));
                break;
            case SENDOFF:
                match.events.add(new RedCard(time, player));
                break;
            //case SUBSTITUTION:
            //    break;
        }
/*
        Class className = Class.forName(eventType.stringValue());
        Constructor constructor = className.getConstructor(Long.class, Player.class);
        Object event = constructor.newInstance(time, player);
*/
    }

    private void updateFreeKicks(Team team) {
        TextView textView;
        if (team.equals(match.homeTeam)){
            textView = homeTeamFoul;
        } else {
            textView = awayTeamFoul;
        }
        textView.setText(String.valueOf(getNumberOfFreeKicks(team)));
    }

    private void updateScore(Team team) {
        TextView textView;
        if (team.equals(match.homeTeam)){
            textView = homeTeamScore;
        } else {
            textView = awayTeamScore;
        }
        textView.setText(String.valueOf(getScore(team)));
    }

    private int getScore(Team team) {
        return goals(team).size();
    }

    private ArrayList<Goal> goals(Team team) {
        ArrayList goals = new ArrayList();
        for (Goal goal: goals()) {
            if (goal.getPlayer().getTeam() == team) {
                goals.add(goal);
            }
        }
        return goals;
    }

    private ArrayList<Goal> goals() {
        ArrayList<Goal> goals = new ArrayList<>();
        for (Event event: match.events) {
            if (event instanceof Goal) {
                goals.add((Goal)event);
            }
        }
        return goals;
    }

    private int getNumberOfFreeKicks(Team team) {
        int numberOfFreeKicks = 0;
        for (Event event: match.events) {
            if (event instanceof FreeKick) {
                if (((FreeKick) event).getTeam().equals(team));
                numberOfFreeKicks++;
            }
        }
        return numberOfFreeKicks;
    }
}
