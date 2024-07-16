package com.example.poo.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Epreuve implements Serializable {

    protected ArrayList<QCM> qcms = new ArrayList<>();
    protected ArrayList<QCU> qcus = new ArrayList<>();
    protected ArrayList<ReponseLibre> replibre = new ArrayList<>();
    protected ArrayList<TestExo> testexo = new ArrayList<>();
    
    private String observation;
    private int scoretotal;
    private String conclusion;
    private ArrayList<CompteRendu> compterendu = new ArrayList<>();



    public void RemplireObservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer votre observation sur cette épreuve:");
        this.observation = scanner.nextLine();
        scanner.close();
    }

    public ArrayList<QCM> getQcms() {
        return qcms;
    }

    public ArrayList<QCU> getQcus() {
        return qcus;
    }

    public ArrayList<ReponseLibre> getReplibre() {
        return replibre;
    }

    public ArrayList<TestExo> getTestexo() {
        return testexo;
    }

    public String getObservation() {
        return observation;
    }

    public int getScoretotal() {
        return scoretotal;
    }

    public String getConclusion() {
        return conclusion;
    }

    public ArrayList<CompteRendu> getCompterendu() {
        return compterendu;
    }


    public ArrayList<TestSimple> getCombinedQcmAndQcu() {
        ArrayList<TestSimple> combinedList = new ArrayList<>();
        for (QCM qcm : qcms) {
            combinedList.add((TestSimple)qcm);
        }
        for (QCU qcu : qcus) {
            combinedList.add((TestSimple)qcu);
        }
        return combinedList;
    }
}
