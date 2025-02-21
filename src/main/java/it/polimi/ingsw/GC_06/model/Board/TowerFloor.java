package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;


/**
 * Created by massimo on 12/05/17.
 */

/**
 * @author massimo
 * This class is the TowerFloor of a certain tower
 */
public class TowerFloor {

    private DevelopmentCard card;       //TODO Lista????
    private ActionPlace actionPlace;


    /*
    public TowerFloor(ArrayList<Effect> effect, int price, DevelopmentCard card )
    {
        this.actionPlace = new ActionPlaceFixed(effect , price);    
        this.card = card;
    }*/

    public TowerFloor(ActionPlace actionPlace, DevelopmentCard card)
    {
        this.actionPlace = actionPlace;
        this.card = card;
    }

    public DevelopmentCard getCard() {
        return card;
    }
    public void setCard(DevelopmentCard c)
    {
        this.card = c;
    }

    public ActionPlace getActionPlace() {
        return actionPlace;
    }

    /**
     * Adds a new FamilyMember to the TowerFloor ActionSpace
     * @param familyMember il familiare che può essere aggiunto
     */
    void addFamilyMember(FamilyMember familyMember) {
        actionPlace.addFamilyMember(familyMember);
    }

    void removeFamilyMembers()
    {
        actionPlace.removeFamilyMembers();
    }

    /**
     * return the associated card and remove it from the ActionSpace
     * @return
     */
    DevelopmentCard pickCard()
    {
        DevelopmentCard developmentCard = this.card;
        this.card = null;
        return developmentCard;
    }

    /**
     * Check if it is allowed place a FamilyMember here
     * (no check if you can afford the card, it checks only if you can place the familymember and if there is a card)
     * @param familyMember
     * @return
     */
    public boolean isAllowed(FamilyMember familyMember) {
        return actionPlace.isAllowed(familyMember) && card != null;
    }

    public boolean isEmpty()
    {
        return card==null;
    }



}
