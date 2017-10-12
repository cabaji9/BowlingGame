package org.bowling.game;

import org.bowling.util.Constants;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Created by Hyun Woo Son on 10/11/17.
 */
public class PrintTotalScoreLine {


    private String[] scoreArray;
    private List<Integer> pinsDownList;

    public PrintTotalScoreLine(List<String> pinsDownList){
        this.scoreArray = new String[10];
        this.pinsDownList =convertToInteger(pinsDownList);
    }


    private List<Integer> convertToInteger(List<String> pinsDownList){
        return pinsDownList.stream().map(pinsDown -> {
           Integer pinsDownInteger =0;
            if(!Constants.FOUL.equals(pinsDown)){
                pinsDownInteger = Integer.parseInt(pinsDown);
           }
            return pinsDownInteger;
        }).collect(Collectors.toList());
    }



    public String[] obtainTotalScore(){
        int frame =0;
        int score =0;
        for(int i=0;i<10;i++){

            if (isStrike(frame)) {
                score += 10 + strikeBonus(frame);
                frame++;
            } else if (isSpare(frame)) {
                score += 10 + spareBonus(frame);
                frame += 2;
            } else {
                score += sumOfRolls(frame);
                frame += 2;
            }
            scoreArray[i]= score+"";
        }
        return scoreArray;
    }


    private boolean isStrike(int frame) {
        return pinsDownList.get(frame) == 10;
    }

    private boolean isSpare(int frame) {
        return sumOfRolls(frame) == 10;
    }

    private int strikeBonus(int frame) {
        return sumOfRolls(frame+1);
    }

    private int spareBonus(int frame) {
        return pinsDownList.get(frame+2);
    }

    private int sumOfRolls(int frame) {
        return pinsDownList.get(frame) + pinsDownList.get(frame+1);
    }



}
