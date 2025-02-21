package it.polimi.ingsw.GC_06;

import it.polimi.ingsw.GC_06.Server.Message.ActionController;
import it.polimi.ingsw.GC_06.Server.Network.*;
import it.polimi.ingsw.GC_06.model.Loader.Setting;

import java.io.IOException;

/**
 * Created by massimo on 01/06/17.
 * This is the main server application
 */
public class AppServer {

    public static void main( String[] args ) throws IOException {

        System.out.println("Server started...");
        Setting.getInstance().addPath("settings/bundle");

        ServerOrchestrator serverOrchestrator = new ServerOrchestrator();
        LoginHub.getInstance().setServerOrchestrator(serverOrchestrator);
        //Adding servers
        Server server = new SocketServer();
        serverOrchestrator.addServer(server);
        server = new RMIServer();
        serverOrchestrator.addServer(server);
        //
        ActionController actionController = new ActionController();
        serverOrchestrator.addObserver(actionController);
        serverOrchestrator.start();

        System.out.println("Server ended...");

    }

}
