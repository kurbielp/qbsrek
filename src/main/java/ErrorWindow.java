import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ErrorWindow {

    public ErrorWindow(boolean[][] invalidConditions)
    {
        Stage errorWindow = new Stage();
        errorWindow.setTitle("Błąd");
        GridPane gridError = new GridPane();

        gridError.setAlignment(Pos.TOP_LEFT);
        gridError.setHgap(10);
        gridError.setVgap(10);
        gridError.setPadding(new Insets(25, 25, 25, 25));
        Label lblError= new Label("Wystąpiły następujące błędy: \n");
        if (invalidConditions[0][1]==true)
            lblError.setText(lblError.getText() + "Nie wybrano folderu \n");
        if (invalidConditions[0][2]==true)
            lblError.setText(lblError.getText() + "Nie wprowadzono rozszerzenia pliku \n");
        if (invalidConditions[0][3]==true)
            lblError.setText(lblError.getText() + "Nie wprowadzono ciągu znaków (stary) \n");
        if (invalidConditions[0][4]==true)
            lblError.setText(lblError.getText() + "Nie rozpoznano typu kodowania \n");
        gridError.add(lblError, 0, 0);
        final Button btnErrorClose = new Button();
        btnErrorClose.setText("OK");
        btnErrorClose.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                // get a handle to the stage
                Stage stage = (Stage) btnErrorClose.getScene().getWindow();
                // do what you have to do
                stage.close();
            }
        });
        gridError.add(btnErrorClose, 0, 1);
        errorWindow.setScene(new Scene(gridError, 300, 300));
        errorWindow.show();
    }
}
