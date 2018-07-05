package app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class ConfirmationWindow {
    public void ConfirmationWindow(ArrayList<File> listf, final String results){
        boolean acceptation;
        Stage errorWindow = new Stage();
        errorWindow.setTitle("Potwiedzenie");


        GridPane gridConfirmationTop = new GridPane();
        gridConfirmationTop.setAlignment(Pos.TOP_LEFT);
        gridConfirmationTop.setHgap(10);
        gridConfirmationTop.setVgap(10);
        gridConfirmationTop.setPadding(new Insets(25, 25, 25, 25));
        Label lblError= new Label("Czy na pewno chcesz zmienić następujące " + "rodo" + " plików:");

        gridConfirmationTop.add(lblError, 0, 0);
        TextArea txtPliki = new TextArea();
        txtPliki.setText(results);
        ScrollPane sp = new ScrollPane();
        sp.setContent(txtPliki);
        gridConfirmationTop.add(lblError, 0, 1);

        GridPane gridConfirmationBottom = new GridPane();
        gridConfirmationBottom.setAlignment(Pos.TOP_LEFT);
        gridConfirmationBottom.setHgap(10);
        gridConfirmationBottom.setVgap(10);
        gridConfirmationBottom.setPadding(new Insets(25, 25, 25, 25));

        final Button btnAccept = new Button();
        btnAccept.setText("OK");
        btnAccept.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                Stage stage = (Stage) btnAccept.getScene().getWindow();
                // do what you have to do
                stage.close();
            }
        });
        gridConfirmationBottom.add(btnAccept, 1, 0);

        final Button btnCancel = new Button();
        btnCancel.setText("Anuluj");
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                Stage stage = (Stage) btnCancel.getScene().getWindow();
                // do what you have to do
                stage.close();
            }
        });
        gridConfirmationBottom.add(btnCancel, 1, 0);
        VBox vBox = new VBox(gridConfirmationTop,gridConfirmationBottom);
        errorWindow.setScene(new Scene(vBox, 300, 500));
        errorWindow.show();

    }

}
