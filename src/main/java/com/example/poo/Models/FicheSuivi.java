package com.example.poo.Models;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.io.*;

public class FicheSuivi implements Serializable{
    protected Objectif[] objectifs;
    protected int evaluation;


//************************************ Creer fiche suivi*********************************************************** */

    public void CreerFicheSuivi() {
        Scanner sc = new Scanner(System.in);
        String reponse;

        System.out.print("Combien d'objectifs vous allez entrer? "); // nombre objectifs
        objectifs = new Objectif[sc.nextInt()];
        sc.nextLine();  // Consume the newline character left by nextInt()

        int i = 0;

        while (i < objectifs.length) {
            System.out.print("Veuillez entrer l'objectif (cliquez 0 pour arrêter) "); // remplir objectif
            reponse = sc.nextLine();
            if (reponse.equals("0")) {
                break;
            }
            Objectif objectif = new Objectif();
            objectif.nom = reponse;

            System.out.print("Quelle est sa catégorie? 1. Long terme 2. Moyen terme 3. Court terme "); // choisir categorie
            reponse = sc.nextLine();
            switch (reponse) {
                case "1":
                    objectif.terme = CategObj.LONG;
                    break;
                case "2":
                    objectif.terme = CategObj.MOYEN;
                    break;
                case "3":
                    objectif.terme = CategObj.COURT;
                    break;
                default:
                    System.out.println("Catégorie invalide, réessayez.");
                    continue;  // Skip the rest of the loop and retry
            }

            objectifs[i] = objectif;
            i++;
        }

        // Resize the array to match the number of entered objectives if stopped early
        
        if (i < objectifs.length) {
            objectifs = Arrays.copyOf(objectifs, i);
        }
        // ObjectOutputStream out ;
        // try {
        //     out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Anamnese.dat"))));
        //     out.writeObject(this);

        // } catch (FileNotFoundException e) {
        //  e.printStackTrace();
        // }catch (IOException e){
        //     e.printStackTrace();
        // }

    }


//************************************Afficher objectifs*********************************************************** */

    public void AfficherObjectifs(Objectif[] objectifs) {
        for (int i = 0; i < objectifs.length; i++) {
            if (objectifs[i] != null) {
                StringBuilder objectifbuilder = new StringBuilder("\n");
                objectifbuilder.append(i + 1).append("- ").append(objectifs[i].nom).append("\t\t")
                        .append(objectifs[i].terme).append("\t\t").append(objectifs[i].note);
                String obj = objectifbuilder.toString();
                System.out.println(obj);
            } else {
                System.out.println((i + 1) + "- Objectif non défini");
            }
        }
    }


//************************************ Noter les objectifs*********************************************************** */


    public void NoterObjectif() {
        Scanner sc = new Scanner(System.in);
        int i;

        while (true) {
            AfficherObjectifs(objectifs);
            System.out.print("Veuillez entrer l'objectif que vous voulez noter (tapez 0 pour quitter) ");
            i = sc.nextInt();
            if (i == 0) {
                break;
            }
            if (i > 0 && i <= objectifs.length) {
                System.out.print("Veuillez entrer la note (entre 1 et 5)pour l'objectif " + i + ": ");
                objectifs[i - 1].note = sc.nextInt();
            } else {
                System.out.println("Numéro d'objectif invalide, réessayez.");
            }
        }
    }

  

}