package mvc.view;

import automobile.metier.Pays;

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
            int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "fin"));

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
        System.out.println("sigle : ");
        String sigle = sc.nextLine();
        System.out.println("nom : ");
        String nom = sc.nextLine();
        System.out.println("langue : ");
        String langue = sc.nextLine();
        Pays pays = paysController.addPays(new Pays(0, sigle, nom, langue));
        if (pays != null) {
            affMsg("création de : " + pays);
        } else {
            affMsg("erreur de création");
        }
    }

    public void retirer() {
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
        int idPays = sc.nextInt();
        paysController.search(idPays);
    }

    public void modifier() {
        int n = choixElt(lp);
        Pays pays = lp.get(n - 1);
        String sigle = modifyIfNotBlank("sigle", pays.getSigle());
        String nom = modifyIfNotBlank("nom", pays.getNom());
        String langue = modifyIfNotBlank("langue", pays.getLangue());
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
}
