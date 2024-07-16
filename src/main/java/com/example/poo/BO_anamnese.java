package com.example.poo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class BO_anamnese {

    @FXML
    private TextField questionField;

    @FXML
    private TextField reponseField;

    @FXML
    private TableView<TableData> tbData;

    @FXML
    private TableColumn<TableData, Integer> numeroColumn;

    @FXML
    private TableColumn<TableData, String> questionColumn;

    @FXML
    private TableColumn<TableData, String> reponseColumn;

    @FXML
    private TableColumn<TableData, String> noteColumn;

    private ObservableList<TableData> dataList;

    @FXML
    public void initialize() {
        numeroColumn.setCellValueFactory(new PropertyValueFactory<>("numero"));
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
        reponseColumn.setCellValueFactory(new PropertyValueFactory<>("reponse"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));

        dataList = FXCollections.observableArrayList();
        tbData.setItems(dataList);

        // Initialize the table with default values (e.g., numbers from 1 to 10)
        for (int i = 1; i <= 10; i++) {
            dataList.add(new TableData(i, "Question " + i, "Reponse " + i, ""));        }
    }

    @FXML
    private Button retour;

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
    @FXML
    private void handleOkButtonAction() {
        String questionNumberStr = questionField.getText();
        String note = reponseField.getText();

        if (!questionNumberStr.isEmpty() && !note.isEmpty()) {
            int questionNumber = Integer.parseInt(questionNumberStr);

            for (TableData data : dataList) {
                if (data.getNumero() == questionNumber) {
                    data.setNote(note);
                    tbData.refresh();
                    break;
                }
            }
        }

        questionField.clear();
        reponseField.clear();
    }



    // Inner class representing the table data
    public static class TableData {
        private Integer numero;
        private String question;
        private String reponse;
        private String note;

        public TableData(Integer numero, String question, String reponse, String note) {
            this.numero = numero;
            this.question = question;
            this.reponse = reponse;
            this.note = note;
        }

        public Integer getNumero() {
            return numero;
        }

        public void setNumero(Integer numero) {
            this.numero = numero;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getReponse() {
            return reponse;
        }

        public void setReponse(String reponse) {
            this.reponse = reponse;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }
}
