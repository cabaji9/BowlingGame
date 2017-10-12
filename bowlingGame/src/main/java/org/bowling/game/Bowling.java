package org.bowling.game;

import org.bowling.util.BowlingUtil;
import org.bowling.util.Constants;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Hyun Woo Son on 10/10/17.
 */
public class Bowling {

    private Map<String, List<String>> playersMap;


    public Bowling(Map<String, List<String>> playersMap) {
        this.playersMap = playersMap;
    }

    public void startBowl() {
        printHeader();
        this.playersMap.keySet().stream().forEach(player -> {
            List<String> pinsDownList = this.playersMap.get(player);
            printPlayer(player);
            printScoreLine(pinsDownList);
            printTotalScoreLine(pinsDownList);
        });
    }


    private void printScoreLine(List<String> pinsDownList) {
        String[] eachPinDownArray = new PrintLine().obtainPinDownLine(pinsDownList);
        StringBuilder scoreLine = new StringBuilder(Constants.LABEL_PINFALLS + "\t");
        Arrays.stream(eachPinDownArray).forEach(pinDown -> {
            scoreLine.append(pinDown + "\t");
        });
        BowlingUtil.print(scoreLine.toString());
    }


    private void printTotalScoreLine(List<String> pinsDownList) {

        String[] totalScoreArray = new PrintTotalScoreLine(pinsDownList).obtainTotalScore();
        StringBuilder scoreLine = new StringBuilder(Constants.LABEL_SCORE + "\t\t");
        Arrays.stream(totalScoreArray).forEach(score -> {
            scoreLine.append(score + "\t\t");
        });
        BowlingUtil.print(scoreLine.toString());


    }


    private void printPlayer(String player) {
        BowlingUtil.print(player);
    }


    private void printHeader() {
        StringBuilder header = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            if (i == 0) {
                header.append(Constants.LABEL_FRAME + "\t\t");
            } else {
                header.append(i + "\t\t");
            }
        }
        BowlingUtil.print(header.toString());
    }
}
