package com.example.iths.elvira.Parser;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.iths.elvira.match.FutsalMatch;
import com.example.iths.elvira.player.FutsalPlayer;
import com.example.iths.elvira.team.FutsalTeam;

import java.io.IOException;

public class Parser {

    public static FutsalMatch parse(String url) throws IOException {
        return getDataFromFogis(url);
    }

    private static FutsalMatch getDataFromFogis(String url) throws IOException {
        FutsalMatch match = new FutsalMatch();

        Document doc = Jsoup.connect(url).get();

        Element homeTeamElement = doc.getElementsByClass("squad hometeam-squad").first();
        Element awayTeamElement = doc.getElementsByClass("squad awayteam-squad").first();

        match.homeTeam = addTeamFromHtml(homeTeamElement);
        match.awayTeam = addTeamFromHtml(awayTeamElement);

        return match;
    }

    private static FutsalTeam addTeamFromHtml(Element teamElement) {
        FutsalTeam team = new FutsalTeam();
        String name = getTeamNameFromHtml(teamElement);
        team.setName(name);

        Elements playerList = teamElement.getElementsByTag("ul").first().children();
        Elements substituteList = teamElement.getElementsByTag("ul").first().lastElementSibling().children();
        addPlayersAndSubstitutesFromHtml(team, playerList, false);
        addPlayersAndSubstitutesFromHtml(team, substituteList, true);
        return team;
    }

    private static FutsalTeam addPlayersAndSubstitutesFromHtml(FutsalTeam team, Elements playerList, boolean substitute) {
        for (Element playerElement : playerList) {
            String[] names;
            int number = Integer.parseInt(playerElement.getElementsByClass("number").first().text().split("[.]")[0]);
            if (playerElement.getElementsByClass("name-and-number").first().getElementsByClass("name").size() != 0 ) {
                names = playerElement.getElementsByClass("name-and-number").first().getElementsByClass("name").first().text().split("[ ]");
            } else {
                String nameHtml = playerElement.getElementsByClass("name-and-number").first().html().trim();
                nameHtml = nameHtml.substring(nameHtml.indexOf("</span> ") + 8);
                if (nameHtml.contains("<span")) {
                    nameHtml = nameHtml.split(" <span")[0];
                }
                names = nameHtml.split("[ ]");
            }

            String firstName = names[0];
            String lastName = "";
            for (int i = 1; i < names.length; i++) {
                if (!names[i].contains("\"")) {
                    lastName += names[i] + " ";
                }
            }
            lastName = lastName.trim();
            FutsalPlayer player = new FutsalPlayer(firstName, lastName, 0, team, number);
            player.setSubstitute(substitute);
            team.addPlayer(player);
            System.out.println();
        }
        return team;
    }

    private static String getTeamNameFromHtml(Element teamElement) {
        return teamElement.getElementsByTag("h3").first().text();
    }
}
