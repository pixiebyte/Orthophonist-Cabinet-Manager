package com.example.poo;

import com.example.poo.Models.Orthophoniste;
import com.example.poo.Models.Patient;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PatientsController {

    private int nextId = 1123456;

    @FXML
    private TableView<Patient> tbData;

    @FXML
    private TableColumn<Patient, Integer> studentId;

    @FXML
    private TableColumn<Patient, String> firstName;

    @FXML
    private TableColumn<Patient, String> lastName;

    @FXML
    private TableColumn<Patient, Void> actionColumn;

    @FXML
    private TableColumn<Patient, Void> bilanColumn;

    @FXML
    private TableColumn<Patient, Void> ficheSuiviColumn;

    @FXML
    private TableColumn<Patient, Void> supprimerColumn;

    @FXML
    private Button retour;

    @FXML
    private TextField questionField;

    @FXML
    private TextField reponseField;

    private Orthophoniste ortho = new Orthophoniste("Felkir", "Ryma", "BEK", "mr_felkir@esi.dz", "admin");

    @FXML
    public void initialize() {
        studentId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getID()).asObject());
        firstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
        lastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));

        Patient p1 = new Patient("felkir", "ryma", "09/07/2004", "alg", 1234567890);
        Patient p2 = new Patient("felkir", "billel", "09/08/2009", "alg", 456457890);
        Patient p3 = new Patient("maachi", "aymen", "03/06/2004", "alg", 1234567890);
        Patient p4 = new Patient("kellou", "ines", "09/07/2004", "alg", 1234567890);
        Patient p5 = new Patient("kheddia", "assia", "09/08/2009", "alg", 456457890);
        Patient p6 = new Patient("sennane", "rayane", "03/06/2004", "alg", 1234567890);
        ortho.patients.add(p1);
        ortho.patients.add(p2);
        ortho.patients.add(p3);
        ortho.patients.add(p4);
        ortho.patients.add(p5);
        ortho.patients.add(p6);

        List<Patient> patients = ortho.patients;
        ObservableList<Patient> patientData = FXCollections.observableArrayList(patients);
        tbData.setItems(patientData);

        addButtonToTable(actionColumn, "Rendez vous", "/com/example/poo/RDV-patient.fxml", null);
        addButtonToTable(bilanColumn, "Bilan Orthophonique", "/com/example/poo/lancer-rdv.fxml", null);
        addButtonToTable(ficheSuiviColumn, "Fiche Suivi", "/com/example/poo/fiche-suivi.fxml", null);
        addButtonToTable(supprimerColumn, "Supprimer", null, "-fx-background-color: red; -fx-text-fill: white;");
    }

    private void addButtonToTable(TableColumn<Patient, Void> column, String buttonText, String fxmlPath, String style) {
        Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>> cellFactory = new Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>>() {
            @Override
            public TableCell<Patient, Void> call(final TableColumn<Patient, Void> param) {
                final TableCell<Patient, Void> cell = new TableCell<Patient, Void>() {

                    private final Button btn = new Button(buttonText);

                    {
                        if (style != null) {
                            btn.setStyle(style);
                        }
                        btn.setOnAction((event) -> {
                            Patient patient = getTableView().getItems().get(getIndex());
                            if (fxmlPath != null) {
                                switchToPage(fxmlPath);
                            } else {
                                // Handle the "Supprimer" action
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation de suppression");
                                alert.setHeaderText(null);
                                alert.setContentText("Voulez-vous supprimer ce patient ?");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.isPresent() && result.get() == ButtonType.OK) {
                                    getTableView().getItems().remove(patient);
                                }
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }

                    private void switchToPage(String fxmlPath) {
                        try {
                            // Load the next page
                            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                            Parent nextPage = loader.load();

                            // Get the current stage
                            Stage stage = (Stage) btn.getScene().getWindow();

                            // Set the scene to the new page
                            Scene scene = new Scene(nextPage);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                return cell;
            }
        };

        column.setCellFactory(cellFactory);
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
        int patientId = nextId++;

        // Create a new Patient instance with the input values
        Patient newPatient = new Patient(patientId, firstName, lastName);

        // Add the new patient to the table
        tbData.getItems().add(newPatient);

        // Clear input fields
        questionField.clear();
        reponseField.clear();
    }

    @FXML
    private void handleRetourButtonAction() {
        switchToPage("/com/example/poo/accueil.fxml", retour);
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
