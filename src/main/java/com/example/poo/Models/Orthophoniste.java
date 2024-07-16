package com.example.poo.Models;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.*;
import java.util.NoSuchElementException;


public class Orthophoniste implements Serializable  {
    public String nom;
    public String prenom;
    public String adresse;
    public String mail;
    public String mdp;
    public  List<Patient> patients = new ArrayList<>();
    public BO bo= new BO();
    public  List<RendezVous> RDV = new ArrayList<>();



   public  Orthophoniste(String nom , String prenom , String adresse,String mail,String mdp){
        this.nom=nom;
        this.prenom=prenom;
        this.mail=mail;
        this.mdp=mdp;
        this.adresse=adresse;
    }

    public String toString() {
        return "Orthophoniste{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", mail='" + mail + '\'' +
                ", mdp='" + mdp + '\'' +
                ", bo=" + bo +
                ", patients=" + patients +
                '}';
    }

    


    // ********************************************* questions personnel anamnese *********************************************
    public void RemplireInfo(Patient p) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nom :");
        p.nom = scanner.nextLine();

        System.out.println("Prénom :");
        p.prenom = scanner.nextLine();

        System.out.println("Date de naissance :");
        p.dateN = scanner.nextLine();

        System.out.println("Lieu de naissance :");
        p.lieuN = scanner.nextLine();

        System.out.println("Adresse :");
        p.adresse = scanner.nextLine();

        scanner.close();
    }




// ********************************************* Afficher anamnese *********************************************

    public static void AfficherAnamneseFichier() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Anamnese.dat"))) {
            ArrayList<Questionnaire> tab = (ArrayList<Questionnaire>) in.readObject();
            System.out.println("Anamnese List:");
            for (Questionnaire questionnaire : tab) {
                System.out.println("\nEnonce: " + questionnaire.getQuestion());
                System.out.println("Reponse: " + questionnaire.getReponse());
      
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

// ********************************************* poser questions  Anamnese *********************************************

    public void PoserQuestionsAnamnese(Patient p) {
        Scanner scanner = new Scanner(System.in);
        //read from the file

        System.out.println("Tapez E pour enfant, A pour adulte");
        String reponse = scanner.nextLine();

        if (reponse.equals("E")) {
            Enfant patient = new Enfant();
            RemplireInfo(patient);

            System.out.println("Classe d'étude :");
            patient.classesEtude = scanner.nextLine();
            System.out.println("Numéro de téléphone des parents :");
            patient.numTelParents = scanner.nextLine();

            for (int i = 0; i < bo.anamnese.questionnaireenfant.size(); i++) {
                System.out.println("Voici les questions d'anamnèse pour enfant, veuillez entrer les réponses s'il vous plaît:");
                System.out.println(bo.anamnese.questionnaireenfant.get(i).getQuestion());
                reponse = scanner.nextLine();
            }
        } else if (reponse.equals("A")) {
            Adulte patient = new Adulte();
            RemplireInfo(patient);

            for (int i = 0; i < bo.anamnese.questionnaireadulte.size(); i++) {
                System.out.println("Voici les questions d'anamnèse pour adulte, veuillez entrer les réponses s'il vous plaît:");
                System.out.println(bo.anamnese.questionnaireadulte.get(i).getQuestion());
                reponse = scanner.nextLine();
            }
        }

        patients.add(p);
        // Don't close the scanner here
    }
    
// ********************************************* poser questions simples *********************************************
public void PoserQuestionsSimple(Patient patient) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Voici les questions simples de l'épreuve, veuillez entrer les réponses s'il vous plaît:");




 if (this.bo==null){
     BO bo = new BO();
 }
 if (this.bo.epreuve==null){
     this.bo.epreuve= new Epreuve();
 }
    System.out.println("********QCM*******");

    for(QCM elt :this.bo.epreuve.qcms){
        System.out.println(elt.question);
        System.out.println(elt.reponses);
        //noter
    }
    System.out.println("********QCU*******");

    for(QCU elt :bo.epreuve.qcus){
        System.out.println(elt.question);
        System.out.println(elt.reponse);
        //noter
    }
    System.out.println("******** Questions a reponses libres*******");

    for(ReponseLibre elt :bo.epreuve.replibre){
        System.out.println(elt.question);
        System.out.println(elt.reponse);
        //noter
    }


    scanner.close();
}


// ********************************************* poser questions exo *********************************************

public void PoserQuestionsExo(Patient patient) {
Scanner s = new Scanner(System.in);

for (int i = 0; i < bo.epreuve.testexo.size(); i++) {
    int j = 0;
    do {
        System.out.println("Voici les questions exo de l'épreuve,");
        System.out.println(bo.epreuve.testexo.get(i).question+bo.epreuve.testexo.get(i).question);
        String reponse = s.nextLine();
        j++;
    } while (j < bo.epreuve.testexo.get(i).rep);
}
}



//********************************************** Passer Epreuve *******************************************
    public void PasserEpreuve(Patient patient) {
        PoserQuestionsSimple(patient);
        PoserQuestionsExo(patient);
    }



//********************************************* Lancer un BO *********************************************

    public void Lancer_BO(Patient patient) {
        if (!patients.contains(patient)) {
            PoserQuestionsAnamnese(patient);
        }
        PasserEpreuve(patient);
    }


// ******************************************* Decision de prise en charge ou pas ************************************/
    public void PriseEnCharge() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Est-ce que vous allez prendre en charge ce patient? Tapez oui / non");
        String reponse = sc.next();
        if (reponse.equals("oui")) {
            // Ajouter rendez-vous à la liste du patient
            // Ajouter BO à la liste du patient
        }
        sc.close();
    }

	
//****************************************************** sauvegarder patient **********************************************************/

public void SauvegarderPatient(Patient patient,RendezVous rdv, BO bo, FicheSuivi fs ){
    
    patient.RemplireDossier(rdv, bo, fs);
    patients.add(patient);
    ObjectOutputStream out ;
    try {
        out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Patients.dat"))));
        out.writeObject(patients);

    } catch (FileNotFoundException e) {
     e.printStackTrace();
    }catch (IOException e){
        e.printStackTrace();
    }


}


//****************************************************** Afficher liste patients **********************************************************/


public static void ConsulterDossierPatient() {
    try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Patients.dat"))))) {
        ArrayList<Patient> patients = (ArrayList<Patient>) in.readObject();
        System.out.println("List of Patients:");
        for (Patient patient : patients) {
            System.out.println(patient.toString());
            System.out.println("\n************************************************************************\n");

        }
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}

//****************************************************** creer anamnese **********************************************************/
public void CreerAnamnese() {
    Scanner sc = new Scanner(System.in);
    String reponse1;
    String reponse2;
    if (this.bo == null) {
        this.bo = new BO();
    }
    if (this.bo.anamnese == null) {
        this.bo.anamnese = new BO_Anamnese();
    }
    if (this.bo.anamnese.questionnaireenfant == null) {
        this.bo.anamnese.questionnaireenfant = new ArrayList<>();
    }
    if (this.bo.anamnese.questionnaireadulte == null) {
        this.bo.anamnese.questionnaireadulte = new ArrayList<>();
    }

     do {
        System.out.println("Anamnèse E:enfant A:adulte?(Q= quitter anamnese)");
        reponse1 = sc.nextLine();
        if (reponse1.equals("Q")) {
            break;
        }

        switch (reponse1) {
            case "E":
                do {
                    System.out.println("Entrez énoncé, (0 si vous voulez quitter)");
                    reponse1 = sc.next();

                    if (reponse1.equals("0")) {
                        break;
                    }
                    System.out.println("Entrez la réponse attendue par le patient");
                    reponse2 = sc.next();

                    bo.anamnese.questionnaireenfant.add(new QuestionnaireEnfant(reponse1, reponse2));
                } while (true);
                break;

            case "A":
                do {
                    System.out.println("Entrez énoncé, (0 si vous voulez quitter)");
                    reponse1 = sc.next();

                    if (reponse1.equals("0")) {
                        break;
                    }
                    System.out.println("Entrez la réponse attendue par le patient");
                    reponse2 = sc.next();

                    bo.anamnese.questionnaireadulte.add(new QuestionnaireAdulte(reponse1, reponse2));
                } while (true);
                break;

            case "Q":
                break;
            default:
                break;
        }
    } while (true);

    sc.close();
}


   


//****************************************************** Creer teste simple  orthophoniste (QCU QCM Reponse libre )**********************************************************/


public void CreerTestSimple(Scanner scanner) {
    if (this.bo.epreuve == null) {
        this.bo.epreuve = new Epreuve();
    }

    System.out.println("1-QCM  2- QCU   3-Questions a reponse libre 4-quitter: ");
    int question = scanner.nextInt();
    scanner.nextLine(); // Consume newline character

    switch (question) {
        case 1: // QCM
            QCM qcm = new QCM();
            System.out.println("veuillez entrer l'énoncé de la question : ");
            String temp = scanner.nextLine();

            System.out.println("combien de choix y en a-t-il ");
            int number = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            StringBuilder questionBuilder = new StringBuilder(temp + "\n");

            for (int i = 1; i <= number; i++) {
                System.out.println("choix num " + i);
                String choice = scanner.nextLine();
                questionBuilder.append(i).append("- ").append(choice).append("\n");
            }

            System.out.println("entrez les numeros de la bonne reponse");
            String input = scanner.nextLine();

            String[] tokens = input.split(" ");
            for (String token : tokens) {
                try {
                    int responseNum = Integer.parseInt(token);
                    qcm.reponses.add(responseNum);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number: " + token + ". Skipping.");
                }
            }

            this.bo.epreuve.qcms.add(qcm);
            qcm.question = questionBuilder.toString();
            System.out.println("La question avec les choix est :");
            System.out.println(qcm.question);

            break;

        case 2: // QCU
            QCU qcu = new QCU();
            System.out.println("veuillez entrer l'énoncé de la question : ");
            String qcuQuestion = scanner.nextLine();
            qcu.question = qcuQuestion;

            System.out.println("combien de choix y en a-t-il ");
            int qcuNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            questionBuilder = new StringBuilder(qcuQuestion + "\n");

            for (int i = 1; i <= qcuNumber; i++) {
                System.out.println("choix num " + i);
                String choice = scanner.nextLine();
                questionBuilder.append(i).append("- ").append(choice).append("\n");
            }

            System.out.println("entrez le numero de la bonne reponse");
            int qcuResponse = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            qcu.reponse = qcuResponse;

            this.bo.epreuve.qcus.add(qcu);
            qcu.question = questionBuilder.toString();
            System.out.println("La question avec les choix est :");
            System.out.println(qcu.question);

            break;

        case 3: // Questions a reponse libre
            ReponseLibre rl = new ReponseLibre();
            System.out.println("veuillez entrer l'énoncé de la question : ");
            rl.question = scanner.nextLine();
            System.out.println("veuillez entrer la reponse correcte : ");
            rl.reponse = scanner.nextLine();
            this.bo.epreuve.replibre.add(rl);
            break;

        default:
            break;
    }
}

//********************************************* Creer epreuve************************************************************/


     public void CreerTestExo(Scanner scanner) {
        System.out.println("veuillez créer des tests exo");
        System.out.println("veuillez entrer le nom du materiel utilisé (Q pour quitter)");
        String materiel = scanner.next();
        if (materiel.equals("Q")) {
            return;
        }
        System.out.println("veuillez entrer le nombre des répétitions");
        int rep = scanner.nextInt();
        TestExo testexo = new TestExo(materiel, rep);
        this.bo.epreuve.testexo.add(testexo);
        scanner.nextLine(); // Consume newline character
    }

//********************************************* Creer epreuve************************************************************/
public void CreerEpreuve() {
    Scanner scan = new Scanner(System.in);

    try {
        while (true) {
            System.out.println("Voulez-vous créer un test simple ? oui/non");
            if (!scan.hasNextLine()) {
                break;
            }
            String reponse = scan.nextLine().trim();
            if (reponse.equalsIgnoreCase("non")) {
                break;
            }
            if (reponse.equalsIgnoreCase("oui")) {
                CreerTestSimple(scan);
            }
        }

        while (true) {
            System.out.println("Voulez-vous créer un test exo ? oui/non");
            if (!scan.hasNextLine()) {
                break;
            }
            String reponse = scan.nextLine().trim();
            if (reponse.equalsIgnoreCase("non")) {
                break;
            }
            if (reponse.equalsIgnoreCase("oui")) {
                CreerTestExo(scan);
            }
        }
    } catch (NoSuchElementException e) {
        System.err.println("No more input available.");
    } finally {
        scan.close(); // Ensure the scanner is closed to avoid resource leaks
    }
}



//********************************************* Modifier Aanamese ***********************************************************8 */
public void ModifierAnamnese() {
    Scanner sc = new Scanner(System.in);
    String reponse1;
    String reponse2;

    System.out.println("Anamnèse enfant adulte?");
     reponse1 = sc.next();
     ArrayList<Questionnaire> tab = new ArrayList<Questionnaire>();
     int i=1;
    switch (reponse1) {            //adulte ou enfant
        case "enfant":
        for (QuestionnaireEnfant questionnaire : bo.anamnese.questionnaireenfant) {
            System.out.println(i+"- Enonce: " + questionnaire.getQuestion());
            System.out.println("\nReponse: " + questionnaire.getReponse()+"\n");
  
        }   
        System.out.println("M:modifier , S: supprimer , A:ajouter question " );     
        String reponse = sc.next();

        switch (reponse) {
                case "M":                                  
                    System.out.println("entrez le numero de la question que voux voulez modifier  " );   //modifier question
                    int number = sc.nextInt();

                    System.out.println("Entrez énoncé");
                    reponse1 = sc.next();

                    System.out.println("Entrez la reponnse attendu par le patient )");
                    reponse2 = sc.next();

                    bo.anamnese.questionnaireenfant.add(number-1 ,new QuestionnaireEnfant(reponse1 , reponse2));
                    break;

                case "S":
                    System.out.println("entrez le numero de la question que voux voulez supprimer  " );   //supprimer questions
                    number = sc.nextInt();
                    bo.anamnese.questionnaireenfant.remove(number-1);

                    break;
                case "A":

                    System.out.println("Entrez énoncé");                                                                         //ajouter question
                    reponse1 = sc.next();

                    System.out.println("Entrez la reponnse attendu par le patient )");
                    reponse2 = sc.next();

                    bo.anamnese.questionnaireenfant.add(new QuestionnaireEnfant(reponse1 , reponse2));
                break;
            
                default:
                    break;
        
                }
                 break;

        case "adulte":
                for (QuestionnaireAdulte questionnaire : bo.anamnese.questionnaireadulte) {            //
                    System.out.println(i+"Enonce: " + questionnaire.getQuestion());
                    System.out.println("Reponse: " + questionnaire.getReponse()+"\n");
        
                }  

                System.out.println("M:modifier , S: supprimer , A:ajouter question " );     
                 reponse = sc.next();
        
                switch (reponse) {
                        case "M":                                  
                            System.out.println("entrez le numero de la question que voux voulez modifier  " );   //modifier question
                            int number = sc.nextInt();
        
                            System.out.println("Entrez énoncé");
                            reponse1 = sc.next();
        
                            System.out.println("Entrez la reponnse attendu par le patient )");
                            reponse2 = sc.next();
        
                            bo.anamnese.questionnaireadulte.add(number-1 ,new QuestionnaireAdulte(reponse1 , reponse2));
                            break;
        
                        case "S":
                            System.out.println("entrez le numero de la question que voux voulez supprimer  " );   //supprimer questions
                            number = sc.nextInt();
                            bo.anamnese.questionnaireadulte.remove(number-1);
        
                            break;
                        case "A":
        
                            System.out.println("Entrez énoncé");                                                                         //ajouter question
                            reponse1 = sc.next();
        
                            System.out.println("Entrez la reponnse attendu par le patient )");
                            reponse2 = sc.next();
        
                            bo.anamnese.questionnaireadulte.add(new QuestionnaireAdulte(reponse1 , reponse2));
                        break;
                    
                        default:
                            break;
                
                        }

        break;

        default:
            break;
    }


}

//********************************************* Modifier Test epreuve ***********************************************************8 */
public void ModifierQCM() {

    Scanner scanner = new Scanner(System.in);
    String reponse1;
    String reponse2;


    System.out.println("********QCM*******");    //print

    for(QCM elt :bo.epreuve.qcms){
        System.out.println(elt.question);
        System.out.println(elt.reponses);
        //noter
    }
    System.out.println("M:modifier , S: supprimer , A:ajouter question " );     
    String reponse = scanner.next();
    QCM qcm =new QCM();


    switch (reponse) {
            case "M":                                  
                System.out.println("entrez le numero de la question que voux voulez modifier  " );   //modifier question
                int num = scanner.nextInt();

               

                System.out.println("combien de choix y en a t'il ");         //nombre des choix------------------------------------------- 
                 int number =scanner.nextInt();



                System.out.println("veillez entrez l'enconcé de la question  : ");     //enonce question------------------------------------------- 
                String question =scanner.nextLine();

                StringBuilder questionBuilder = new StringBuilder(question+"\n");     //construire  question+les choix------------------------------------------- 
                for(int i=1 ; i<(number+1) ; i++){
                    System.out.println("choix num "+ i);
                    String choice = scanner.next();
                    questionBuilder.append(i).append("- ").append(choice).append("\n");
                }

                qcm.question=questionBuilder.toString();

                System.out.println("entrez les numeros de la bonne reponse");   //les choix  correcte------------------------------------------- 
                String input =scanner.next();

                String[] tokens = input.split(" ");
                for (String token : tokens) {               
                    try {
                        number = Integer.parseInt(token);
                        qcm.reponses.add(number);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number: " + token + ". Skipping.");
                    }
                }
                    bo.epreuve.qcms.add(num-1 ,qcm);                       //add it to the arraylist-------------------------------------------
                    break;

            case "S":
                System.out.println("entrez le numero de la question que voux voulez supprimer  " );   //supprimer questions
                number = scanner.nextInt();
                bo.epreuve.qcms.remove(number-1);

                break;
            case "A":


                System.out.println("combien de choix y en a t'il ");         //nombre des choix------------------------------------------- 
                 number =scanner.nextInt();

                System.out.println("veillez entrez l'enconcé de la question  : ");     //enonce question------------------------------------------- 
                 question =scanner.nextLine();

                 questionBuilder = new StringBuilder(question+"\n");     //construire  question+les choix------------------------------------------- 
                for(int i=1 ; i<(number+1) ; i++){
                    System.out.println("choix num "+ i);
                    String choice = scanner.next();
                    questionBuilder.append(i).append("- ").append(choice).append("\n");
                }

                qcm.question=questionBuilder.toString();

                System.out.println("entrez les numeros de la bonne reponse");   //les choix  correcte------------------------------------------- 
                 input =scanner.next();

                 tokens = input.split(" ");
                for (String token : tokens) {               
                    try {
                        number = Integer.parseInt(token);
                        qcm.reponses.add(number);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number: " + token + ". Skipping.");
                    }
                }
                bo.epreuve.qcms.add(qcm);                       //add it to the arraylist-------------------------------------------
                    break;
        
            default:
                break;
    
            }


}

//********************************************* Modifier QCU   ***********************************************************8 */

public void ModifierQCU() {

    Scanner scanner = new Scanner(System.in);
    String reponse1;
    String reponse2;


    System.out.println("********QCU*******");    //print

    for(QCU elt : bo.epreuve.qcus){
        System.out.println(elt.question);
        System.out.println(elt.reponse);
        //noter
    }
    System.out.println("M:modifier , S: supprimer , A:ajouter question " );     
    String reponse = scanner.next();
    QCU qcu =new QCU();


    switch (reponse) {
            case "M":                                  
                System.out.println("entrez le numero de la question que voux voulez modifier  " );   //modifier question
                int num = scanner.nextInt();
                

                System.out.println("combien de choix y en a t'il ");         //nombre des choix------------------------------------------- 
                 int number =scanner.nextInt();

                System.out.println("veillez entrez l'enconcé de la question  : ");     //enonce question------------------------------------------- 
                String question =scanner.nextLine();

                StringBuilder questionBuilder = new StringBuilder(question+"\n");     //construire  question+les choix------------------------------------------- 
                for(int i=1 ; i<(number+1) ; i++){
                    System.out.println("choix num "+ i);
                    String choice = scanner.next();
                    questionBuilder.append(i).append("- ").append(choice).append("\n");
                }

                qcu.question=questionBuilder.toString();

                System.out.println("entrez le numero de la bonne rponse");   //nombre des choix
                number =scanner.nextInt();
                qcu.reponse=number;

                bo.epreuve.qcus.add(num-1,qcu);    //add it to the arraylist of questions
    
    
                qcu.question = questionBuilder.toString();                  //print it
                System.out.println("La question avec les choix est :");
                System.out.println(question);
    
              break;
    

            case "S":
                System.out.println("entrez le numero de la question que voux voulez supprimer  " );   //supprimer questions
                number = scanner.nextInt();
                bo.epreuve.qcus.remove(number-1);

                break;
            case "A":


            System.out.println("combien de choix y en a t'il ");         //nombre des choix------------------------------------------- 
             number =scanner.nextInt();

           System.out.println("veillez entrez l'enconcé de la question  : ");     //enonce question------------------------------------------- 
            question =scanner.nextLine();

            questionBuilder = new StringBuilder(question+"\n");     //construire  question+les choix------------------------------------------- 
           for(int i=1 ; i<(number+1) ; i++){
               System.out.println("choix num "+ i);
               String choice = scanner.next();
               questionBuilder.append(i).append("- ").append(choice).append("\n");
           }

           qcu.question=questionBuilder.toString();

           System.out.println("entrez le numero de la bonne rponse");   //nombre des choix
           number =scanner.nextInt();
           qcu.reponse=number;

           scanner.close();
                bo.epreuve.qcus.add(qcu);    //add it to the arraylist of questions


           qcu.question = questionBuilder.toString();                  //print it
           System.out.println("La question avec les choix est :");
           System.out.println(question);

         break;
        
            default:
                break;
    
            }


}

//********************************************* Modifier reponse libre  ***********************************************************8 */

public void ModifierReponseLibre(){

    System.out.println("******** Questions a reponses libres*******");

    int i=1;
    for(ReponseLibre elt : bo.epreuve.replibre){
        System.out.println(i+"-"+elt.question);
        System.out.println(elt.reponse);
        i++;
        //noter
    }
    Scanner scanner = new Scanner(System.in);

    System.out.println("M:modifier , S: supprimer , A:ajouter question " );     
    String reponse = scanner.next();
    ReponseLibre reponselibre =new ReponseLibre();

    switch (reponse) {
        case "M":
            System.out.println("entrez le numero de la question que voux voulez modifier  " );   //modifier question
            int num = scanner.nextInt();
            ReponseLibre rl = new ReponseLibre();

            System.out.println("veillez entrez l'enconcé de la question  : ");  //entrez questions
            rl.question =scanner.next();
            System.out.println("veillez entrez la reponse correcte : ");     //entrez reponse
            rl.reponse =scanner.next();
            bo.epreuve.replibre.add(num-1,rl);

            break;
        
        case "S":
            System.out.println("entrez le numero de la question que voux voulez supprimer  " );   //modifier question
            num = scanner.nextInt();
            bo.epreuve.replibre.remove(num-1);

            break;


        case "A":

            rl = new ReponseLibre();
            System.out.println("veillez entrez l'enconcé de la question  : ");  //entrez questions
            rl.question =scanner.next();
            System.out.println("veillez entrez la reponse correcte : ");     //entrez reponse
            rl.reponse =scanner.next();
            bo.epreuve.replibre.add(rl);
            break;
    
        default:
            break;
    }

}

//********************************************* Modifier epreuve ***********************************************************8 */

 public void ModifierEpreuve(){
    Scanner sc = new Scanner(System.in);
    System.out.println(" modifier  1- qcm ,2- qcu , 3-quitter");
    int choix =  sc.nextInt();
    switch (choix) {
        case 1:
            ModifierQCM();
            break;
        case 2:
            ModifierQCU();
            break;
        case 3:
            ModifierReponseLibre();
            break;
        default:
            break;
    }

    }







    //********************************************* Creer BO ***********************************************************8 */


    public void CreerBO(){

        CreerAnamnese();



        CreerEpreuve();
    }

    public List<Patient> getPatients() {
       return patients;
    }
}

///////// afficher fiche suivi 