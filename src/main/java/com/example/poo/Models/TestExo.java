package com.example.poo.Models;

import java.io.Serializable;

class TestExo extends Test  implements Serializable{

    public  String materiel;
     public int rep;

    public String getMateriel() {
        return this.materiel;
    }

    public void setMateriel(String materiel) {
        this.materiel = materiel;
    }


    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

  TestExo(){
      super();

  }
    TestExo(String materiel,int rep){
        super();
        this.materiel=materiel;
        this.rep=rep;
    }

}
