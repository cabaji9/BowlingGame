package org.bowling;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hyun Woo Son on 10/10/17.
 */
public class PlayersTest extends FileReadTest {


    static Logger log = Logger.getLogger(PlayersTest.class);


    @Before
    public void setUp(){
        super.setUp();
    }

    @Test
    public void testPlayer(){
        try{
            super.fileRead();
            assertTrue(this.fileContentList != null && !this.fileContentList.isEmpty());
            Players players = new Players(this.fileContentList);
            Map<String, List<String>> playersMap = players.obtainPlayersAndScores();

            assertTrue(playersMap != null && !playersMap.isEmpty());
            assertTrue(playersMap.keySet().size() == 2);
            assertTrue(!playersMap.get("Jeff").isEmpty());



        }
        catch(Exception e){
            log.error("Exception catched on test");
        }

    }
}
