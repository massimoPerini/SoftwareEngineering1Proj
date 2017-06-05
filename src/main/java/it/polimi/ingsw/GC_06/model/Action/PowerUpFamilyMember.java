package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import static it.polimi.ingsw.GC_06.model.Action.PlayType.powerUp;

/**
 * Created by massimo on 26/05/17.
 */
public class PowerUpFamilyMember extends Action{

    private Player player;
    private FamilyMember familyMember;
    private ResourceSet variateResources;

    public PowerUpFamilyMember(Player player, FamilyMember familyMember, ResourceSet variateResources, int variateFamilyMember, BonusMalusHandler bonusMalusHandler) {
        super(powerUp, variateFamilyMember,bonusMalusHandler);
        this.player = player;
        this.familyMember = familyMember;
        this.variateResources = variateResources;
    }

    @Override
    public void execute() {

        player.variateResource(variateResources);
        familyMember.variateValue(super.getValueAction());

    }

    @Override
    public boolean isAllowed() {

        return ! player.getResourceSet().isIncluded(variateResources);

    }
}
