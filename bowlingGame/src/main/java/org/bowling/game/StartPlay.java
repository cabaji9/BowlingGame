package org.bowling.game;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * Created by Hyun Woo Son on 10/9/17.
 */
public class StartPlay {

    static Logger log = Logger.getLogger(StartPlay.class);


    public StartPlay(){

        try {
            FileRead fileRead = new FileRead(System.in);
            List<String> fileContentList = fileRead.getFileContents();

            Players players = new Players(fileContentList);
            Map<String, List<String>> playersMap =players.obtainPlayersAndScores();

            Bowling bowling = new Bowling(playersMap);
            bowling.startBowl();





        }
        catch(Exception e){
            log.error("StartPlay | An error ocurred trying to start the game.",e);
        }




    }


    public static void main(String args[]){

        StartPlay startPlay = new StartPlay();



    }


}
