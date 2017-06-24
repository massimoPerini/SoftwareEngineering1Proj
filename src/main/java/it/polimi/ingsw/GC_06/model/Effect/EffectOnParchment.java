package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.Server.Message.Server.PopUp.MessageChooseParchment;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.Blocking;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;



/**
 * Created by giuseppe on 5/28/17.
 */
public class EffectOnParchment implements Effect, Blocking {

    private List<ResourceSet> parchments;
    private boolean different;
    private Integer choosen;
    private int quantity;
    List<Integer> alreadyChoosed;

    public EffectOnParchment(int numbers, boolean different) {
        super();
        this.quantity = numbers;
        this.different = different;
        alreadyChoosed = new LinkedList<>();
    }

    @Override
    public synchronized void execute (Player player,Game game) {
        FileLoader fileLoader = FileLoader.getFileLoader();
        parchments = Arrays.asList(fileLoader.loadParchments());

        for (int i=0;i<quantity;i++) {

            do{
                MessageChooseParchment messageChooseParchment = new MessageChooseParchment(parchments, "");
                waitAnswer(game, messageChooseParchment);
            }
            while(alreadyChoosed.contains(choosen) && different);
            player.variateResource(parchments.get(choosen));
            choosen = null;

        }
    }

    private synchronized void waitAnswer(Game game, MessageChooseParchment messageChooseParchment)
    {
        GameList.getInstance().setCurrentBlocking(game, this, messageChooseParchment);
        while (choosen == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread svegliato");
        }
    }

    @Override
    public synchronized void setOptionalParams(Object list) {
            this.choosen = (Integer) list;
            notifyAll();
    }

    @Override
    public synchronized void userLoggedOut(String user) {
        for (int i=0;i<parchments.size();i++)
        {
            if (!alreadyChoosed.contains(i))
            {
                this.choosen = i;
                notifyAll();
                return;
            }
        }
        //Non dovrebbe succedere di avere + di n privilegi del consiglio tutti diversi se n < numero totale privilegi consiglio
        this.choosen = 0;
        notifyAll();
        return;
    }

}