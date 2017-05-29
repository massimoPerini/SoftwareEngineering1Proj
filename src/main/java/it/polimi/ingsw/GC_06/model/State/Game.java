package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Board.Board;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Dice.DiceColor;
import it.polimi.ingsw.GC_06.model.Dice.DiceSet;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by massimo on 27/05/17.
 */
public class Game {

    private Board board;
    private DiceSet diceSet;
    private DevelopmentCard[] developmentCards;
    private static Game game;
    private int maxPlayers;
    private GameStatus gameStatus;

    //TODO TO REMOVE
    public static void clearForTesting()
    {
        game=null;
    }

    private Game() throws IOException {
        FileLoader f = FileLoader.getFileLoader();
        board = f.loadBoard();
        developmentCards = f.loadCards();
        diceSet = new DiceSet();
        maxPlayers = 2;
        gameStatus = new GameStatus();

        for (int i=0;i<board.getTowers().size();i++)
        {
            board.getTowers().get(i).setCards(new ArrayList<>(Arrays.asList(developmentCards)));
            board.getTowers().get(i).shuffle();
        }
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        if (gameStatus.getPlayers().size() > maxPlayers)
            throw new IllegalStateException();
        this.maxPlayers = maxPlayers;
    }

    public static Game getInstance(){
        if (game==null) {
            try {
                game = new Game();
            }catch (Exception e)
            {
                System.out.println("Game class: error reading a file");
            }
        }
        return game;
    }


    public void addPlayer (String p)
    {
        if (p==null)
            throw new NullPointerException();
        if (gameStatus.getPlayers().size() >= maxPlayers)
            throw new IllegalStateException();
        this.createFamilyMembers(true, p);
        Player player = new Player(p, this.createFamilyMembers(true, p));
        gameStatus.addPlayer(player);
    }

    private FamilyMember[] createFamilyMembers(boolean zeroFamiliar, String playerID)
    {
        int i=0;

        if (zeroFamiliar)
            i=1;

        FamilyMember[] familyMembers = new FamilyMember[DiceColor.values().length+i];

        for (i=0;i<DiceColor.values().length;i++) {
            familyMembers[i] = new FamilyMember(DiceColor.values()[i].name(), playerID);
            diceSet.getDices()[i].addObserver(familyMembers[i]);
        }
        if (zeroFamiliar)
            familyMembers[i]=new FamilyMember("",playerID);
        return familyMembers;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void roll()
    {
        diceSet.roll();
    }

}
