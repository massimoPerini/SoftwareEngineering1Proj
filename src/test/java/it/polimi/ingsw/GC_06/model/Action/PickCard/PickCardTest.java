package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.Server.Network.ServerOrchestrator;
import it.polimi.ingsw.GC_06.model.State.DefaultEventManager;
import it.polimi.ingsw.GC_06.model.State.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by massimo on 06/06/17.
 */
public class PickCardTest {

    private Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game(1);
        game.addPlayer("massimo");
        game.addPlayer("pinco");
        game.start(new DefaultEventManager(new ServerOrchestrator(), game));
    }


    @Test
    public void pickCard()
    {
    /*    Player player = game.getCurrentPlayer();
        Board board = game.getBoard();
        DevelopmentCard pickingCard = board.getTowers().get(0).getTowerFloor().get(0).getCard();
        Tower tower = board.getTowers().get(0);
        Action pickCard = new PickCard(player,tower, tower.getTowerFloor().get(0), 10);
        pickCard.execute();
        assertTrue(player.getPlayerBoard().getDevelopmentCards().size() == 1);
        assertTrue(player.getPlayerBoard().getDevelopmentCards().get(0) == pickingCard);*/
    }



    @After
    public void tearDown() throws Exception {

    }

}