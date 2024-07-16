package com.example.poo;

import com.example.poo.Models.Orthophoniste;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import com.calendarfx.view.page.DayPage;
import com.calendarfx.view.page.MonthPage;
import com.calendarfx.view.page.WeekPage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.application.Platform;
import java.time.LocalDate;
import java.time.LocalTime;

import java.io.IOException;

public class AcceuilController {

    @FXML
    private Button patients;

    @FXML
    private Button testsButton;

    @FXML
    private Button rdvButton;

    @FXML
    private Button statsButton;

    @FXML
    private Button paramButton;

    private Orthophoniste ortho;

    public void setOrthophoniste(Orthophoniste ortho) {
        this.ortho = ortho;
    }

    @FXML
    private void handlePatientsButtonAction() {
        switchToPage("/com/example/poo/Patients.fxml", patients);
    }

    @FXML
    private void handleTestsButtonAction() {
        System.out.println("Tests button clicked");
        switchToPage("/com/example/poo/gerer-testes.fxml", testsButton);
    }

    @FXML
    private void handleRdvButtonAction() {
        switchToPage("/com/example/poo/lancer-rdv.fxml", rdvButton);
    }

    @FXML
    private void handleStatsButtonAction() {
        switchToPage("/com/example/poo/statistiques.fxml", statsButton);
    }

    @FXML
    private void handleParamButtonAction() {
        switchToPage("/com/example/poo/parametres.fxml", paramButton);
    }
    @FXML
    private Button calendar_btn;

    @FXML
    private StackPane calendar_page;



    @FXML
    private void handleCalendarButtonAction() {
        // Set up the calendar view
        Stage calendarStage = new Stage();
        CalendarView calendarView = new CalendarView();
        calendarView.setShowAddCalendarButton(false);

        // Create and configure calendars
        Calendar consultation = new Calendar("Consultation");
        Calendar suivi = new Calendar("Seance de Suivi");
        Calendar atelier = new Calendar("Atelier de Groupe");
        consultation.setStyle(Calendar.Style.STYLE1);
        suivi.setStyle(Calendar.Style.STYLE2);
        atelier.setStyle(Calendar.Style.STYLE3);

        // Add calendars to a calendar source
        CalendarSource orthophonistecaCalendarSource = new CalendarSource("Orthophoniste");
        orthophonistecaCalendarSource.getCalendars().addAll(consultation, suivi, atelier);

        // Set calendar source to the calendar view
        calendarView.getCalendarSources().setAll(orthophonistecaCalendarSource);
        calendarView.setRequestedTime(LocalTime.now());

        // Set up the scene and show the calendar window
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(calendarView);
        Scene scene = new Scene(stackPane);
        calendarStage.setScene(scene);
        calendarStage.setWidth(930);
        calendarStage.setHeight(649);
        calendarStage.centerOnScreen();
        calendarStage.show();

        // Add a close request listener to handle the window close event
        calendarStage.setOnCloseRequest(event -> {
            // Navigate back to the "Accueil" page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/poo/Accueil.fxml"));
            try {
                Parent root = loader.load();
                Stage stage = (Stage) calendar_btn.getScene().getWindow();
                Scene accueilScene = new Scene(root);
                stage.setScene(accueilScene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Hide the "Accueil" page when the calendar window is opened
        calendar_page.setVisible(true);
        calendar_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #2d82cc, #9cc0d7);");
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
}



