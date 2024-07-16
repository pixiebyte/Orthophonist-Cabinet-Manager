package com.example.poo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Atelier {

    @FXML
    private Button retour;

    @FXML
    private TextField reponseField;

    @FXML
    private TextField questionField;

    @FXML
    private Button okButton;

    @FXML
    private TableView<Map<String, String>> tbData;

    @FXML
    private TableColumn<Map<String, String>, String> studentId;

    @FXML
    private TableColumn<Map<String, String>, String> firstName;

    @FXML
    private TableColumn<Map<String, String>, String> lastName;

    @FXML
    private Button retour1;

    @FXML
    private TextField observationField;

    private final ObservableList<Map<String, String>> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize the table columns with map keys
        studentId.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().get("ID")));
        firstName.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().get("FirstName")));
        lastName.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().get("LastName")));

        // Set the table's items
        tbData.setItems(data);
    }

    @FXML
    private void handleOkButtonAction() {
        String firstName = questionField.getText();
        String lastName = reponseField.getText();

        // Validate input fields
        if (firstName.isEmpty() || lastName.isEmpty()) {
            // Handle empty input (optional)
            System.out.println("Both fields are required.");
            return;
        }

        // Generate unique ID for the new patient
        String patientId = String.valueOf(data.size() + 1);

        // Create a new entry with the input values
        Map<String, String> newEntry = new HashMap<>();
        newEntry.put("ID", patientId);
        newEntry.put("FirstName", firstName);
        newEntry.put("LastName", lastName);

        // Add the new entry to the table
        data.add(newEntry);

        // Clear input fields
        questionField.clear();
        reponseField.clear();
    }

    @FXML
    private void handleRetourButtonAction() {
        switchToPage("/com/example/poo/lancer-rdv.fxml", retour);
    }

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
