package org.bowling.game;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Separate the players
 * Obtain all the scores for each player.
 *
 * Created by Hyun Woo Son on 10/9/17.
 */
public class Players {

    static Logger log = Logger.getLogger(Players.class);

    private List<String> allPlayers;
    private Map<String, List<String>> playersMap;


    public Players(List<String> allPlayers) {
        this.allPlayers = allPlayers;
        playersMap = new HashMap<>();
    }

    public Map<String, List<String>> obtainPlayersAndScores() throws Exception {
        List<String> playersName = getPlayersNames();
        addPinsScoreToPlayer(playersName);
        return this.playersMap;
    }

    private List<String> getPlayersNames() throws Exception {
        try {
            if (this.allPlayers != null && !this.allPlayers.isEmpty()) {
                List<String> playersName = this.allPlayers.stream().map(p -> p.split(" ")[0]).distinct().collect(Collectors.toList());
                log.debug("separateAllPlayers | Players are: " + playersName);
                return playersName;
            }
        } catch (Exception e) {
            throw new Exception("Cant obtain players names, syntax is incorrect", e);
        }
        throw new Exception("The list of players is empty");
    }

    private void addPinsScoreToPlayer(List<String> playersName) throws Exception {
        if (!validateLists(playersName)) {
            log.debug("addPinsScoreToPlayer | List validated  " );
            playersName.stream().forEach(player -> {
                List<String> points = this.allPlayers.stream().filter(playerLine -> playerLine.contains(player)).map(playerLine -> playerLine.split(" ")[1]).collect(Collectors.toList());
                playersMap.put(player, points);
            });
            log.debug("addPinsScoreToPlayer | Obtained all scores: "+ playersMap);
        }
        else{
            throw new Exception("List are empty");
        }
    }

    private boolean validateLists(List<String> playersName) {
        boolean emptyLists = true;
        if (playersName != null && !playersName.isEmpty()) {
            emptyLists = false;
        }
        return emptyLists;
    }
}
