package it.polimi.ingsw.GC_06.Client.Model;

import java.io.Serializable;

/**
 * Created by giuseppe on 6/14/17.
 */
public class ClientFamilyMember implements Serializable {
    private String player;
    private int value;
    private String color;

    public ClientFamilyMember(String player, int value, String color) {
        this.player = player;
        this.value = value;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setValue(int value) {
        this.value = value;
    }


    //*******


    public String getPlayer() {
        return player;
    }

    public int getValue() {
        return value;
    }
}
