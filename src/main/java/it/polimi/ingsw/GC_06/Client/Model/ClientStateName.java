package it.polimi.ingsw.GC_06.Client.Model;

/**
 * Created by massimo on 15/06/17.
 * this class contains all the possible states of the client
 */
public enum ClientStateName {
    START, LOGIN, LOGGED, GAME_INIT, WAIT_TURN, MY_TURN, PARCHMENT, CHOOSE_NEW_CARD, MULTIPLE_PAYMENT, ASK_PRODHARV_CARDS, POWERUP, EXCOMMUNICATION, ACTION_FINISHED, CHOOSE_PERSONAL_BONUS,
    END_GAME, USER_DISCONNECT, ERROR
}
