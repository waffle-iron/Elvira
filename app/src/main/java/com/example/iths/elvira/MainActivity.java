package com.example.iths.elvira;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView homeTeamName, awayTeamName, homeTeamScore, awayTeamScore, homeTeamFoul, awayTeamFoul;

    private String htName, atName;
    private int htScore, atScore, htFoul, atFoul;

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
        htName = homeTeamName.getText().toString();
        int f = 1;
        showConfirmDialog(htName, f);
    }

    public void homeTeamYellowCard(View view) {
    }

    public void homeTeamRedCard(View view) {
    }

    public void awayTeamGoal(View view) {
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
}
