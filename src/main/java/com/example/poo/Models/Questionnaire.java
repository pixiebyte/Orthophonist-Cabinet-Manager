package com.example.poo.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import java.io.Serializable;

public class Questionnaire implements Serializable {
    private final StringProperty enonce;
    private final StringProperty reponse;

    public Questionnaire(String e, String r) {
        this.enonce = new SimpleStringProperty(e);
        this.reponse = new SimpleStringProperty(r);
    }

    public String getQuestion() {
        return enonce.get();
    }

    public void setEnonce(String enonce) {
        this.enonce.set(enonce);
    }

    public String getReponse() {
        return reponse.get();
    }

    public void setReponse(String reponse) {
        this.reponse.set(reponse);
    }

    public ObservableValue<String> questionProperty() {
        return enonce;
    }

    public ObservableValue<String> reponseProperty() {
        return reponse;
    }
}
