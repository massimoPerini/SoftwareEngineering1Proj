package it.polimi.ingsw.GC_06.Client.Model;

import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.util.*;

/**
 * Created by massimo on 20/06/17.
 */

public class PlayerBonusActions extends Observable {

    private List<ResourceSet> parchmentList;
    private List<ClientTowerFloor>  pickAnotherCard;
    private List<Requirement> requirementCard;
    private Map<String, List<Integer>> prodHarvAsk;
    private ClientStateName currentState;
    private List<String> personalBonusOptions;

    public PlayerBonusActions()
    {
        parchmentList = Collections.synchronizedList(new LinkedList<>());
        pickAnotherCard = Collections.synchronizedList(new LinkedList<>());
        requirementCard = Collections.synchronizedList(new LinkedList<>());
    }

    public void setPersonalBonusOptions (List<String> personalBonusOptions)
    {
        this.personalBonusOptions = Collections.synchronizedList(personalBonusOptions);
        this.currentState = ClientStateName.CHOOSE_PERSONAL_BONUS;
        setChanged();
        notifyObservers(currentState);
    }

    public void changePowerUp(boolean powerUpQuestion)
    {
        this.currentState = ClientStateName.POWERUP;
        setChanged();
        notifyObservers(currentState);
    }

    public void changeParchment(List<ResourceSet> parchmentList)
    {
        this.parchmentList = parchmentList;
        this.currentState = ClientStateName.PARCHMENT;
        setChanged();
        notifyObservers(this.currentState);
    }

    public void setPickAnotherCard(List<ClientTowerFloor> clientTowerFloors)
    {
        this.pickAnotherCard = clientTowerFloors;
        this.currentState = ClientStateName.CHOOSE_NEW_CARD;
        setChanged();
        notifyObservers(currentState);
    }

    public void setRequirementCard(List<Requirement> requirementCard) {
        this.requirementCard = requirementCard;
        this.currentState = ClientStateName.MULTIPLE_PAYMENT;
        setChanged();
        notifyObservers(currentState);
    }

    public void setProdHarvAsk(Map<String, List<Integer>> cards) {
        this.prodHarvAsk = cards;
        this.currentState = ClientStateName.ASK_PRODHARV_CARDS;
        setChanged();
        notifyObservers(currentState);
    }

    public void changeState(ClientStateName clientStateName)
    {
        this.currentState = clientStateName;
        setChanged();
        notifyObservers(currentState);
    }

    public List<ResourceSet> getParchmentList() {
        return parchmentList;
    }

    public List<ClientTowerFloor> getPickAnotherCard() {
        return pickAnotherCard;
    }

    public List<Requirement> getRequirementCard() {
        return requirementCard;
    }

    public Map<String, List<Integer>> getProdHarvAsk() {
        return prodHarvAsk;
    }

    public List<String> getPersonalBonusOptions() {
        return personalBonusOptions;
    }

    public ClientStateName getCurrentState() {
        return currentState;
    }
}
