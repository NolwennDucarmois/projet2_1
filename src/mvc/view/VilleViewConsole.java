package mvc.view;

import automobile.metier.Ville;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class VilleViewConsole extends VilleAbstractView {
    private Scanner sc = new Scanner(System.in);

    @Override
    public void affMsg(String msg) {
        System.out.println("information : " + msg);
    }

    @Override
    public Ville selectionner() {
        update(villeController.getAll());
        int n = choixListe(lv);
        Ville v = lv.get(n - 1);
        return v;
    }

    @Override
    public void menu() {
        update(villeController.getAll());
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
        System.out.println("Nom de la ville : ");
        String nom = sc.nextLine();
        System.out.println("Latitude de la ville : ");
        double latitude = lireDouble();
        System.out.println("Longitude de la ville : ");
        double longitude = lireDouble();
        Ville v = villeController.addVille(new Ville(0, nom, latitude, longitude));
        if (v != null) {
            affMsg("création de : " + v);
        } else {
            affMsg("erreur de création");
        }
    }

    public void retirer() {
        System.out.println("Indiquez le numéro de la ligne");
        int n = choixElt(lv);
        Ville v = lv.get(n - 1);
        boolean ok = villeController.removeVille(v);
        if (ok) {
            affMsg("ville effacée");
        } else {
            affMsg("ville non effacée");
        }
    }

    public void rechercher() {
        System.out.println("idVille : ");
        int idVille = lireInt();
        Ville v = villeController.search(idVille);
        if(v == null){
            System.out.println("ville non trouvé");
        } else{
            affMsg(v.toString());
        }
    }

    public void modifier() {
        System.out.println("Indiquez le numéro de la ligne");
        int n = choixElt(lv);
        Ville v = lv.get(n - 1);
        String nom = modifyIfNotBlank("Nom de la ville : ", v.getNom());
        double latitude = Double.parseDouble(modifyIfNotBlank("Latitude de la ville : ", "" + v.getLatitude()));
        double longitude = Double.parseDouble(modifyIfNotBlank("Longitude de la ville ; ", "" + v.getLongitude()));
        Ville maj = villeController.update(new Ville(v.getIdVille(), nom, latitude, longitude));
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
