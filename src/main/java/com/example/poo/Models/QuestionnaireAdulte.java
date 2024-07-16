package com.example.poo.Models;

import java.io.Serializable;

public class QuestionnaireAdulte extends Questionnaire implements Serializable {
    private Categ_A type;

    QuestionnaireAdulte(String e , String r){
        super(e,r);
    }
}
