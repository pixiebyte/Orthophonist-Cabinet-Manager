package com.example.poo.Models;

import java.io.Serializable;

public class QuestionnaireEnfant extends Questionnaire implements Serializable{ 
   private  Categ_E type;
   
   QuestionnaireEnfant(String e , String r){
      super(e,r);
  }
}
