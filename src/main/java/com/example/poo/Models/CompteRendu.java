package com.example.poo.Models;

import java.io.Serializable;

public class CompteRendu implements Serializable {
    
    private String nom;
    private int score;
    private Test test;

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Test getTest() {
        return this.test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

}
