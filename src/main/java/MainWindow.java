import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Formatter;

public class MainWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(final Stage primaryStage) {



        primaryStage.setTitle("Podmieniacz");


        GridPane gridCenterRight = new GridPane();
        GridPane gridCenterLeft = new GridPane();
        GridPane gridTop = new GridPane();

        gridCenterRight.setAlignment(Pos.TOP_LEFT);
        gridCenterRight.setHgap(10);
        gridCenterRight.setVgap(10);
        gridCenterRight.setPadding(new Insets(25, 25, 25, 25));

        gridCenterLeft.setAlignment(Pos.TOP_LEFT);
        gridCenterLeft.setHgap(10);
        gridCenterLeft.setVgap(10);
        gridCenterLeft.setPadding(new Insets(25, 25, 25, 25));

        gridTop.setAlignment(Pos.TOP_LEFT);
        gridTop.setHgap(10);
        gridTop.setVgap(10);
        gridTop.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Parametry zmiany");
        scenetitle.setFont(Font.font("Podmieniacz", FontWeight.NORMAL, 16));
        gridTop.add(scenetitle, 0, 0, 2, 1);

        Label lblDirectoryName = new Label("Nazwa folderu:");
        gridCenterRight.add(lblDirectoryName, 0, 1);

        GridPane gridChoseFile = new GridPane();
        gridChoseFile.setHgap(10);
        gridChoseFile.setVgap(10);

        final Label labelSelectedDirectory = new Label("No Directory selected");
        gridChoseFile.add(labelSelectedDirectory, 1, 0);

        DirectoryChooser txtDirectoryName = new DirectoryChooser();
        Button btnOpenDirectoryChooser = new Button();
        btnOpenDirectoryChooser.setText("Open DirectoryChooser");
        btnOpenDirectoryChooser.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File userDirectory = new File ("C:/Users/dell/Desktop/TEST");
                directoryChooser.setInitialDirectory(userDirectory);
                File selectedDirectory =
                        directoryChooser.showDialog(primaryStage);

                if(selectedDirectory == null){
                    labelSelectedDirectory.setText("No Directory selected");
                }else{
                    labelSelectedDirectory.setText(selectedDirectory.getAbsolutePath());
                }
            }
        });
        gridChoseFile.add(btnOpenDirectoryChooser, 0, 0);

        gridCenterRight.add(gridChoseFile,1,1);

        Label lblExtensionName = new Label("Nazwa rozszerzenia:");
        gridCenterRight.add(lblExtensionName, 0, 2);

        final TextField txtExtensionName = new TextField();
        gridCenterRight.add(txtExtensionName, 1, 2);

        Label lblInputString1 = new Label("Ciąg znaków (stary):");
        gridCenterRight.add(lblInputString1, 0, 3);

        final TextArea txtInputStringOld = new TextArea();
        gridCenterRight.add(txtInputStringOld, 1, 3);


        Label lblInputString2 = new Label("Ciąg znaków (nowy):");
        gridCenterRight.add(lblInputString2, 0, 4);

        final TextArea txtInputStringNew = new TextArea();
        gridCenterRight.add(txtInputStringNew, 1, 4);

        //----------GRID 2--------------

        Label lblProgramOutput= new Label("Podmienione pliki:");
        gridCenterLeft.add(lblProgramOutput, 0, 0);

        final TextArea txtProgramOutput = new TextArea();
        //txtProgramOutput.setDisable(true);
        txtProgramOutput.setPrefHeight(600);
        gridCenterLeft.add(txtProgramOutput, 0, 1);
/*
        grid.add(scenetitle, 1, 0, 2, 1);

        Label lblProgramOutput = new Label("Zienione Pliki:");
        grid.add(lblInputString2, 0, 4);

        TextField txtInputString2 = new TextField();
        grid.add(txtInputString2, 1, 4);
        */

        final List<File> listF = new ArrayList<File>();
        Button btnReplace = new Button();
        btnReplace.setText("Replace");
        btnReplace.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                if (labelSelectedDirectory.getText() == "No Directory selected"){


                }
                else {
                    ChangeFiles changeFiles = new ChangeFiles();

                    changeFiles.listf(labelSelectedDirectory.getText(), listF , txtExtensionName.getText().replace(".","").replace(" ",""), txtInputStringOld.getText(), txtInputStringNew.getText());
                    txtProgramOutput.appendText(changeFiles.getResult());
                    System.out.println( txtExtensionName.getText().replace(".","").replace(" ",""));
                    //System.out.println(txtInputString1.getText());

                    for (File f : listF) {
                        System.out.println(f.getName());
                        txtProgramOutput.appendText(f.getName() + "\n");
                    }
                }
            }
        });
        gridCenterRight.add(btnReplace, 0, 5);

        ScrollPane sp = new ScrollPane();
        sp.setContent(gridCenterLeft);

        HBox hBox = new HBox();
        //hBox.setSpacing(5.0);
        //hBox.setPadding(new Insets(5,5,5,5));
        hBox.getChildren().addAll(gridCenterRight,gridCenterLeft);

        VBox vBox = new VBox(gridTop,hBox);

        Scene scene = new Scene(vBox, 1200, 800);

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
