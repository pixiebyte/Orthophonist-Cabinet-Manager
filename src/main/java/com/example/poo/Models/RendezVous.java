package com.example.poo.Models;

import java.io.Serializable;

public class RendezVous implements Serializable  {

    public String date;
    public String heure;
    public String observation;
    public Patient patients[];
    public String Dur;
    public String  type ;


    RendezVous(){
    }

    public RendezVous(String date, String heure, String observation, String Dur,String type){
            this.date = date;
            this.heure = heure;
            this.observation = observation;
            this.Dur = Dur;
            this.type = type;

    }
    public String getDate() {
        return date;
    }

    public String getHeure() {
        return heure;
    }

    public String getObservation() {
        return observation;
    }

    public String getDur() {
        return Dur;
    }

    public String gettype() {
        return type;
    }
}