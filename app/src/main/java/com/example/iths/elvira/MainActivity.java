package com.example.iths.elvira;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView homeTeamName, awayTeamName, homeTeamScore, awayTeamScore, homeTeamFoul, awayTeamFoul;

    private String htName, atName;
    //get default value of 0
    private int htScore, atScore, htFoul, atFoul;

    //lägger till dessa för att se om det ändras
    private String skräp;
    
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
    }

    public void homeTeamGoal(View view) {
    }

    public void homeTeamFoul(View view) {
    }

    public void homeTeamYellowCard(View view) {
    }

    public void homeTeamRedCard(View view) {
    }

    public void awayTeamGoal(View view) {
    }

    public void awayTeamFoul(View view) {
    }

    public void awayTeamYellowCard(View view) {
    }

    public void awayTeamRedCard(View view) {
    }
}
