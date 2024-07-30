package com.example.assignment2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Optional;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextField inputField = new TextField();
        inputField.setPromptText("Enter spell name");
        Button fetchButton = new Button("Fetch Spell");
        Label examplesLabel = new Label("Examples: Avada Kedavra, Expelliarmus, Accio");
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);



        fetchButton.setOnAction(event -> {
            String spellName = inputField.getText().trim().toLowerCase();
            resultArea.clear();
            if (!spellName.isEmpty()) {
                try {
                    SpellModel[] spells = SpellService.fetchData();
                    Optional<SpellModel> spell = Arrays.stream(spells)
                            .filter(s -> s.getSpell().equalsIgnoreCase(spellName))
                            .findFirst();
                    if (spell.isPresent()) {
                        resultArea.appendText(spell.get().toString());
                    } else {
                        resultArea.appendText("Spell not found: " + spellName);
                    }
                } catch (Exception e) {
                    resultArea.setText("Error fetching data: " + e.getMessage());
                }
            } else {
                resultArea.setText("Please enter a spell name.");
            }
        });

        VBox vbox = new VBox(10, inputField, fetchButton,examplesLabel, resultArea);
        Scene scene = new Scene(vbox, 400, 300);

        primaryStage.setTitle("Harry Potter Spells");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}