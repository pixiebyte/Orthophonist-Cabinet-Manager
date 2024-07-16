package com.example.poo.Models;

import java.io.Serializable;
import java.util.Scanner;

public class TestSimple extends Test  implements Serializable{


    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
