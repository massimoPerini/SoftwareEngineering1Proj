package it.polimi.ingsw.GC_06.model.BonusMalus;

/**
 * Created by giuseppe on 6/12/17.
 * bonus malus che modifica la possibilità di accesso di un giocatore ad uno spazio
 */
public class BonusMalusOnAccess {

    private ActionType actionType;
    private boolean modifiedAccess;
    private boolean permanent;
    private boolean used = false;

    public BonusMalusOnAccess(ActionType actionType, boolean modifiedAccess, boolean permanent) {
        this.actionType = actionType;
        this.modifiedAccess = modifiedAccess;
        this.permanent = permanent;
    }

    public boolean  modify(boolean result){
        result = modifiedAccess;
        return result;
    }

    public boolean isAllowed(ActionType actionType,boolean result){

        if(result == modifiedAccess){
            return false;
        }

        if(this.actionType.equals(ActionType.GENERAL)||this.actionType.equals(actionType)){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isModifiedAccess() {
        return modifiedAccess;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isUsed() {
        return used;
    }
}
