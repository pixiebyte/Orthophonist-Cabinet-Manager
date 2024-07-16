package com.example.poo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class InfoPersonnelles {

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
    private ComboBox<String> type;

    @FXML
    private TextField classeField;

    @FXML
    private TextField telParentField;

    @FXML
    private TextField diplomeField;

    @FXML
    private TextField professionField;

    @FXML
    private TextField telField;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button retour;

    @FXML
    public void initialize() {
        type.getItems().addAll("Adulte", "Enfant");
    }

    @FXML
    Button okButton;
    @FXML
    private void handleOkButtonAction(ActionEvent event) {
        gatherInputData();
        switchToPage("/com/example/poo/BO_anamnese.fxml", okButton);

    }

    private void gatherInputData() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String dateNaissance = dateNaissanceField.getText();
        String lieuNaissance = lieuNaissanceField.getText();
        String adresse = adresseField.getText();
        String selectedType = type.getSelectionModel().getSelectedItem();

        if (nom.isEmpty() || prenom.isEmpty() || dateNaissance.isEmpty() || lieuNaissance.isEmpty() || adresse.isEmpty() || selectedType == null) {
            showAlert("Erreur", "Tous les champs doivent être remplis.");
            return;
        }

        if (selectedType.equals("Enfant")) {
            String classe = classeField.getText();
            String telParent = telParentField.getText();
            if (classe.isEmpty() || telParent.isEmpty()) {
                showAlert("Erreur", "Pour un enfant, les champs 'Classe d'étude' et 'Numéro téléphone parent' doivent être remplis.");
                return;
            }
        } else if (selectedType.equals("Adulte")) {
            String diplome = diplomeField.getText();
            String profession = professionField.getText();
            String tel = telField.getText();
            if (diplome.isEmpty() || profession.isEmpty() || tel.isEmpty()) {
                showAlert("Erreur", "Pour un adulte, les champs 'Diplôme', 'Profession' et 'Numéro téléphone' doivent être remplis.");
                return;
            }
        }

        try {
            String fxmlFile;
            if (selectedType.equals("Adulte")) {
                fxmlFile = "/com/example/poo/Accueil.fxml";
            } else {
                fxmlFile = "/com/example/poo/Connecter.fxml";
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) anchorPane.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRetourButtonAction() {
        switchToPage("/com/example/poo/lancer-rdv.fxml", retour);
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
