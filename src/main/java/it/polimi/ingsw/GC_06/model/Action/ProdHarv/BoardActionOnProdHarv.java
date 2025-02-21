package it.polimi.ingsw.GC_06.model.Action.ProdHarv;

import it.polimi.ingsw.GC_06.model.Action.Actions.Action;
import it.polimi.ingsw.GC_06.model.Action.Actions.ExecuteEffects;
import it.polimi.ingsw.GC_06.model.Board.ProdHarvZone;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;


/**
 * Created by massimo on 01/06/17.
 * la classe implementa l'azione da parte di un giocatore di mettere un familiare su una zona produzione o una zona raccolto
 */
public class BoardActionOnProdHarv implements Action {

    private final int index;
    private final Player player;
    private final ProdHarvZone prodHarvArea;
    private final FamilyMember familyMember;
    private final StartProdHarv startProdHarv;
    private Game game;
    private ActionType actionTypeStartProdHarv;
    private ActionType actionType;

    /**
     *
     * @param player The player that invoked the action
     * @param index Index of the field of the production/harvest area
     * @param prodHarvArea The production/harvest area
     *
     * @param askUserCardFilter The function that selects the cards that we have to ask
     * @param familyMember The family member placed
     */

    public BoardActionOnProdHarv(Player player, int index, ProdHarvZone prodHarvArea, ActionType actionType, ActionType startProdHarvType, AskUserCard askUserCardFilter , FamilyMember familyMember, Game game)
    {
        super();
        if (player == null || prodHarvArea == null || familyMember == null || askUserCardFilter==null || actionType == null)
            throw new NullPointerException();
        this.actionType = actionType;
        this.prodHarvArea = prodHarvArea;
        this.actionTypeStartProdHarv = startProdHarvType;
        this.player = player;
        this.game = game;
        this.index = index;
        this.familyMember = familyMember;
        this.startProdHarv = new StartProdHarv(actionTypeStartProdHarv, askUserCardFilter ,familyMember.getValue(), player, game);
    }

    /**
     * Adds the family member and starts the prod/harv
     */

    /**
     * il metodo si occupa di utilizzare il familiare e di gestire gli effetti legati agli spazi azione
     * @throws InterruptedException
     */
    @Override
    public void execute() throws InterruptedException {

    //    game.getGameStatus().changeState(TransitionType.ACTION_ON_PRODHARV);
        familyMember.useIt();
        game.getGameStatus().changeState(TransitionType.ACTION_ON_PRODHARV);


        List<Effect> effects = prodHarvArea.getEffect(index);
        ExecuteEffects executeEffects = new ExecuteEffects(effects, player, game);
        executeEffects.execute();


        BonusMalusHandler.filter(player,actionType,"",familyMember);

        startProdHarv.setValue(familyMember.getValue());
        player.getBonusMalusSet().removeBonusMalusAction(actionType,null);

        startProdHarv.execute();


        prodHarvArea.addFamilyMember(familyMember, index);


        game.getGameStatus().changeState(TransitionType.END_ACTION);


    }


    /**
     *
     * @return ritorna se l'azione può essere eseguita
     * @throws InterruptedException
     */
    @Override
    public boolean isAllowed() throws InterruptedException {


        Player playerClone = new Player(player);
        playerClone.getBonusMalusSet().joinSet(player.getBonusMalusSet());

        for (Effect effect : prodHarvArea.getActionPlaces().get(index).getEffects()) {
            effect.execute(playerClone,game);
        }

        int originalValue = familyMember.getValue();

        BonusMalusHandler.filter(playerClone,actionType,"",familyMember);

        if(familyMember.getValue() < 1){
            return false;
        }

        startProdHarv.setValue(familyMember.getValue());

       if( !startProdHarv.isAllowed()){
           familyMember.setValue(originalValue);
           return false;
       }



        return familyMember.isAllowed() && prodHarvArea.isAllowed(familyMember, index) &&  game.getGameStatus().getCurrentStatus().canConsume(TransitionType.ACTION_ON_PRODHARV);

    }



    //TODO BONUSMALUS
/*
    public void run() {

        int originalValue = familyMember.getValue();
        boolean position = this.isAllowed();
        if (BonusMalusHandler.filter(player,actionType,position)) {
            execute();
            player.getBonusMalusSet().removeBonusMalusAccess(actionType,position);
            //
        }
        else{
            familyMember.setValue(originalValue);
            System.out.println("AZIONE NON CONSENTITA");
        }
    }
    */
}
