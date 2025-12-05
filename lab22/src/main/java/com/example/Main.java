package com.example;// Make sure to rename this file from Main-22.java to Main.java

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.io.File;

public class Main extends Application {
    private static NoteCollection noteCollection = new NoteCollection();

    // This is the current note counter
    private static int number = 1;

    // Buttons
    private static Button nextButton = new Button("Next"),
            clearButton = new Button("Clear"),
            newNoteButton = new Button("Add New Note");

    // Labels
    private static Label nameLabel = new Label("Name"),
            bodyLabel = new Label("Body"),
            statusLabel = new Label("OK");

    // Text fields
    private static TextField nameField = new TextField(),
            bodyField = new TextField();

    // HBoxes
    private static HBox nameBox = new HBox(),
            bodyBox = new HBox(),
            buttonBox = new HBox();

    // VBoxes
    private static VBox noteBox = new VBox(),
            bottomBox = new VBox();

    // Menu item, menu, menu bar, file chooser
    private static MenuItem openFile = new MenuItem("Open");
    private static Menu menu = new Menu("File");
    private static MenuBar menuBar = new MenuBar();
    private static FileChooser fileChooser = new FileChooser();

    // The main layout manager
    private static BorderPane borderPane = new BorderPane();

    // The main scene - new this up later
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        // Create the note display
        setupNote();

        // Create the buttons and status field
        setupBottom();

        // Uncomment the next line after getting the menu and file 
        // chooser set up:
        setUpMenu(primaryStage);

        // Add the boxes to the layout manager
        borderPane.setCenter(noteBox);
        borderPane.setBottom(bottomBox);

        // Uncomment the next line after getting the menu working:
        borderPane.setTop(menuBar);
        scene = new Scene(borderPane, 300, 300);

        // Add your code here to handle the <escape> key
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE) {
                    primaryStage.close();
                }
            }
        });

        primaryStage.setTitle("Lab 15");
        // Adjust the size if needed
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Set up the note box
    public static void setupNote() {
        nameLabel.setMinWidth(80.0);
        bodyLabel.setMinWidth(80.0);
        nameBox.getChildren().addAll(nameLabel, nameField);
        bodyBox.getChildren().addAll(bodyLabel, bodyField);
        noteBox.getChildren().addAll(nameBox, bodyBox);
    }

    // Set up the button box and the status field in the bottom box
    public static void setupBottom() {
        buttonBox.getChildren().addAll(nextButton, clearButton, newNoteButton);
        bottomBox.getChildren().addAll(buttonBox, statusLabel);

        // Put the clearButton event handler code here:
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearFields();
                statusLabel.setText("OK");
            }
        });


        // Put the nextButton event handler code here:
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Check if there are any notes
                if (noteCollection.getCount() > 0) {
                    // Get the note by number
                    Note currentNote = noteCollection.getNoteByNumber(number);

                    // Populate the fields with note data
                    nameField.setText(currentNote.getName());
                    bodyField.setText(currentNote.body);
//                    statusLabel.setText("OK");

                    // Increment number (cycle back to 1 if at max)
                    number = (number == noteCollection.getCount()) ? 1 : number + 1;
                } else {
                    // No notes present
                    statusLabel.setText("No Notes Present");
                }
            }
        });


        // Put the newNoteButton event handler code here:
        newNoteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Get text from fields
                String name = nameField.getText();
                String body = bodyField.getText();

                // Check if any field is empty
                if (name.equals("") || body.equals("")) {
                    clearFields();
                    statusLabel.setText("Note not added");
                } else {
                    // Create new note and add to collection
                    Note newNote = new Note(name, body);
                    noteCollection.add(newNote);
                    clearFields();
                    statusLabel.setText("OK");
                }
            }
        });


    }

    // Clear the note fields
    public static void clearFields() {
        nameField.setText("");
        bodyField.setText("");
    }

    // Set up a File->Open menu with a file chooser
    public static void setUpMenu(Stage stage) {
        // Your code here
        // Set initial directory to current directory
        fileChooser.setInitialDirectory(new File("."));

        // Add openFile to the menu
        menu.getItems().add(openFile);

        // Add menu to the menuBar
        menuBar.getMenus().add(menu);

        // Create and add handler for openFile
        openFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File file = fileChooser.showOpenDialog(stage);

                if (file != null) {
                    noteCollection.readNotes(file.getAbsolutePath());
                    statusLabel.setText("OK");
                } else {
                    statusLabel.setText("File not opened");
                }
            }
        });

    }

    public static void main(String[] args) {
        // Comment out the next line when you get the file chooser working
        noteCollection.readNotes("notes.txt");
        launch(args);
    }
}
