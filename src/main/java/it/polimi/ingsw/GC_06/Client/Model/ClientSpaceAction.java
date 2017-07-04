package it.polimi.ingsw.GC_06.Client.Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 * Created by giuseppe on 6/14/17.
 */
public class ClientSpaceAction extends Observable {
    private List<ClientFamilyMember> familyMembers;

    public ClientSpaceAction()
    {
        familyMembers = new LinkedList<>();
    }

    synchronized void addClientFamilyMember(ClientFamilyMember clientFamilyMember) {
        familyMembers.add(clientFamilyMember);
    }

    synchronized void reset() {
        familyMembers = new LinkedList<>();
        //notify
    }

    //*******

    public synchronized List<ClientFamilyMember> getFamilyMembers() {
        return familyMembers;
    }
}
