package com.example.poo;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class seancesuivi {

    @FXML
    private TableView<Objectif> tbData;

    @FXML
    private TableColumn<Objectif, String> studentId;

    @FXML
    private TableColumn<Objectif, String> firstName;

    @FXML
    private TableColumn<Objectif, String> supprimerColumn;

    @FXML
    private TableColumn<Objectif, String> numeroColumn;

    @FXML
    private Button retour;

    @FXML
    private TextField questionField;

    @FXML
    private TextField reponseField;

    @FXML
    private TextField reponseField1;

    @FXML
    private TextField numero;

    @FXML
    private TextField note;

    private ObservableList<Objectif> objectifsData = FXCollections.observableArrayList();

    public void initialize() {
        numeroColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(objectifsData.indexOf(cellData.getValue()) + 1)));
        studentId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomObjectif()));
        firstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        supprimerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNote()));

        // Add sample data
        objectifsData.add(new Objectif("Objectif 1", "Long", "10"));
        objectifsData.add(new Objectif("Objectif 2", "Moyen", "10"));
        objectifsData.add(new Objectif("Objectif 3", "Court", "10"));

        tbData.setItems(objectifsData);
    }

    @FXML
    private void handleOkButtonAction() {
        System.out.println("Ajouter button clicked");

        String nomObjectif = questionField.getText();
        String type = reponseField.getText();
        String note = reponseField1.getText();

        System.out.println("Input values: " + nomObjectif + ", " + type + ", " + note);

        try {
            int noteValue = Integer.parseInt(note);
            if (noteValue < 1 || noteValue > 10) {
                showAlert("Note must be a number between 1 and 10.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Note must be a valid number.");
            return;
        }

        if (nomObjectif.isEmpty() || type.isEmpty() || note.isEmpty()) {
            showAlert("All fields are required.");
            return;
        }

        Objectif newObjectif = new Objectif(nomObjectif, type, note);
        tbData.getItems().add(newObjectif);

        questionField.clear();
        reponseField.clear();
        reponseField1.clear();
    }

    @FXML
    private void handleNoterButtonAction() {
        String numeroText = numero.getText();
        String noteText = note.getText();

        try {
            int numeroValue = Integer.parseInt(numeroText);
            int noteValue = Integer.parseInt(noteText);
            if (noteValue < 1 || noteValue > 10) {
                showAlert("Note must be a number between 1 and 10.");
                return;
            }

            if (numeroValue < 1 || numeroValue > tbData.getItems().size()) {
                showAlert("Numero must be a valid row number.");
                return;
            }

            Objectif objectif = tbData.getItems().get(numeroValue - 1);
            objectif.setNote(noteText);
            tbData.refresh();

        } catch (NumberFormatException e) {
            showAlert("Both Numero and Note must be valid numbers.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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

    public static class Objectif {
        private final SimpleStringProperty nomObjectif;
        private final SimpleStringProperty type;
        private final SimpleStringProperty note;

        public Objectif(String nomObjectif, String type, String note) {
            this.nomObjectif = new SimpleStringProperty(nomObjectif);
            this.type = new SimpleStringProperty(type);
            this.note = new SimpleStringProperty(note);
        }

        public String getNomObjectif() {
            return nomObjectif.get();
        }

        public String getType() {
            return type.get();
        }

        public String getNote() {
            return note.get();
        }

        public void setNote(String note) {
            this.note.set(note);
        }
    }
}
