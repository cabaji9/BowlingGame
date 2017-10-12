package org.bowling.game;

import org.bowling.util.Constants;

import java.util.List;

/**
 * Created by Hyun Woo Son on 10/11/17.
 */
public class PrintLine {

    String[] eachPinDownArray;

    public PrintLine(){
        eachPinDownArray = new String[21];
    }

    public String[] obtainPinDownLine(List<String> pinsDownList) {

        int i = 0;
        boolean isSecondThrow = false;
        int valOne = 0;
        for (String pinsDown : pinsDownList) {
            Integer pinsDownInteger = Constants.FOUL.equals(pinsDown) ?0:Integer.parseInt(pinsDown);
            if (i == 21) {
                break;
            }
            if (Constants.STRIKE == pinsDownInteger) {
                i= addStrike(i);
            } else {
                if (!isSecondThrow) {
                    valOne = addFirstThrowValueReturnValOne(pinsDown,i,pinsDownInteger);
                    if (i < 18) {
                        isSecondThrow = true;
                    }
                } else {
                    addSecondThrowValue(pinsDown,valOne,pinsDownInteger,i);
                    valOne = 0;
                    isSecondThrow = false;
                }
            }
            i++;
        }
        return eachPinDownArray;
    }

    private Integer addStrike(Integer i){
        if (i < 18) {
            eachPinDownArray[i++] = "";
        }
        eachPinDownArray[i] = Constants.STRIKE_VAL;
        return i;
    }

    private Integer addFirstThrowValueReturnValOne(String pinsDown, Integer i, Integer pinsDownInteger ){
        Integer valOne;
        if (Constants.FOUL.equals(pinsDown)) {
            eachPinDownArray[i] = Constants.FOUL;
            valOne = 0;
        } else {
            eachPinDownArray[i] = pinsDown;
            valOne = pinsDownInteger;
        }
        return valOne;
    }

    private void addSecondThrowValue(String pinsDown,Integer valOne, Integer pinsDownInteger,Integer i){
        String secondThrowValue;
        if (Constants.FOUL.equals(pinsDown)) {
            secondThrowValue = Constants.FOUL;
        } else if (pinsDownInteger + valOne == Constants.STRIKE) {
            secondThrowValue = Constants.SPARE_VAL;
        } else {
            secondThrowValue = pinsDown;
        }
        eachPinDownArray[i] =secondThrowValue;
    }


}
