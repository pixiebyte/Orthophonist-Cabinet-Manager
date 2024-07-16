package com.example.poo.Models;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Suivi extends RendezVous  {
    List<Objectif> selected = new LinkedList<>();
    public boolean type; // presentiel/en ligne
    public FicheSuivi fs;

    public void SelectObjectifs() {
        Scanner sc = new Scanner(System.in);
        fs.AfficherObjectifs(fs.objectifs);
        System.out.println("veillez selectionner : ");
        String input = sc.nextLine();

        String[] selectedNumbers = input.split("\\s+");
        for (String number : selectedNumbers) {
            int index = Integer.parseInt(number) - 1;
            if (index >= 0 && index < fs.objectifs.length) {
                selected.add(fs.objectifs[index]);
            }
        }
        fs.AfficherObjectifs(selected.toArray(new Objectif[0]));
        sc.close();
    }

    public void ShowSelectedObjectifs() {
        Scanner sc = new Scanner(System.in);
        int i =0;
        for (Objectif objectif : selected) {
            StringBuilder objectifbuilder = new StringBuilder("\n");
            objectifbuilder.append(i + 1).append("- ").append(objectif.nom ).append("\t\t").append(objectif.terme).append("\t\t").append(objectif.note);
            String obj = objectifbuilder.toString();
            System.out.println(obj);
            objectif.note=sc.nextInt();
        } 
        sc.close();
 
    }

    public void JugerObjectifs(){
        Scanner sc = new Scanner(System.in);
        fs.AfficherObjectifs(fs.objectifs);
        System.out.println(" comment vous jugez les ojbectifs ?\n 'done': objectifs etteint   'not' : pas encore ");
        String reponse = sc.next();
        if(reponse=="done"){
            //sauvgarder sans les fichiers 
            System.out.println(" la fiche de suivi est bien sauvgarder un autre est crée , voulez vous créer une nouvelle ? oui/non ");
            reponse=sc.next();
            if (reponse=="oui"){
                fs.CreerFicheSuivi();
            }
        }else{
            
            System.out.println(" la fiche de suivi est bien gardée ");

        }
    }
}
