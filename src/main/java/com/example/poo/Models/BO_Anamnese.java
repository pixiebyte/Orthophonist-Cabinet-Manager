package com.example.poo.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;


public  class BO_Anamnese   implements Serializable {
    
    protected ArrayList<QuestionnaireEnfant> questionnaireenfant = new ArrayList<QuestionnaireEnfant>();
    protected ArrayList<QuestionnaireAdulte> questionnaireadulte = new ArrayList<QuestionnaireAdulte>();

}
