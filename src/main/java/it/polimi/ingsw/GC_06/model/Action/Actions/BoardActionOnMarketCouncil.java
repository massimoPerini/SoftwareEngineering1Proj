package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.model.Board.MarketAndCouncil;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;

/**
 * Created by massimo on 06/06/17.
 */
public class BoardActionOnMarketCouncil implements Action, Runnable {

    private final MarketAndCouncil marketAndCouncil;
    private final int index;
    private final FamilyMember familyMember;
    private final ExecuteEffects executeEffects;
    private Player player;
    private Game game;
    private ActionType actionType;

    public BoardActionOnMarketCouncil(MarketAndCouncil marketAndCouncil, int index, FamilyMember familyMember, Player player,ActionType actionType)
    {
        super();
        this.actionType = actionType;
        if (marketAndCouncil==null || familyMember==null || player==null) {
            throw new NullPointerException();
        }

        this.marketAndCouncil = marketAndCouncil;
        this.index = index;
        this.familyMember = familyMember;
        List<Effect> effectList = marketAndCouncil.getEffects(index);

        if (effectList==null)
            throw new NullPointerException();


        this.executeEffects = new ExecuteEffects(effectList, player,game);
    }

    @Override
    public void execute() {

        game.getGameStatus().changeState(TransitionType.ACTION_ON_MARKETCOUNSIL);

        marketAndCouncil.addFamilyMember(familyMember, index);
        executeEffects.execute();

        game.getGameStatus().changeState(TransitionType.END_ACTION);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean isAllowed() {
        boolean value = marketAndCouncil.isAllowed(familyMember, index) && executeEffects.isAllowed() && game.getGameStatus().getCurrentStatus().canConsume(TransitionType.ACTION_ON_MARKETCOUNSIL);
        BonusMalusHandler.filter(player,actionType,value);
        return value;
    }

    @Override
    public void run() {
        if (isAllowed())
            execute();
    }
}
