package com.example.poo;

import com.example.poo.Models.Main;
import com.example.poo.Models.Orthophoniste;
import com.example.poo.Models.Patient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert;


import java.io.IOException;
import java.util.Optional;

public class LoginController {

   @FXML
   private TextField gmailField;

   @FXML
   private PasswordField passwordField;

   @FXML
   private Button loginButton;

   @FXML
   private Button createAccountButton;

   @FXML
   private void handleLoginButtonAction() {
      String gmail = gmailField.getText();
      String password = passwordField.getText();
      Main main = new Main();
      Orthophoniste ortho = main.Authentifier(gmail, password);
      Patient p1 = new Patient("felkir", "ryma", "09/07/2004", "alg", 1234567890);
      Patient p2 = new Patient("sebbak", "narimene", "12/08/2003", "alg", 456457890);
      Patient p3 = new Patient("maachi", "aymen", "03/06/2004", "alg", 1234567890);
      Patient p4 = new Patient("kellou", "ines", "09/07/2004", "alg", 1234567890);
      Patient p5 = new Patient("kheddia", "assia", "09/08/2009", "alg", 456457890);
      Patient p6 = new Patient("sennane", "rayane", "03/06/2004", "alg", 1234567890);
      ortho.SauvegarderPatient(p1, null , null , null );
      ortho.SauvegarderPatient(p2, null , null , null );
      ortho.SauvegarderPatient(p3, null , null , null );
      ortho.SauvegarderPatient(p4, null , null , null );
      ortho.SauvegarderPatient(p5, null , null , null );
      ortho.SauvegarderPatient(p6, null , null , null );

      main.SauvegarderOrthophonistes();

//        ortho.patients.add(p1);
//        ortho.patients.add(p2);
//        ortho.patients.add(p3);
//        ortho.patients.add(p4);
//        ortho.patients.add(p5);
//        ortho.patients.add(p6);


      if (gmail.isEmpty() || password.isEmpty()) {
         showAlert("Erreur", "Tous les champs doivent être remplis.");
         return;
      }

      System.out.println("Gmail: " + gmail);
      System.out.println("Password: " + password);

      if (ortho != null) {
         switchToPage("/com/example/poo/Accueil.fxml", loginButton, ortho);
      } else {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation de suppression");
         alert.setHeaderText(null);
         alert.setContentText("Voulez-vous supprimer cette ligne ?");


      }
   }

   @FXML
   private void handleCreateAccountButtonAction() {
      switchToPage("/com/example/poo/creer-compte.fxml", createAccountButton, null);
   }

   private void switchToPage(String fxmlPath, Button button, Orthophoniste ortho) {
      try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
         Parent nextPage = loader.load();

         Object controller = loader.getController();
         if (controller instanceof AcceuilController) {
            AcceuilController accueilController = (AcceuilController) controller;
            if (ortho != null) {
               accueilController.setOrthophoniste(ortho);
            }
         }

         Stage stage = (Stage) button.getScene().getWindow();
         Scene scene = new Scene(nextPage);
         stage.setScene(scene);
         stage.show();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void showAlert(String title, String message) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle(title);
      alert.setHeaderText(null);
      alert.setContentText(message);
      alert.showAndWait();
   }
}
