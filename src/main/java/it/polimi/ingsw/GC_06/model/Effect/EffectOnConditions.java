package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 5/28/17.
 */
public class EffectOnConditions implements Effect{

    private Resource conditionalResource; /** può essere una carta o una Resource */
    private String cardColour;
    private double multiplier;
    private Resource trasferredResource;

    public EffectOnConditions(Resource conditionalResource, double multiplier, Resource trasferredResource,String cardColour) {
        this.conditionalResource = conditionalResource;
        this.multiplier = multiplier;
        this.trasferredResource = trasferredResource;
    }

    @Override
    public void execute(Player player,Game game) {

       int conditionalValue =  converter(player);

       int transferValue = (int) (conditionalValue * multiplier);

       ResourceSet resourceSet = new ResourceSet();
       resourceSet.variateResource(trasferredResource,transferValue);
       player.variateResource(resourceSet);
    }

    private int converter(Player player){

        int conditionalValue;

        if(this.cardColour == null && conditionalResource == null){
            throw new IllegalStateException();
        }
        if(this.cardColour == null){
            return conditionalValue = player.getResourceSet().getResourceAmount(conditionalResource);
        }
        else
            return conditionalValue = player.getPlayerBoard().getDevelopmentCards(cardColour).size();

    }

}
