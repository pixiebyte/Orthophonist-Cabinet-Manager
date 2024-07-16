package com.example.poo;

import com.example.poo.Models.Questionnaire;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Optional;

public class CreerAnamnese {

    @FXML
    private TextField questionField;

    @FXML
    private TextField reponseField;

    @FXML
    private TableView<Questionnaire> tbData;

    @FXML
    private TableColumn<Questionnaire, String> questionColumn;

    @FXML
    private TableColumn<Questionnaire, String> reponseColumn;

    @FXML
    private TableColumn<Questionnaire, Void> supprimerColumn;

    @FXML
    private Button okButton;

    @FXML
    public void initialize() {
        System.out.println("Initializing...");

        // Ensure the text fields are enabled and editable
        questionField.setDisable(false);
        questionField.setEditable(true);
        reponseField.setDisable(false);
        reponseField.setEditable(true);

        // Bind columns to Questionnaire properties
        questionColumn.setCellValueFactory(cellData -> cellData.getValue().questionProperty());
        reponseColumn.setCellValueFactory(cellData -> cellData.getValue().reponseProperty());

        // Add a button to each row for deletion
        addButtonToTable(supprimerColumn, "Supprimer");
    }

    private void addButtonToTable(TableColumn<Questionnaire, Void> column, String buttonText) {
        Callback<TableColumn<Questionnaire, Void>, TableCell<Questionnaire, Void>> cellFactory = new Callback<TableColumn<Questionnaire, Void>, TableCell<Questionnaire, Void>>() {
            @Override
            public TableCell<Questionnaire, Void> call(final TableColumn<Questionnaire, Void> param) {
                final TableCell<Questionnaire, Void> cell = new TableCell<Questionnaire, Void>() {
                    private final Button deleteButton = new Button(buttonText);

                    {
                        deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                        deleteButton.setOnAction((event) -> {
                            Questionnaire questionnaire = getTableView().getItems().get(getIndex());
                            // Handle the "Supprimer" action
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de suppression");
                            alert.setHeaderText(null);
                            alert.setContentText("Voulez-vous supprimer cette ligne ?");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                getTableView().getItems().remove(questionnaire);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteButton);
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
        // Get input values from text fields
        String question = questionField.getText();
        String reponse = reponseField.getText();

        System.out.println("Question: " + question);
        System.out.println("Reponse: " + reponse);

        if (question.isEmpty() || reponse.isEmpty()) {
            // Handle empty input (optional)
            System.out.println("Both fields are required.");
            return;
        }

        // Create a new Questionnaire instance with the input values
        Questionnaire questionnaire = new Questionnaire(question, reponse);

        // Add the Questionnaire instance to the TableView
        tbData.getItems().add(questionnaire);

        // Clear input fields
        questionField.clear();
        reponseField.clear();

        // Request focus on the question field to allow for easy data entry
        questionField.requestFocus();
    }

    @FXML
    private void handleRetourButtonAction() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gerer-testes.fxml"));
            Scene accueilScene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) tbData.getScene().getWindow();
            stage.setScene(accueilScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
