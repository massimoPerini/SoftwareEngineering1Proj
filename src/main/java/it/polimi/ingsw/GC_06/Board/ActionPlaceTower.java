package it.polimi.ingsw.GC_06.Board;

import java.util.ArrayList;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.playerTools.Effect;

public class ActionPlaceTower extends ActionPlace {

	public ActionPlaceTower(ArrayList<Effect> effect, int costo) {
		super(effect, costo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isOccupied(FamilyMember member) {
		if (isValidColor(member) && this.members.size() < maxFamiliari) {
			return false;
		}
		else return true;
	}

	

}
