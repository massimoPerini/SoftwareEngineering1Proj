package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.FamilyMembers;

import it.polimi.ingsw.GC_06.Client.Model.ClientFamilyMember;
import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.Model.PlayerColors;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.AllBoards.AllBoardsView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.HeroCard.HeroCardView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.MessageCreator;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageEndTurn;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 05/07/17.
 */
public class FamilyMembersPresenter implements Observer {


    @FXML private ListView familyMembersView;
    @FXML private VBox mainView;
    @FXML private TextField powerUpValue;
    @FXML private Button pass;
    @FXML private Button hero;

    @Inject
    private MainClientModel mainClientModel;

    @Inject private MessageCreator messageCreator;
    @Inject
    PlayerColors playerColors;
    private ObservableList<ClientFamilyMember> listItems;


    @FXML
    public void initialize()
    {
        this.update(null, null);
        mainClientModel.getClientPlayerBoard(mainClientModel.getMyUsername()).addObserver(this);
        familyMembersView.setCellFactory(param -> new ListCell<ClientFamilyMember>() {
            @Override
            public void updateItem(ClientFamilyMember familyMember, boolean empty) {
                super.updateItem(familyMember, empty);
                System.out.println("Updating items...");
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try
                    {
                        Canvas canvas = new Canvas();
                        GraphicsContext gc = canvas.getGraphicsContext2D();
                    //    Field field = Class.forName("javafx.scene.paint.Color").getField(familyMember.getColor().toLowerCase());
                 //       gc.setFill(Color.web(playerColors.getPlayerColor(mainClientModel.getCurrentPlayer())));
                   //     gc.setFill(Color.RED);
                  //      gc.fillOval(5, 5, 10, 10);
                  //      setGraphic(canvas);
                        setText(String.valueOf(familyMember.getValue()));
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

/*
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if ("Orange".equals(item)) {
                            setDisable(true);
                        } else {
                            setDisable(false);
                        }
                        setText(item);
                    }

                };*/

        });

        /*
        Button button = new Button();
        button
        TextInputDialog dialog = new TextInputDialog("power up");
        dialog.setTitle("Scegli power-up");
        dialog.setHeaderText("Puoi scegliere un power-up");
        dialog.setContentText("Di quanto il power-up:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> System.out.println("Value: " + name));
        */

        powerUpValue.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    powerUpValue.setText("0");
                }
                int powerUpInt = Integer.parseInt(powerUpValue.getText());
                messageCreator.setPowerUp(Integer.parseInt(powerUpValue.getText()));
            }
        });

        familyMembersView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ClientFamilyMember>() {
            @Override
            public void changed(ObservableValue<? extends ClientFamilyMember> observable, ClientFamilyMember oldValue, ClientFamilyMember newValue) {
                int selectedIndex = familyMembersView.getSelectionModel().getSelectedIndex();
                messageCreator.setFamilyMember(selectedIndex);
            }
        });

    }


        @PostConstruct public void init()
        {
        }


    public void handlePassPressed(ActionEvent actionEvent) {
        MessageEndTurn messageEndTurn = new MessageEndTurn();
        messageCreator.setMessageClient(messageEndTurn);
    }

    public void handleShowAllBoards(ActionEvent actionEvent) {
        AllBoardsView allBoardsView = new AllBoardsView();

        Parent parent = allBoardsView.getView();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("All boards");
        stage.setScene(new Scene(parent));
        stage.show();
        stage.sizeToScene();
    }

    public void disableOnlyFamilyMembers(boolean disable)
    {
        pass.setDisable(false);
        familyMembersView.getSelectionModel().clearSelection();
        familyMembersView.setDisable(disable);
    }

    public void disableAll(boolean disable)
    {
        familyMembersView.getSelectionModel().clearSelection();
        familyMembersView.setDisable(disable);
        pass.setDisable(disable);
    }

    public void handleHeroPressed(ActionEvent actionEvent) {

        Map<String, Object> context = new HashMap<>();
        context.put("clientPlayerBoard", mainClientModel.getClientPlayerBoard(mainClientModel.getMyUsername()));

        HeroCardView heroCardView = new HeroCardView(context::get);

        /** stiamo iniziallizando una nuova schermata */
        Parent parent = heroCardView.getView();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("All boards");
        stage.setScene(new Scene(parent));
        stage.show();
        stage.sizeToScene();

    }

    @Override
    public void update(Observable o, Object arg) {
        this.listItems = FXCollections.observableList(mainClientModel.getClientPlayerBoard(mainClientModel.getMyUsername()).getFamilyMembers());
        familyMembersView.setItems(listItems);
        familyMembersView.refresh();
    }
}
