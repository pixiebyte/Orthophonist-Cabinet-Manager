package com.example.poo;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class CreerEpreuve {

    @FXML
    private TableView<RowData> tbData;

    @FXML
    private TableColumn<RowData, String> studentId;

    @FXML
    private TableColumn<RowData, String> actionColumn;

    @FXML
    private TableColumn<RowData, Void> supprimerColumn;

    @FXML
    private TableView<RowData> tbData1;

    @FXML
    private TableColumn<RowData, String> studentId1;

    @FXML
    private TableColumn<RowData, String> lastName1;

    @FXML
    private TableColumn<RowData, Void> supprimerColumn1;

    @FXML
    private TextField questionField;

    @FXML
    private TextField reponseField;

    @FXML
    private TextField questionField1;

    @FXML
    private TextField reponseField1;

    @FXML
    private Button okButton;

    @FXML
    private Button okButton1;

    @FXML
    private Button retourbutton;

    private ObservableList<RowData> data = FXCollections.observableArrayList();
    private ObservableList<RowData> data1 = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize table 1
        studentId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuestion()));
        actionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReponse()));
        tbData.setItems(data);
        addButtonToTable(supprimerColumn, data);

        // Initialize table 2
        studentId1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuestion()));
        lastName1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReponse()));
        tbData1.setItems(data1);
        addButtonToTable(supprimerColumn1, data1);

        // Add button action handlers
        okButton.setOnAction(event -> handleAddRowToTable1());
        okButton1.setOnAction(event -> handleAddRowToTable2());

        // Add action handler for retourbutton
        retourbutton.setOnAction(event -> handleRetourButtonAction());
    }

    private void handleAddRowToTable1() {
        String question = questionField.getText();
        String reponse = reponseField.getText();
        if (!question.isEmpty() && !reponse.isEmpty()) {
            data.add(new RowData(question, reponse));
            questionField.clear();
            reponseField.clear();
        }
    }

    private void handleAddRowToTable2() {
        String question = questionField1.getText();
        String reponse = reponseField1.getText();
        if (!question.isEmpty() && !reponse.isEmpty()) {
            data1.add(new RowData(question, reponse));
            questionField1.clear();
            reponseField1.clear();
        }
    }

    private void addButtonToTable(TableColumn<RowData, Void> column, ObservableList<RowData> data) {
        Callback<TableColumn<RowData, Void>, TableCell<RowData, Void>> cellFactory = new Callback<TableColumn<RowData, Void>, TableCell<RowData, Void>>() {
            @Override
            public TableCell<RowData, Void> call(final TableColumn<RowData, Void> param) {
                final TableCell<RowData, Void> cell = new TableCell<RowData, Void>() {
                    private final Button deleteButton = new Button("Supprimer");

                    {
                        deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                        deleteButton.setOnAction((event) -> {
                            RowData rowData = getTableView().getItems().get(getIndex());
                            // Handle the "Supprimer" action
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de suppression");
                            alert.setHeaderText(null);
                            alert.setContentText("Voulez-vous supprimer cette ligne ?");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                data.remove(rowData);
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

    public static class RowData {
        private final SimpleStringProperty question;
        private final SimpleStringProperty reponse;

        public RowData(String question, String reponse) {
            this.question = new SimpleStringProperty(question);
            this.reponse = new SimpleStringProperty(reponse);
        }

        public String getQuestion() {
            return question.get();
        }

        public void setQuestion(String question) {
            this.question.set(question);
        }

        public String getReponse() {
            return reponse.get();
        }

        public void setReponse(String reponse) {
            this.reponse.set(reponse);
        }
    }

    @FXML
    private void handleRetourButtonAction() {
        switchToPage("/com/example/poo/gerer-testes.fxml", retourbutton);
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