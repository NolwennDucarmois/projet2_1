package mvc.view;

import automobile.metier.Pays;
import automobile.metier.Pilote;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class PaysViewConsole extends PaysAbstractView {
    private Scanner sc = new Scanner(System.in);

    @Override
    public void affMsg(String msg) {
        System.out.println("information : " + msg);
    }

    @Override
    public Pays selectionner() {
        update(paysController.getAll());
        int n = choixListe(lp);
        Pays pays = lp.get(n - 1);
        return pays;
    }

    @Override
    public void menu() {
        update(paysController.getAll());
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
        System.out.println("Sigle du pays : ");
        String sigle = sc.nextLine();
        System.out.println("Nom du pays : ");
        String nom = sc.nextLine();
        System.out.println("Langue : ");
        String langue = sc.nextLine();
        Pays pays = paysController.addPays(new Pays(0, sigle, nom, langue));
        if (pays != null) {
            affMsg("création de : " + pays);
        } else {
            affMsg("erreur de création");
        }
    }

    public void retirer() {
        System.out.println("Indiquez le numéro de la ligne");
        int n = choixElt(lp);
        Pays pays = lp.get(n - 1);
        boolean ok = paysController.removePays(pays);
        if (ok) {
            affMsg("pays effacé");
        } else {
            affMsg("pays non effacé");
        }
    }

    public void rechercher() {
        System.out.println("idPays : ");
        int idPays = lireInt();
        Pays p = paysController.search(idPays);
        if (p == null) {
            System.out.println("Pays non trouvé");
        } else {
            affMsg(p.toString());
            special(p);
        }
    }

    public void modifier() {
        System.out.println("Indiquez le numéro de la ligne");
        int n = choixElt(lp);
        Pays pays = lp.get(n - 1);
        String sigle = modifyIfNotBlank("Sigle du pays : ", pays.getSigle());
        String nom = modifyIfNotBlank("Nom du pays : ", pays.getNom());
        String langue = modifyIfNotBlank("Langue : ", pays.getLangue());
        Pays maj = paysController.update(new Pays(pays.getIdPays(), sigle, nom, langue));
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

    public void special(Pays p) {
        do {
            int choix = choixListe(Arrays.asList("Liste des pilotes du pays", "Menu principal"));
            switch (choix) {
                case 1 -> listePilotes(p);
                case 2 -> {
                    return;
                }
            }
        } while (true);
    }

    public void listePilotes(Pays pays) {
        List<Pilote> liste = paysController.listePilotes(pays);
        if (liste.isEmpty()) {
            System.out.println("La liste est null\n");
        } else {
            System.out.println("Liste des pilotes du pays " + pays.getNom() + " : ");
            System.out.println(liste + "\n");
        }
    }
}
