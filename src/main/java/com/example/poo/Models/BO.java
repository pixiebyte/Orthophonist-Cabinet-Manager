package com.example.poo.Models;

import java.io.Serializable;
import java.util.Scanner;

public class BO implements Serializable {
    
    protected Epreuve epreuve;
    protected  Patient patient;
    protected Trouble trouble;
    protected  String projet_therapeutique;
    protected BO_Anamnese anamnese;


    public Epreuve getE() {
        return this.epreuve;
    }

    public void setE(Epreuve e) {
        this.epreuve = e;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


    public String getProjet_therapeutique() {
        return this.projet_therapeutique;
    }

    public void setProjet_therapeutique() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("veillez noter votre projet therapeutique de ce patient :  ");
        this.projet_therapeutique = scanner.next();
        scanner.close();

    }
}
