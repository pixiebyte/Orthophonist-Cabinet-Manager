package com.example.poo.Models;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.Serializable;


public class Main implements Serializable {

    public  TreeMap<String, Orthophoniste> orthophonistes = new TreeMap<>();
    transient public  TreeMap<String, Orthophoniste> test = new TreeMap<>();



    public void SauvegarderOrthophonistes() {
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("orthophonistes.dat")))) {
            out.writeObject(orthophonistes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load orthophonistes TreeMap from a .dat file
    public void RecupererOrthophoniste() {
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("orthophonistes.dat")))) {
            orthophonistes = (TreeMap<String, Orthophoniste>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



//******************************************************** Authentifier *********************************************************8 */


public Orthophoniste Authentifier(String email , String mdp) {


//    System.out.println("      Bienvenu ! ");

    RecupererOrthophoniste(); // Load orthophonistes from file

//    int i = 0;

//    do {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Veillez entrer votre adresse email  : ");
//        email = sc.next();
//
//        System.out.println("Veillez entrer votre mot de passe : ");
//        mdp = sc.next();
//        i++;
//        if (i == 3) {
//            System.exit(0);
//        }
//    } while (!(orthophonistes.containsKey(email)) || !(mdp.equals(orthophonistes.get(email).mdp)));
           if (orthophonistes.containsKey(email)) { return orthophonistes.get(email);}
           else {return null;}
}



//******************************************************** Creer Compte *********************************************************8 */

    public void CreerCompte(String nom,String prenom, String adresse, String email, String mdp){
        Orthophoniste ortho = new  Orthophoniste(nom ,prenom , adresse,email,mdp);
        orthophonistes.put(email,ortho);
        SauvegarderOrthophonistes();

    }

//******************************************************** Menu **********************************************************/

    public int Menu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("************************************* MENU ****************************************");
        System.out.println("1- Afficher la liste de mes patients ");
        System.out.println("2- Afficher la liste de mes rendez-vous");
        System.out.println("3- Commencer une Seance ");
        System.out.println("-----entrez un choix :");
        int reponse = sc.nextInt();
        return reponse;

    }

//******************************************************** print orthoponiste **********************************************************/

    public void printOrthophonistes() {
        System.out.println("List of Orthophonistes:");
        for (String key : orthophonistes.keySet()) {
            System.out.println("Email: " + key);
            System.out.println("Orthophoniste: " + orthophonistes.get(key).toString());
            System.out.println("\n************************************************************************\n");
        }
    }
//******************************************************** MAINNNNNN *********************************************************8 */



    public static void main(String[] args) {
       

        Main main =new Main();
        main.CreerCompte("ryma", "felkir", "BEK", "mr_felkir@esi.dz", "admin");
        Orthophoniste ortho = main.Authentifier("mr_felkir@esi.dz", "admin");
        main.printOrthophonistes();

        Patient p1= new Patient("felkir", "ryma", "09/07/2004", "alg",1234567890)  ;
        Patient p2= new Patient("felkir", "racha", "09/07/2004", "alg",456457890)  ;
        Patient p3= new Patient("maachi", "aymen", "03/06/2004", "alg",1234567890)  ;

        ortho.patients.add(p1);
        ortho.patients.add(p2);
        ortho.patients.add(p3);

        ortho.CreerBO();
        main.SauvegarderOrthophonistes();

        ortho.Lancer_BO(p1);





    }
}