package app;

import app.ChangeFiles;
import app.ErrorWindow;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

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

        final Text scenetitle = new Text("Parametry zmiany");
        scenetitle.setFont(Font.font("Podmieniacz", FontWeight.NORMAL, 16));
        gridTop.add(scenetitle, 0, 0, 2, 1);

        Label lblDirectoryName = new Label("Nazwa folderu:");
        gridCenterRight.add(lblDirectoryName, 0, 1);

        GridPane gridChoseFile = new GridPane();
        gridChoseFile.setHgap(10);
        gridChoseFile.setVgap(10);

        final Label labelSelectedDirectory = new Label("Nie wybrano folderu");
        gridChoseFile.add(labelSelectedDirectory, 1, 0);

        DirectoryChooser txtDirectoryName = new DirectoryChooser();
        Button btnOpenDirectoryChooser = new Button();
        btnOpenDirectoryChooser.setText("Open DirectoryChooser");
        btnOpenDirectoryChooser.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                String pathname = System.getProperty("user.home");
                File userDirectory = new File (pathname);
                directoryChooser.setInitialDirectory(userDirectory);
                File selectedDirectory =
                        directoryChooser.showDialog(primaryStage);

                if(selectedDirectory == null){
                    labelSelectedDirectory.setText("Nie wybrano folderu");
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

        GridPane gridEncoding = new GridPane();
        gridEncoding.setHgap(10);
        gridEncoding.setVgap(10);

        final TextField txtEncodingOther = new TextField();
        gridEncoding.add(txtEncodingOther, 1, 0);

        final ComboBox codingComboBox = new ComboBox();
        codingComboBox.getItems().addAll(
                "UTF-8",
                "Unicode",
                "ASCII",
                "Inne"

        );
        codingComboBox.valueProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue ov, String t, String t1) {
                if(!t1.equals("Inne"))
                {
                    txtEncodingOther.setText("");
                    txtEncodingOther.setDisable(true);
                } else
                    {
                        txtEncodingOther.setDisable(false);
                    }
            }
        });
        codingComboBox.getSelectionModel().selectFirst();
        Label lblCodingType = new Label("Typ kodowania:");
        gridCenterRight.add(lblCodingType, 0, 5);


        gridEncoding.add(codingComboBox, 0, 0);

        gridCenterRight.add(gridEncoding, 1, 5);

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
        final boolean[][] invalidConditions = {{false,false,false,false,false}};
        final List<File> listF = new ArrayList<File>();
        Button btnReplace = new Button();
        btnReplace.setText("Replace");
        btnReplace.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                if (labelSelectedDirectory.getText() == "Nie wybrano folderu"){
                    invalidConditions[0][1] =true;
                } else { invalidConditions[0][1] =false;}
                if (txtExtensionName.getText().equals("")){
                    invalidConditions[0][2] =true;
                } else {invalidConditions[0][2] =false;}
                if (txtInputStringOld.getText().equals("")){
                    invalidConditions[0][3] =true;
                } else {invalidConditions[0][3] =false;}

                if (invalidConditions[0][1]==false && invalidConditions[0][2]==false && invalidConditions[0][3]==false) {
                    invalidConditions[0][0]=false;
                } else { invalidConditions[0][0]=true;}
                System.out.println(invalidConditions[0][0]);


                    final ChangeFiles changeFiles = new ChangeFiles();
                    byte[] bytesOldPattern = new byte[0];
                    byte[] bytesNewPattern = new byte[0];
                    try {
                        if(codingComboBox.getValue().toString().equals("Inne"))
                        {
                            bytesOldPattern = txtInputStringOld.getText().replaceAll("\n", System.getProperty("line.separator")).getBytes(txtEncodingOther.getText());
                            bytesNewPattern = txtInputStringNew.getText().replaceAll("\n", System.getProperty("line.separator")).getBytes(txtEncodingOther.getText());
                        } else {

                            bytesOldPattern = txtInputStringOld.getText().replaceAll("\n", System.getProperty("line.separator")).getBytes(codingComboBox.getValue().toString());

                            bytesNewPattern = txtInputStringNew.getText().replaceAll("\n", System.getProperty("line.separator")).getBytes(codingComboBox.getValue().toString());
                        }
                    } catch (UnsupportedEncodingException e) {
                        System.out.println(e);
                        invalidConditions[0][0]=true;
                        invalidConditions[0][4]=true;

                    }
                    if(invalidConditions[0][0]==true){
                        ErrorWindow errorWindow = new ErrorWindow(invalidConditions);
                    }else {

                        changeFiles.ifListf(labelSelectedDirectory.getText(), listF, txtExtensionName.getText().replace(" ", ""), bytesOldPattern);


                        Stage errorWindow = new Stage();
                        errorWindow.setTitle("Potwiedzenie");


                        GridPane gridConfirmationTop = new GridPane();
                        gridConfirmationTop.setAlignment(Pos.TOP_LEFT);
                        gridConfirmationTop.setHgap(10);
                        gridConfirmationTop.setVgap(10);
                        gridConfirmationTop.setPadding(new Insets(25, 25, 25, 25));
                        Label lblError= new Label("Czy na pewno chcesz zmienić następujące " + changeFiles.getIfFileNumber() + " pliki/plików:");
                        changeFiles.setIfFileNumber(0);
                        gridConfirmationTop.add(lblError, 0, 0);
                        TextArea txtPliki = new TextArea();
                        txtPliki.setText(changeFiles.getIfResult());
                        ScrollPane sp = new ScrollPane();
                        sp.setContent(txtPliki);
                        gridConfirmationTop.add(txtPliki, 0, 1);

                        GridPane gridConfirmationBottom = new GridPane();
                        gridConfirmationBottom.setAlignment(Pos.TOP_LEFT);
                        gridConfirmationBottom.setHgap(250);
                        gridConfirmationBottom.setVgap(10);
                        gridConfirmationBottom.setPadding(new Insets(25, 25, 25, 25));

                        final Button btnAccept = new Button();
                        btnAccept.setText("OK");
                        final byte[] finalBytesOldPattern = bytesOldPattern;
                        final byte[] finalBytesNewPattern = bytesNewPattern;
                        btnAccept.setOnAction(new EventHandler<ActionEvent>() {

                            public void handle(ActionEvent event) {
                                Stage stage = (Stage) btnAccept.getScene().getWindow();

                                changeFiles.listf(labelSelectedDirectory.getText(), listF, txtExtensionName.getText().replace(" ", ""), finalBytesOldPattern, finalBytesNewPattern);
                                txtProgramOutput.appendText(changeFiles.getResult());
                                System.out.println(txtExtensionName.getText().replace(" ", ""));

                                //System.out.println(txtInputString1.getText());

                                for (File f : listF) {
                                    System.out.println(f.getName());
                                    txtProgramOutput.appendText(f.getName() + "\n");
                                }

                                stage.close();
                            }
                        });
                        gridConfirmationBottom.add(btnAccept, 0, 0);

                        final Button btnCancel = new Button();
                        btnCancel.setText("Anuluj");
                        btnCancel.setOnAction(new EventHandler<ActionEvent>() {

                            public void handle(ActionEvent event) {
                                Stage stage = (Stage) btnCancel.getScene().getWindow();

                                stage.close();
                            }
                        });
                        gridConfirmationBottom.add(btnCancel, 1, 0);
                        VBox vBox = new VBox(gridConfirmationTop,gridConfirmationBottom);
                        errorWindow.setScene(new Scene(vBox, 500, 500));
                        errorWindow.show();


                    }
            }
        });
        gridCenterRight.add(btnReplace, 0, 6);

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
