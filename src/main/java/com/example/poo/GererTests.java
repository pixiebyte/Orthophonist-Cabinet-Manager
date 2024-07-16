package com.example.poo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GererTests {

    @FXML
    private Button tests1;

    @FXML
    private Button tests11;

    @FXML
    private Button retourButton;

    @FXML
    private void handleTests1Action() {
        System.out.println("Tests1 button clicked");
        switchToPage("/com/example/poo/creer-anamnese.fxml", tests1);
    }

    @FXML
    private void handleTests11Action() {
        System.out.println("Tests11 button clicked");
        switchToPage("/com/example/poo/creer-epreuve.fxml", tests11);
    }

    @FXML
    private void handleRetourButtonAction() {
        switchToPage("/com/example/poo/accueil.fxml", retourButton);
    }

    private void switchToPage(String fxmlPath, Button button) {
        try {
            System.out.println("Switching to page: " + fxmlPath);

            // Load the next page
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent nextPage = loader.load();

            // Get the current stage
            Stage stage = (Stage) button.getScene().getWindow();

            // Set the scene to the new page
            Scene scene = new Scene(nextPage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load FXML file: " + fxmlPath);
            e.printStackTrace();
        }
    }
}
