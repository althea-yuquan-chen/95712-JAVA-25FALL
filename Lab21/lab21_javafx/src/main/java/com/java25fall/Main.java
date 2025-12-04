package com.java25fall;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {
    private static NoteCollection noteCollection = new NoteCollection();

    @Override
    public void start(Stage primaryStage) throws Exception{
        // VBox vBox = new VBox();
        // Label label1 = new Label("REPLACE ME!");
        // vBox.getChildren().addAll(label1);
        // Label label2 = new Label("REPLACE ME TOO!");
        // vBox.getChildren().addAll(label2);
        // Scene scene = new Scene(vBox, 200, 50);
        // primaryStage.setScene(scene);
        // primaryStage.setTitle("Lab 21");
        // primaryStage.show();

        // first note
        Note first = noteCollection.getNoteByNumber(1);

        // Label nameLabel = new Label("Name:");
        // TextField nameField = new TextField(name1);

        // HBox hboxname = new HBox(10);
        // hboxname.getChildren().addAll(nameLabel, nameField);

        // Scene scene = new Scene(hbox, 300, 50);
        // primaryStage.setScene(scene);
        // primaryStage.setTitle("Lab 21-Name");
        // primaryStage.show();

        // // Problem 5
        // HBox nameBox = makeBox("Name:", first.getName());
        // HBox bodyBox = makeBox("Body:", first.body);
        // VBox vBox2 = new VBox(10);
        // vBox2.getChildren().addAll(nameBox, bodyBox);

        // Scene scene2 = new Scene(vBox2, 300, 120);
        // primaryStage.setScene(scene2);
        // primaryStage.setTitle("Lab 21 - Name & Body");
        // primaryStage.show();

        // // Problem 6
        // VBox bigBox = new VBox(20);
        // for (int i = 1; i <= noteCollection.getCount(); i++) {

        //     Note n = noteCollection.getNoteByNumber(i);

        //     HBox nameBox = makeBox("Name:", n.getName());
        //     HBox bodyBox = makeBox("Body:", n.body);

        //     VBox vbox = new VBox(10);
        //     vbox.getChildren().addAll(nameBox, bodyBox);

        //     bigBox.getChildren().add(vbox);
        // }
        // Scene scene3 = new Scene(bigBox, 400, 400);
        // primaryStage.setScene(scene3);
        // primaryStage.setTitle("Lab 21 - Step 6");
        // primaryStage.show();

        // Problem 7
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(10);

        for (int i = 1; i <= noteCollection.getCount(); i++) {

            Note n = noteCollection.getNoteByNumber(i);

            HBox nameBox = makeBox("Name:", n.getName());
            HBox bodyBox = makeBox("Body:", n.body);

            VBox vbox = new VBox(10);
            vbox.getChildren().addAll(nameBox, bodyBox);

            int col = (i + 1) % 2;
            int row = (i + 1) / 2;

            // Add to grid
            grid.add(vbox, col, row);
        }

        Scene scene4 = new Scene(grid, 600, 400);
        primaryStage.setScene(scene4);
        primaryStage.setTitle("Lab 21 - Step 7");
        primaryStage.show();

    }

    // Problem 5
    public static HBox makeBox(String labelName, String what) {
        Label label = new Label(labelName);
        TextField textField = new TextField(what);
        HBox hbox = new HBox( );
        hbox.setPadding(new Insets(5,10,5,10));
        hbox.getChildren().addAll(label, textField);
        return hbox;
    }


    public static void main(String[] args) {
        noteCollection.readNotes("notes.txt");

        // Problem 3
        // Uncomment this to test note reading
        // ArrayList<Note> a = noteCollection.getAllNotes();
        // for (Note note: a) {
        //     System.out.println(note);
        // }

        launch(args);
    }
}
