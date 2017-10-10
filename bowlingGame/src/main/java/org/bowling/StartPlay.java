package org.bowling;

import org.apache.log4j.Logger;

import java.util.List;

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
            players.separateAllPlayers();

        }
        catch(Exception e){
            log.error("StartPlay | An error ocurred trying to start the game.",e);
        }




    }


    public static void main(String args[]){

        StartPlay startPlay = new StartPlay();



    }


}
