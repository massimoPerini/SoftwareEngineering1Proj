package it.polimi.ingsw.GC_06.Network;

import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by massimo on 13/06/17.
 */
public class LoginHub {

    private List<String> totPlayers = new ArrayList<>();
    private List<String> loggedPlayers = new ArrayList<>();
    private int playerCounter = 0;

    public static LoginHub instance = new LoginHub();

    private LoginHub() {

    }

    public static LoginHub getInstance() {
        return instance;
    }

    public void addUser(String user) throws IllegalArgumentException, IOException {
        int i = 0;
        /** qui faccio il controllo anche sul fatto che un giocatore non può essere registrato su più partite */
        try{
            access(user);

            loggedPlayers.add(user);
            totPlayers.add(user);

            if(loggedPlayers.size() == Integer.parseInt(Setting.getInstance().getProperty("max_player"))){
                Game game = new Game();
                for(String username : loggedPlayers){
                    game.addPlayer(username);
                }
                loggedPlayers = new ArrayList<>();
            }

        }catch(IllegalStateException e){
            e.getMessage();
        }
    }

    private void access(String user) {

        /** la lista contiene l'elenco di tutti i giocatori effettivamente registrati a tutti i giochi  quindi */

        if (totPlayers.contains(user)) {
            throw new IllegalStateException("Username already present!");

        }
    }

}
