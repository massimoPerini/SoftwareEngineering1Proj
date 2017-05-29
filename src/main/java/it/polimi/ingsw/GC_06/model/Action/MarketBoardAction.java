package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.model.Board.Market;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by giuseppe on 5/20/17.
 */
public class MarketBoardAction extends BoardAction {

    private Player player;
    private Market component;
    int index;


    public MarketBoardAction(Player player, FamilyMember familyMember, int index, Market component, int value) {
        super(familyMember, value);
    	this.player = player;
        this.index = index;
        this.component = component;
    }

    @Override
    public void execute() {

        component.addFamilyMember(getFamilyMember(), index);
        List<Effect> effects = component.getEffect(index);
        //facciamo un ciclo
        for(Effect effect : effects){
            effect.execute(player);
        }
    }
/**
    public void setPlayer(Player player) {
        this.player = player;
    }*/

    @Override
    public boolean isAllowed() {
        return component.isAllowed(getFamilyMember(), index) ;
    }

}
