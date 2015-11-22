package com.example.iths.elvira;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iths.elvira.event.awayFreekick;
import com.example.iths.elvira.event.homeFreekick;
import com.example.iths.elvira.player.FutsalPlayer;
import com.example.iths.elvira.team.FutsalTeam;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import static com.example.iths.elvira.R.id;
import static com.example.iths.elvira.R.layout;
import static com.example.iths.elvira.R.string;

public class MainActivity extends AppCompatActivity {

    TextView homeTeamName, awayTeamName, homeTeamScore, awayTeamScore, homeTeamFoul, awayTeamFoul;
    //En spinner skapas för att visa att kunna visa valbara objekt (2015-11-20)
    Spinner spPickTime, spTeamList;
    //EditText som används för att ange minut
    TextView tvPickTime, tvTeamList;

    //array som tar hand om minutangivelserna för Futsal. Detta bör ändras när det kommer till fler sporter (2015-11-22)
    String futsalTime[];

    private int htScore, atScore, htFoul, atFoul;
    private int x = 0;
    private final String DEBUG = "Debug";

    //sökvägar som ska parseas skapas beroende på vilken laglist som hämtas (2015-11-20)
    private final String htList = "/match/deltaglistalag1/spelare";
    private final String atList = "/match/deltaglistalag2/spelare";
    //sökvägar som ska parseas skapas beroende på vilket lagnamn som ska hämtas (2015-11-20)
    private final String htTeamName = "/match/matchinfo/lag1namn/text()";
    private final String atTeamName = "/match/matchinfo/lag2namn/text()";

    //Två lagobjekt skapas för att testa om det går att parsea xml filen och genom det hämta namn (2015-11-20)
    FutsalTeam homeTeam = new FutsalTeam(getTeamNameFromXML(htTeamName),getTeamListFromXML(htList));
    FutsalTeam awayTeam = new FutsalTeam(getTeamNameFromXML(atTeamName),getTeamListFromXML(atList));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        homeTeamName = (TextView) findViewById(id.tv_home_team_name);
        awayTeamName = (TextView) findViewById(id.tv_away_team_name);
        homeTeamScore = (TextView) findViewById(id.tv_home_team_score);
        awayTeamScore = (TextView) findViewById(id.tv_away_team_score);
        homeTeamFoul = (TextView) findViewById(id.tv_home_team_free_kicks);
        awayTeamFoul = (TextView) findViewById(id.tv_away_team_free_kicks);

        //TextViews som används som rubriker för Spinners i showConfirmEventDialog (2015-11-20)
        tvPickTime = (TextView) findViewById(id.tv_pick_time);
        tvTeamList = (TextView) findViewById(id.tv_team_list);

        //set both teams score and foul to default (0) when created
        homeTeamScore.setText(String.valueOf(htScore));
        awayTeamScore.setText(String.valueOf(atScore));
        homeTeamFoul.setText(String.valueOf(htFoul));
        awayTeamFoul.setText(String.valueOf(atFoul));

        //namnen sätts av objektet FutsalTeam som skapats ovan (2015-11-19)
        homeTeamName.setText(homeTeam.getName());
        awayTeamName.setText(awayTeam.getName());

        //populerar futsalTime med de items som rymms i futsal_time (2015-11-22)
        futsalTime = getResources().getStringArray(R.array.futsal_time);
    }

    public void homeTeamGoal(View view) {
        showConfirmEventDialog(homeTeam.getPlayerList());
    }

    public void addHomeTeamFoul(View view) {
        showConfirmDialog(homeTeam.getName());
    }

    public void homeTeamYellowCard(View view) {
    }

    public void homeTeamRedCard(View view) {
    }

    public void awayTeamGoal(View view) {
        showConfirmEventDialog(awayTeam.getPlayerList());
    }

    public void addAwayTeamFoul(View view) {
        showConfirmDialog(awayTeam.getName());
    }

    public void awayTeamYellowCard(View view) {
    }

    public void awayTeamRedCard(View view) {
    }

    //forces the user to confirm the freekick input
    private void showConfirmDialog(final String team){
        //shows confirmationsdialog
        new AlertDialog.Builder(this)
                .setTitle("Foul " + team + "?")
                .setMessage("Add foul to "+ team + ":"+"\n\n")
                .setPositiveButton(string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        //checks wich team should get the foul  (2015-11-20)
                        if (team.equals(homeTeam.getName())) {
                            homeFreekick newHtfoul = new homeFreekick();
                            homeTeamFoul.setText(String.valueOf(newHtfoul.getHtFoul()));
                        } else {
                            awayFreekick newAtfoul = new awayFreekick();
                            awayTeamFoul.setText(String.valueOf(newAtfoul.getAtFoul()));
                        }
                    }
                })
                .setNegativeButton(string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    //Dialogfönster som ber användaren välja tidpunkt (EditText) och spelarobjekt (Spinner) för händelse (2015-11-20)
    private void showConfirmEventDialog(ArrayList teamList) {

        AlertDialog alertDialog;

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_confirm_event, null);

        //skapar ArrayAdapter med given playerlist som populerar Spinnern spTeamList (2015-11-22)
        ArrayAdapter<String> teamAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, teamList);
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTeamList = (Spinner) layout.findViewById(id.sp_team_list);

        //skapar ArrayAdapter med given Array, futsalTime, som populerar Spinnern spPickTime (2015-11-22)
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, futsalTime);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPickTime = (Spinner) layout.findViewById(id.sp_pick_time);

        try {
            spTeamList.setAdapter(teamAdapter);
            spPickTime.setAdapter(timeAdapter);
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "Exception: "+ e,Toast.LENGTH_SHORT).show();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(layout);
        builder.setTitle(string.confirmEvent)
                //add action buttons
                .setPositiveButton(string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton(string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //cancel
                    }
                });
        alertDialog = builder.create();
        alertDialog.show();
    }

    //Team name parser. Kan exporteras till egen Parseklass när allt som ska parsas är bestämt (2015-11-20)
    public String getTeamNameFromXML(String teamName){
        String name = null;
        try {
            String sdCardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            String fileName = "match.xml";
            File xmlFile = new File(sdCardPath, fileName);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            XPath xpath = XPathFactory.newInstance().newXPath();
            XPathExpression expr = xpath.compile(teamName);

            name = (String) expr.evaluate(doc, XPathConstants.STRING);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }
    //Team list parser. Kan exporteras till egen Parseklass när allt som ska parsas är bestämt (2015-11-20)
    public ArrayList getTeamListFromXML(String teamList){
        ArrayList list = new ArrayList();

        try {
            //-------Fungerande kod. Nedanstående blir test för att parsea från webben--------//
            //--------------------------------------------------------------------------------//
            String sdCardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            String fileName = "match.xml";
            File xmlFile = new File(sdCardPath, fileName);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            XPath xpath = XPathFactory.newInstance().newXPath();
            XPathExpression expr = xpath.compile(teamList);
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nodes.getLength(); ++i) {
                Node file = nodes.item(i);

                System.out.println("\nCurrent Element :" + file.getNodeName());
                if (file.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) file;

                    String id = eElement.getElementsByTagName("spelareid").item(0).getTextContent();
                    //String persnr = "Personnummer: " + eElement.getElementsByTagName("personnr").item(0).getTextContent();
                    String firstName = eElement.getElementsByTagName("fornamn").item(0).getTextContent();
                    String lastName = eElement.getElementsByTagName("efternamn").item(0).getTextContent();
                    String number = eElement.getElementsByTagName("trojnr").item(0).getTextContent();
                    //String substitute = "Avbytare: " + eElement.getElementsByTagName("reserv").item(0).getTextContent();

                    FutsalPlayer player = new FutsalPlayer (firstName, lastName, number, id);

                    list.add(player);
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
