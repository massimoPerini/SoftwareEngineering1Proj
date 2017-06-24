package it.polimi.ingsw.GC_06;

import it.polimi.ingsw.GC_06.Server.Message.ActionController;
import it.polimi.ingsw.GC_06.Server.Network.LoginHub;
import it.polimi.ingsw.GC_06.Server.Network.Server;
import it.polimi.ingsw.GC_06.Server.Network.ServerOrchestrator;
import it.polimi.ingsw.GC_06.Server.Network.SocketServer;

import java.io.IOException;

/**
 * Created by massimo on 01/06/17.
 */
public class AppServer {

    public static void main( String[] args ) throws IOException {

        System.out.println("Server started...");

        ServerOrchestrator serverOrchestrator = new ServerOrchestrator();
        LoginHub.getInstance().setServerOrchestrator(serverOrchestrator);
        Server server = new SocketServer();
        serverOrchestrator.addServer(server);
        ActionController actionController = new ActionController();
        serverOrchestrator.addObserver(actionController);
        serverOrchestrator.start();

        System.out.println("Server ended...");

    }

}
