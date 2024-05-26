package mvc.view;

import automobile.metier.Pilote;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class PiloteViewConsole extends PiloteAbstractView {
    private Scanner sc = new Scanner(System.in);

    @Override
    public void affMsg(String msg) {
        System.out.println("information : " + msg);
    }

    @Override
    public Pilote selectionner() {
        update(piloteController.getAll());
        int n = choixListe(lp);
        Pilote p = lp.get(n - 1);
        return p;
    }

    @Override
    public void menu() {
        update(piloteController.getAll());
        do {
            int ch = choixListe(Arrays.asList("Ajout", "Retrait", "Recherche", "Modification", "FIN"));
            switch (ch) {
                case 1:
                    ajouter();
                    break;
                case 2:
                    retirer();
                    break;
                case 3:
                    rechercher();
                    break;
                case 4:
                    modifier();
                    break;
                case 5:
                    return;
            }
        } while (true);
    }

    public void ajouter() {
        System.out.println("Matricule du pilote : ");
        String matricule = sc.nextLine();
        System.out.println("Nom du pilote : ");
        String nom = sc.nextLine();
        System.out.println("Prénom du pilote : ");
        String prenom = sc.nextLine();
        System.out.println("Date de naissance du pilote : ");
        System.out.println("Jour : ");
        int jour = Integer.parseInt(sc.nextLine());
        System.out.println("Mois : ");
        int mois = Integer.parseInt(sc.nextLine());
        System.out.println("Année : ");
        int annee = Integer.parseInt(sc.nextLine());
        LocalDate dateNaiss = LocalDate.of(annee, mois, jour);
        Pilote pi = piloteController.addPilote(new Pilote(0, matricule, nom, prenom, dateNaiss));
        if (pi != null) {
            affMsg("création de : " + pi);
        } else {
            affMsg("erreur de création");
        }
    }

    public void retirer() {
        System.out.println("Indiquez le numéro de la ligne");
        int n = choixElt(lp);
        Pilote pi = lp.get(n - 1);
        boolean ok = piloteController.removePilote(pi);
        if (ok) {
            affMsg("pilote effacé");
        } else {
            affMsg("pilote non effacé");
        }
    }

    public void rechercher() {
        System.out.println("idPilote : ");
        int idPilote = lireInt();
        Pilote pi = piloteController.search(idPilote);
        if (pi == null) {
            System.out.println("Pilote non trouvé");
        } else {
            System.out.println(pi);
        }
    }

    public void modifier() {
        System.out.println("Indiquez le numéro de la ligne");
        int n = choixElt(lp);
        Pilote pi = lp.get(n - 1);
        String matricule = modifyIfNotBlank("Matricule", pi.getMatricule());
        String nom = modifyIfNotBlank("Nom", pi.getNom());
        String prenom = modifyIfNotBlank("Prénom", pi.getPrenom());
        LocalDate dateNaiss = LocalDate.parse(modifyIfNotBlank("Date de naissance : ", "" + pi.getDateNaiss()));
        Pilote maj = piloteController.update(new Pilote(pi.getIdPilote(), matricule, nom, prenom, dateNaiss));
        if (maj == null) {
            affMsg("mise à jour infructueuse");
        } else {
            affMsg("mise à jour effectuée : " + maj);
        }
    }

    @Override
    public void affList(List infos) {
        affListe(infos);
    }
}
