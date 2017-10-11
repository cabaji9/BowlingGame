package org.bowling.game;

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

        });
    }


    private void printScoreLine(List<String> pinsDownList) {
        String[] eachPinDownArray = obtainPinDownLine(pinsDownList);
        StringBuilder scoreLine = new StringBuilder(Constants.LABEL_PINFALLS + "\t");
        Arrays.stream(eachPinDownArray).forEach(pinDown -> {
            scoreLine.append(pinDown + "\t");

        });
        System.out.println(scoreLine);
    }


    private String[] obtainPinDownLine(List<String> pinsDownList) {
        String[] eachPinDownArray = new String[21];
        int i = 0;
        boolean isSecondChance = false;
        int valOne = 0;
        for (String pinsDown : pinsDownList) {
            Integer pinsDownInteger = Constants.FOUL.equals(pinsDown) ?0:Integer.parseInt(pinsDown);
            if (i == 21) {
                break;
            }
            if (Constants.STRIKE == pinsDownInteger) {
                if (i < 18) {
                    eachPinDownArray[i++] = "";
                }
                eachPinDownArray[i] = Constants.STRIKE_VAL;
            } else {
                if (!isSecondChance) {
                    if (Constants.FOUL.equals(pinsDown)) {
                        eachPinDownArray[i] = Constants.FOUL;
                        valOne = 0;
                    } else {
                        eachPinDownArray[i] = pinsDown;
                        valOne = pinsDownInteger;
                    }
                    if (i < 18) {
                        isSecondChance = true;
                    }
                } else {
                    String secondChanceValue;
                    if (Constants.FOUL.equals(pinsDown)) {
                        secondChanceValue = Constants.FOUL;
                    } else if (pinsDownInteger + valOne == Constants.STRIKE) {
                        secondChanceValue = Constants.SPARE_VAL;
                    } else {
                        secondChanceValue = pinsDown;
                    }

                    eachPinDownArray[i] = secondChanceValue;
                    valOne = 0;
                    isSecondChance = false;
                }
            }
            i++;
        }
        return eachPinDownArray;
    }


    private void printPlayer(String player) {
        System.out.println(player);
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
        System.out.println(header);
    }
}
