package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;
import java.util.Observable;

/**
 * Created by massimo on 13/06/17.
 */
public abstract class Server extends Observable {
    abstract void start();
    abstract boolean startGame(@NotNull Map<String, Player> players, int id);
    abstract boolean isPlayerManaged(@NotNull String player);
    abstract void sendMessageToPlayer(@NotNull String player, @NotNull Object o) throws IOException;
    abstract void sendMessageToGame(int game, @NotNull Object o) throws IOException;
    abstract void stop() throws IOException;
}
