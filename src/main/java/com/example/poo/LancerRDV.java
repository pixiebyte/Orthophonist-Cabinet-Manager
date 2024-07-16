package com.example.poo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LancerRDV {

    @FXML
    private Button tests1;
    @FXML
    private Button tests11;
    @FXML
    private Button tests111;
    @FXML
    private Button okButton;
    @FXML
    private Button retourButton; // Add the missing button reference
    @FXML
    private TextField idpatient; // Add reference to your TextField

    @FXML
    private void handleokButtonAction() {
        String idText = idpatient.getText();
        if (!idText.isEmpty()) {
            try {
                int id = Integer.parseInt(idText);
                if (id >= 1 && id <= 5) {
                    // ID is between 1 and 5, navigate to the desired page
                    switchToPage("/com/example/poo/your_desired_page.fxml", okButton); // Change to the desired page
                } else {
                    System.out.println("ID must be between 1 and 5.");
                    // Display an error message or handle invalid input accordingly
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID. Please enter a number between 1 and 5.");
                // Handle the case where the input cannot be parsed to an integer
            }
        } else {
            System.out.println("ID field is empty.");
            // Display an error message or handle empty input accordingly
        }
    }

    @FXML
    private void handleretourButtonAction() {
        switchToPage("/com/example/poo/accueil.fxml", retourButton);
    }

    @FXML
    private void handleconsultationButtonAction() {
        switchToPage("/com/example/poo/info-perso.fxml", tests1);
    }

    @FXML
    private void handlesuiviButtonAction() {
        switchToPage("/com/example/poo/seance-suivi.fxml", tests11);
    }

    @FXML
    private void handleatlierButtonAction() {
        switchToPage("/com/example/poo/atelier.fxml", tests111);
    }

    @FXML
    private void switchToPage(String fxmlPath, Button button) {
        try {
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
            e.printStackTrace();
        }
    }
}
