package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.model.Action.Actions.Action;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 5/20/17.
 * la classe implementa l'azione di mettere un familiare su un piano di una torre
 */
public class BoardActionOnTower implements Action {

    private final Tower tower;
    private final int index;
    private final Action payCard;
    private final FamilyMember familyMember;
    private final Game game;
    private final Player player;
    private final ActionType ACTION_TYPE = ActionType.BOARD_ACTION_ON_TOWER;

    public BoardActionOnTower(Player player, int floor, Tower tower, FamilyMember familyMember,Game game) {
        super();
        if (player==null || tower==null)
            throw new NullPointerException();

        this.familyMember = familyMember;
        this.index = floor;
        this.tower = tower;
        this.payCard = new PayCard(tower,floor, player, game);
        this.player = player;
        this.game = game;
    }

    /**
     * si occupa di piazzare il familiare e di lanciare la sequenza di azioni per prendere la carta
     * @throws InterruptedException
     */
    @Override
    public void execute() throws InterruptedException {

        // farei l'eventuale modifica dell'azione qui tramite i bonus e malus
        // al momento modifichiamo il valore dell'azione che per come sono strutturate le azioni non cambia i controlli


        // qui modifichiamo il valore dell'azione prima che si compia
        // immagino che questa viene eseguita se è stata superata la isAllowe
        familyMember.useIt();
        BonusMalusHandler.filter(player,ACTION_TYPE,tower.getColor(),familyMember);

        game.getGameStatus().changeState(TransitionType.ACTION_ON_TOWER);

        payCard.execute();
        tower.addFamilyMember(familyMember, index);

        // qui potrebbe essere un buon momento per eliminarli
        player.getBonusMalusSet().removeBonusMalusAction(ACTION_TYPE,tower.getColor());
        player.getBonusMalusSet().removeBonusMalusAccess(ACTION_TYPE);


        game.getGameStatus().changeState(TransitionType.END_ACTION);

    }

    /**
     * controlla che l'azione sia conforme con tutte le regole di gioco, non solo per il piano ma per tutta la torre
     * @return ritorna se l'azione si può eseguire o meno
     * @throws InterruptedException
     */
    @Override
    public boolean isAllowed() throws InterruptedException {

        /** è permessa solo quando non c'è un familiare NON NEUTRO sulla torre*/

        //Can add in PlayerBoard
        if (!familyMember.isAllowed())
        {
            return false;
        }

        /** stiamo diminuendo o aumentando il valore del familiare prima dei controlli sul piazzamento*/
        int originalValue = familyMember.getValue();
        BonusMalusHandler.filter(player,ACTION_TYPE,tower.getColor(),familyMember);

        if(!tower.isAllowed(familyMember, index))
        {
            familyMember.setValue(originalValue);
            return false;
        }
        if (!game.getGameStatus().getCurrentStatus().canConsume(TransitionType.ACTION_ON_TOWER))
        {
            familyMember.setValue(originalValue);
            return false;
        }

        boolean actionPermitted = BonusMalusHandler.filter(player,ACTION_TYPE,tower.isAllowed(familyMember, index));

        if (!actionPermitted){
            familyMember.setValue(originalValue);
            return false;}

        familyMember.setValue(originalValue);
        return payCard.isAllowed();

    }

    public void run() {
        System.out.println("ACTION STARTED");

        /*
        int originalValue = familyMember.getValue();
        boolean position = this.isAllowed();
        List<Integer> nonPermanentBonusMalus = BonusMalusHandler.filter(player,ACTION_TYPE,tower.getColor(),familyMember);
        if (BonusMalusHandler.filter(player,ACTION_TYPE,position)) {
            this.execute();
            // devo rimuovere il bonus o il malus che ho utilizzato
            for (Integer permanentBonusMalus : nonPermanentBonusMalus) {
                player.getBonusMalusSet().removeBonusMalusAction(permanentBonusMalus);
            }
            player.getBonusMalusSet().removeBonusMalusAccess(ACTION_TYPE,position);

        }
        else{
            familyMember.setValue(originalValue);
            System.out.println("ERRORE, NON POSSO ESEGUIRE L'AZIONE");
        }
        */
    }
}
