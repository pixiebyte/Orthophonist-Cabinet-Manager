package com.example.poo.Models;

import java.io.Serializable;

public class QCU extends TestSimple implements Serializable{
    
    public int reponse;

    public int getReponse() {
        return this.reponse;
    }

    public void setReponse(int reponse) {
        this.reponse = reponse;
    }
}
