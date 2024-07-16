package com.example.poo;

import com.example.poo.Models.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreerCompte {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    private void handleSignUpButtonAction() {
        String username = usernameField.getText();
        String firstName = firstNameField.getText();
        String address = addressField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        // Perform validation
        if (username.isEmpty() || firstName.isEmpty() || address.isEmpty() || email.isEmpty() || password.isEmpty()) {
            // Display an error message if any field is empty
            System.out.println("Please fill in all fields");
            return;
        }

        Main main = new Main();
        main.CreerCompte(username, firstName, address, email, password);

        // After successful account creation, navigate to another page
        switchToWelcomePage();

        // For demo purposes, let's just print the input values
        System.out.println("Username: " + username);
        System.out.println("First Name: " + firstName);
        System.out.println("Address: " + address);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
    }

    private void switchToWelcomePage() {
        try {
            // Load the next page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/poo/Accueil.fxml"));
            Parent welcomePage = loader.load();


            // Get the current stage
            Stage stage = (Stage) signUpButton.getScene().getWindow();

            // Set the scene to the new page
            Scene scene = new Scene(welcomePage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
