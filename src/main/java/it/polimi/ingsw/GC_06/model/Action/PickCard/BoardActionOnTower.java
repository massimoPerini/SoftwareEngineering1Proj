package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import static it.polimi.ingsw.GC_06.model.Action.PlayType.actionOnTower;

/**
 * Created by giuseppe on 5/20/17.
 */
public class BoardActionOnTower extends Action {

    private Tower tower;
    private int index;
    private Action pickCard;
    private FamilyMember familyMember;

    public BoardActionOnTower(Player player, int index, Tower tower, FamilyMember familyMember, BonusMalusHandler bonusMalusHandler) {
        super(actionOnTower, familyMember.getValue(),bonusMalusHandler);
        if (player==null || tower==null)
            throw new NullPointerException();

        this.familyMember = familyMember;
        this.index = index;
        this.tower = tower;
        this.familyMember = familyMember;
        this.pickCard = new PickCard(player, tower.getTowerFloor().get(index), tower,super.getValueAction(),super.getBonusMalusHandler());
    }

    @Override
    public void execute() {

        // farei l'eventuale modifica dell'azione qui tramite i bonus e malus
        // al momento modifichiamo il valore dell'azione che per come sono strutturate le azioni non cambia i controlli


        super.getBonusMalusHandler().filter(super.getPlayer(),super.getPlayType(),this);

        // soluzione temporanea = in questa azione di fatto posizioniamo soltanto il familiare

        //this.familyMember.getValue() = super.getValueAction();



        if (!isAllowed())
            throw new IllegalStateException();


        tower.getTowerFloor().get(index).addFamilyMember(familyMember);

        pickCard.execute();



    }

    @Override
    public boolean isAllowed() {

        /** è permessa solo quando non c'è un familiare sulla torre*/

        return pickCard.isAllowed();

    }
}
