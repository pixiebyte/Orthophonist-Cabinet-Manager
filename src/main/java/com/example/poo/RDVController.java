package com.example.poo;

import com.example.poo.Models.Orthophoniste;
import com.example.poo.Models.RendezVous;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RDVController {

    @FXML
    private TableView<RendezVous> tbData;

    @FXML
    private TableColumn<RendezVous, String> studentId;

    @FXML
    private TableColumn<RendezVous, String> firstName;

    @FXML
    private TableColumn<RendezVous, String> lastName;

    @FXML
    private TableColumn<RendezVous, String> actionColumn;

    @FXML
    private TableColumn<RendezVous, String> type;
    @FXML
    private Button retour;

    private Orthophoniste ortho = new Orthophoniste("Felkir", "Ryma", "BEK", "mr_felkir@esi.dz", "admin");

    @FXML
    public void initialize() {

        studentId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));
        firstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHeure()));
        lastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDur()));
        actionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getObservation()));
        type.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().gettype()));


        RendezVous rdv1 = new RendezVous("12 juin 2024", "12:00", "premier rdv", "2h","Seance suivi");
        RendezVous rdv2 = new RendezVous("13 juin 2024", "13:00", "Autiste", "2h","Seance suivi");
        RendezVous rdv3 = new RendezVous("14 juin 2024", "14:00", "no observation", "2h","Consultation");
        RendezVous rdv4 = new RendezVous("12 juin 2024", "12:00", "premier rdv", "2h","Atelier");
        RendezVous rdv5 = new RendezVous("13 juin 2024", "13:00", "premier rdv", "2h","Seance suivi");
        RendezVous rdv6 = new RendezVous("14 juin 2024", "14:00", "seance suivie", "2h","Consulation");

        List<RendezVous> rdv = new ArrayList<>();
        rdv.add(rdv1);
        rdv.add(rdv2);
        rdv.add(rdv3);
        rdv.add(rdv4);
        rdv.add(rdv5);
        rdv.add(rdv6);

        ObservableList<RendezVous> RDVdata = FXCollections.observableArrayList(rdv);
        tbData.setItems(RDVdata);


    }

    @FXML
    private void handleRetourButtonAction() {
        switchToPage("/com/example/poo/Patients.fxml", retour);
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

