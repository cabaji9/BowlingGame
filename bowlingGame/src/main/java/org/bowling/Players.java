package org.bowling;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Hyun Woo Son on 10/9/17.
 */
public class Players {

    static Logger log = Logger.getLogger(Players.class);

    private List<String> allPlayers;
    private Map<String,Integer> playersMap;


    public Players(List<String> allPlayers){
        this.allPlayers = allPlayers;
    }


    public void separateAllPlayers(){

        List<String> playersName = getPlayersName();
        log.debug("separateAllPlayers | Players are: "+ playersName);


    }


    private List<String> getPlayersName(){
        return this.allPlayers.stream().map(p -> p.split(" ")[0]).distinct().collect(Collectors.toList());
    }


    public Map<String, Integer> getPlayersMap() {
        return playersMap;
    }

}
