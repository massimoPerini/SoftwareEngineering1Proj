package it.polimi.ingsw.GC_06.Server.Message.Client.PopUp;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.Action;
import it.polimi.ingsw.GC_06.model.Action.Actions.HeroCardAction;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;

/**
 * Created by giuseppe on 7/5/17.
 */
public class PlayerHeroCardChoices implements MessageClient{

    private int game;
    private String player;
    private List<Integer> heroCardChoices;

    public PlayerHeroCardChoices(List<Integer> heroCardChoices) {
        this.heroCardChoices = heroCardChoices;
    }

    @Override
    public void execute() {

        Game currentGame = GameList.getInstance().getGameId(game);
        Player currentPlayer = currentGame.getGameStatus().getPlayers().get(player);

        Action heroCardAction = new HeroCardAction(currentGame,currentPlayer,heroCardChoices, ActionType.PLAY_HERO_CARD);

        try{
            if(heroCardAction.isAllowed()){
                heroCardAction.execute();
            }
            else{
                currentGame.getGameStatus().changeState(TransitionType.ERROR);
            }

        }catch (Exception e){

            return;

        }

    }

    @Override
    public void setGame(int game) {
        this.game = game;
    }

    @Override
    public void setPlayer(String player) {
        this.player = player;
    }

    @Override
    public String getPlayer() {
        return null;
    }



}
