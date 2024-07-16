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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class fichesuivi {

    @FXML
    private TableView<Objectif> tbData;

    @FXML
    private TableColumn<Objectif, String> studentId;

    @FXML
    private TableColumn<Objectif, String> firstName;

    @FXML
    private TableColumn<Objectif, String> supprimerColumn;

    @FXML
    private Button retour;

    @FXML
    private TextField questionField;

    @FXML
    private TextField reponseField;

    @FXML
    private TextField reponseField1;

    private ObservableList<Objectif> objectifsData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
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
        String nomObjectif = questionField.getText();
        String type = reponseField.getText();
        String note = reponseField1.getText();

        // Check if the note is a valid number between 1 and 5
        try {
            int noteValue = Integer.parseInt(note);
            if (noteValue < 1 || noteValue > 10) {
                System.out.println("Note must be a number between 1 and 5.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Note must be a valid number.");
            return;
        }

        if (nomObjectif.isEmpty() || type.isEmpty() || note.isEmpty()) {
            System.out.println("All fields are required.");
            return;
        }

        Objectif newObjectif = new Objectif(nomObjectif, type, note);
        tbData.getItems().add(newObjectif);

        questionField.clear();
        reponseField.clear();
        reponseField1.clear();
    }

    @FXML
    private void handleRetourButtonAction() {
        switchToPage("/com/example/poo/Patients.fxml", retour);
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
    }
}
