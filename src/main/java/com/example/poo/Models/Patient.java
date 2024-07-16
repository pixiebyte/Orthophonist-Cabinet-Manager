package com.example.poo.Models;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Patient implements Serializable  {
    // instance variaimplementsbles
    public String nom;
    public String prenom;
    public String dateN;
    public String lieuN;
    public String adresse;
    public ArrayList<RendezVous> rdv;
     public int id;
    public ArrayList<BO> bo;
    public ArrayList<FicheSuivi> fs;

     Patient(){}

    public Patient(int patientId, String firstName, String lastName) {
         this.id = patientId;
         this.nom = firstName;
         this.prenom = lastName;
    }

    public void  RemplireDossier(RendezVous rdv, BO bo, FicheSuivi fs) {

      this.rdv.add(rdv);
      this.bo.add(bo);
      this.fs.add(fs);

    }

     // Constructor
     public Patient(String nom, String prenom,String  DateN, String adresse,int id) {
      this.nom = nom;
      this.prenom = prenom;
      this.adresse = adresse;
      this.dateN = DateN;
      this.id = id;
      this.rdv = new ArrayList<>();
      this.bo = new ArrayList<>();
      this.fs = new ArrayList<>();
  }

    public String toString() {
      return "Patient{" +
              "nom='" + nom + '\'' +
              ", prenom='" + prenom + '\'' +
              ", dateN='" + dateN + '\'' +
              ", lieuN='" + lieuN + '\'' +
              ", adresse='" + adresse + '\'' +
              ", id=" + id +
              '}';
  }

    public int getID() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }


    // Define getters and setters for id, nom, prenom

}

