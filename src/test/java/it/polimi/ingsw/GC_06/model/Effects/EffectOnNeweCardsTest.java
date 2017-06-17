package it.polimi.ingsw.GC_06.model.Effects;

import it.polimi.ingsw.GC_06.model.Board.TowerFloor;
import it.polimi.ingsw.GC_06.model.Effect.EffectOnNewCards;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.StateName;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by gabri on 08/06/2017.
 */
public class EffectOnNeweCardsTest {
    private List<TowerFloor> selectableCards;
    private EffectOnNewCards effectOnNewCards;
    private Player player;
    private Game game;

    @Before
    public void setUp() throws IOException {
        game = new Game();
        game.addPlayer("gabriele");
        player = game.getGameStatus().getPlayers().get("gabriele");
        selectableCards = new ArrayList<>();
        selectableCards = game.getBoard().getTowers().get("GREEN").getTowerFloor();
        effectOnNewCards = new EffectOnNewCards(selectableCards);
    }

    @Test
    public void correctTransition() {
        game.getGameStatus().changeState(TransitionType.ACTION_ON_TOWER);
        game.getGameStatus().changeState(TransitionType.PAY_CARD);
        game.getGameStatus().changeState(TransitionType.PICK_CARD);
        effectOnNewCards.execute(player, game);
        assertTrue(game.getGameStatus().getCurrentStatus().getID()== StateName.CHOOSING_CARD);
    }

    /*@Test //(expected = IllegalStateException.class)
    public void incorrectApplication() {
        game.getGameStatus().changeState(TransitionType.ACTION_ON_TOWER);
        game.getGameStatus().changeState(TransitionType.PAY_CARD);
        effectOnNewCards.execute(player, game);
    }*/
}
