package it.polimi.ingsw.GC_06.model.Action.EndGame;

import it.polimi.ingsw.GC_06.model.State.Game;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by giuseppe on 7/3/17.
 * renak dei giocaotori in una partita, ne esiste una sola istanza
 */
public class Ranking {


    private static Ranking instance = null;

    private Ranking(){

    }

    /**
     * getter della classe Singleton
     * @return ritorna l'istanza della classe
     */

    public static  Ranking getInstance(){
        if(instance == null){
            instance = new Ranking();
        }
        return instance;
    }


    /**
     *
     * @param game gioco di cui si sta eseguendo il ranking
     * @return ritorna una lista di statistiche legate ai giocatori
     */
    public List<PersonalStatistics> produceCurrentGameRanking(Game game){

        List<PersonalStatistics> currentRanking = new LinkedList<>();
        Set<String> players = game.getGameStatus().getPlayers().keySet();

        for (String player : players) {
            PersonalStatistics personalStatistic = game.getGameStatus().getPlayers().get(player).createPersonalStatistics();
            currentRanking.add(personalStatistic);
        }

        return currentRanking;
    }

    public void changeGlobalRanking(){

    }
}
