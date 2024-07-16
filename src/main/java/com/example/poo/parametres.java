package com.example.poo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class parametres {

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField dateNaissanceField;

    @FXML
    private TextField lieuNaissanceField;

    @FXML
    private TextField adresseField;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button retour;

    @FXML
    private Button okButton;

    @FXML
    private Button suuprimer;

    @FXML
    private Button deconnecter;

    @FXML
    private void handleOkButtonAction(ActionEvent event) {
        if (event.getSource() == suuprimer) {
            // Handle the "Supprimer compte" action
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Voulez-vous supprimer ce compte ?");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    switchToPage("/com/example/poo/Connecter.fxml", retour);
                    showAlert("Compte Supprimé", "Le compte a été supprimé avec succès.");
                }
            });
        } else if (event.getSource() == deconnecter) {
            // Handle the "Déconnecter" action
            showAlert("Déconnecter", "Vous avez été déconnecté.");
            switchToPage("/com/example/poo/Connecter.fxml", deconnecter);
        } else {
            // Handle the "OK" action
            gatherInputData();
            switchToPage("/com/example/poo/Acceuil.fxml", okButton);
        }
    }

    private void gatherInputData() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String dateNaissance = dateNaissanceField.getText();
        String lieuNaissance = lieuNaissanceField.getText();
        String adresse = adresseField.getText();

        if (nom.isEmpty() || prenom.isEmpty() || dateNaissance.isEmpty() || lieuNaissance.isEmpty() || adresse.isEmpty()) {
            showAlert("Erreur", "Tous les champs doivent être remplis.");
            return;
        }

        // Add logic to save the gathered input data as needed
    }

    @FXML
    private void handleRetourButtonAction(ActionEvent event) {
        switchToPage("/com/example/poo/Accueil.fxml", retour);
    }

    @FXML
    private void handleskipButtonAction(ActionEvent event) {
        switchToPage("/com/example/poo/Connecter.fxml", deconnecter);
    }
    @FXML
    private void handlesdeconnecterButtonAction(ActionEvent event) {
        switchToPage("/com/example/poo/Connecter.fxml", deconnecter);
    }

    @FXML
    private void handleacceuilButtonAction(ActionEvent event) {
        switchToPage("/com/example/poo/accueil.fxml", okButton);
    }

    private void switchToPage(String fxmlPath, Button button) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent nextPage = loader.load();

            Stage stage = (Stage) button.getScene().getWindow();
            Scene scene = new Scene(nextPage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
