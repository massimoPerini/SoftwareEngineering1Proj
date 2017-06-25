package it.polimi.ingsw.GC_06.Client.View;

/**
 * Created by massimo on 27/05/17.
 */
public interface CommandView extends Runnable{

    void addLocalizedText(String string);
    void flush();
    void addText(String string);
    void print();
    int getInt(int start, int end);
    int getInt(int start);
    String getString() throws InterruptedException;
    void sleep();
    void unload();

}
