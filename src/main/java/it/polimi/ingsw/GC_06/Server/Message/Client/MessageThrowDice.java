package it.polimi.ingsw.GC_06.Server.Message.Client;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;

/**
 * Created by massimo on 19/06/17.
 */
public class MessageThrowDice implements MessageClient {

    private int game;
    private String player;



    @Override
    public void execute() {
    //    Game currGame = GameList.getInstance().getGameId(game);
    //    currGame.roll();
    }

    @Override
    public void setGame(int game) {
        this.game = game;
    }

    @Override
    public void setPlayer(String player) {
        this.player = player;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public void run() {
        execute();
    }
}
