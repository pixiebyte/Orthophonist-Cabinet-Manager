package com.example.poo.Models;

import java.io.Serializable;

class Test  implements Serializable{
     private String nom;
     private String capacite;
     protected String question;



     public String getNom() {
          return this.nom;
     }

     public void setNom(String nom) {
          this.nom = nom;
     }

     public String getCapacite() {
          return this.capacite;
     }

     public void setCapacite(String capacite) {
          this.capacite = capacite;
     }

}
