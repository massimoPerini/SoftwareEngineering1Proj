package it.polimi.ingsw.GC_06;

import it.polimi.ingsw.GC_06.Board.ActionPlaceFixed;
import it.polimi.ingsw.GC_06.Board.Board;
import it.polimi.ingsw.GC_06.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.Loader.FileLoader;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{

    //Main controller
    //http://stackoverflow.com/questions/34712885/how-to-load-an-external-properties-file-from-a-maven-java-project
    //http://www.avajava.com/tutorials/lessons/how-do-i-read-a-properties-file-with-a-resource-bundle.html

    public static void main( String[] args ) throws IOException {

        FileLoader f = FileLoader.getFileLoader();
        DevelopmentCard [] developmentCards = f.loadCards();
        Board board = f.loadBoard();

        //TODO Implement FIX: https://futurestud.io/tutorials/how-to-deserialize-a-list-of-polymorphic-objects-with-gson
        //TODO http://stackoverflow.com/questions/19588020/gson-serialize-a-list-of-polymorphic-objects

        if (board.getTowers().get(0).getTowerFloors().get(0).getActionPlace() instanceof ActionPlaceFixed){
            System.out.println("Tutto ok, ho capito l'effetiva implementazione");
        }
        else{
            System.out.println("Errore, penso che sulla torre ci sia un actionplace generico");
        }
/*
        Game gioco = Game.getInstance();
        CommandView view = new CommandView();
        TerminalControl c = new TerminalControl(gioco, view);

        FxLoader fxLoader = new FxLoader();
        FxControl fxControl = new FxControl();
        fxLoader.setFxControl(fxControl);
        fxLoader.show("");
    //    view.startMessage();

        FileLoader f = FileLoader.getFileLoader();
    //    f.writeResources();
        f.loadResources();
*/

    }
}
