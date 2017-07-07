package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageEndTurn;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageMarketCouncil;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageProdHarv;

import java.util.concurrent.Future;

/**
 * Created by massimo on 19/06/17.
 */
public class UserActionViewController implements ViewPresenterCLI {

    private final MainClientModel mainClientModel;
    private final ClientNetworkOrchestrator clientNetworkOrchestrator;
    private Future future;
    private final CommandView commandView;


    public UserActionViewController(MainClientModel mainClientModel, ClientNetworkOrchestrator clientNetworkOrchestrator)
    {
        this.mainClientModel = mainClientModel;
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
        this.commandView = new CmdView();
    }

    @Override
    public void viewWillAppear() throws InterruptedException {
    //    ExecutorService executor = Executors.newCachedThreadPool();
    //    this.future = executor.submit(this);

        System.out.println("CLIENTBOARDGAME INVOKED");
        boolean ok = false;
        while(!ok) {
            commandView.addLocalizedText("E' il tuo turno. Inserire s per mostrare la board o le board dell'utente, d per tirare i dadi, p per prendere una carta," +
                    "Se vuoi eseguire il raccolto o la produzione scrivi prod, se vuoi posizionare il familiare nello aspazio consiglio/mercato scrivi m "+ "\n scrivi Hero Card se la vuoi attivare");
            String input = commandView.getString();

            if (input.equals("s")) {
                BoardStatusViewController boardStatusViewController = new BoardStatusViewController(mainClientModel.getClientBoardGame(), mainClientModel.getClientPlayerBoard());
                boardStatusViewController.viewWillAppear();
            }

          /*  if (input.equals("d")) {
            }*/

            if (input.equals("p")) {
                TutorialPickCard tutorialPickCard = new TutorialPickCard(commandView, mainClientModel.getClientBoardGame(), mainClientModel.getClientPlayerBoard(mainClientModel.getMyUsername()), clientNetworkOrchestrator);        //Probabilmente l'interfaccia è inutile
                tutorialPickCard.viewWillAppear();
                ok = true;
            }

            if(input.equals("prod")){

                commandView.addLocalizedText("Dammi i dati");
                String[] inp = commandView.getString().split(" ");
                MessageProdHarv messageProdHarv = new MessageProdHarv(Integer.parseInt(inp[0]),Integer.parseInt(inp[1]),Integer.parseInt(inp[2]),Integer.parseInt(inp[3]));
                clientNetworkOrchestrator.send(messageProdHarv);
                ok = true;
            }
            if (input.equals("l"))
            {
                MessageEndTurn messageEndTurn = new MessageEndTurn();
                clientNetworkOrchestrator.send(messageEndTurn);
                ok = true;
            }
            if (input.equals("m"))
            {
                commandView.addLocalizedText("slotMarketCouncil indiceSlot familiare powerup");
                String[] inp = commandView.getString().split(" ");
                MessageMarketCouncil messageMarketCouncil = new MessageMarketCouncil(Integer.parseInt(inp[0]),Integer.parseInt(inp[1]),Integer.parseInt(inp[2]), Integer.parseInt(inp[3]));
                clientNetworkOrchestrator.send(messageMarketCouncil);
                ok = true;
            }

            if(input.equals("Hero Card")){
                // String[] inp = commandView.getString().split(" ");
                PlayHeroCardViewController playHeroCardViewController = new PlayHeroCardViewController(mainClientModel.getClientPlayerBoard(mainClientModel.getMyUsername()), clientNetworkOrchestrator);
                playHeroCardViewController.viewWillAppear();
                ok = true;
            }
        }

    }

    public void run(){

    }
}
