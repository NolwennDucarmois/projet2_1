package mvc.view;

import automobile.metier.Course;
import automobile.metier.ListePilotesPlaceGain;
import automobile.metier.Pays;
import automobile.metier.Pilote;
import mvc.controller.CourseController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class CourseViewConsole extends CourseAbstractView {
    private Scanner sc = new Scanner(System.in);

    @Override
    public void affMsg(String msg) {
        System.out.println("Information : " + msg);
    }

    @Override
    public void affList(List infos) {
        affListe(infos);
    }

    @Override
    public void menu() {
        update(courseController.getAll());
        do {
            int ch = choixListe(Arrays.asList("Ajout", "Retrait", "Recherche", "Modification", "Menu principal"));
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
        System.out.println("Nom de la course : ");
        String nom = sc.nextLine();
        System.out.println("PriceMoney : ");
        BigDecimal priceMoney = new BigDecimal(sc.nextLine());
        System.out.println("Date de la course : ");
        System.out.println("Jour : ");
        int jour = Integer.parseInt(sc.nextLine());
        System.out.println("Mois : ");
        int mois = Integer.parseInt(sc.nextLine());
        System.out.println("Année : ");
        int annee = Integer.parseInt(sc.nextLine());
        LocalDate dateCourse = LocalDate.of(annee, mois, jour);
        System.out.println("Km de la course : ");
        int km = Integer.parseInt(sc.nextLine());
        Course c = courseController.addCourse(new Course(0, nom, priceMoney, dateCourse, km));
        if (c != null) {
            affMsg("création de : " + c);
        } else {
            affMsg("erreur de création");
        }
    }

    public void retirer() {
        System.out.println("Indiquez le numéro de la ligne");
        int n = choixElt(lc);
        Course c = lc.get(n - 1);
        boolean ok = courseController.removeCourse(c);
        if (ok) {
            affMsg("course effacée");
        } else {
            affMsg("course non effacée");
        }
    }

    public void rechercher() {
        System.out.println("idCourse : ");
        int idCourse = lireInt();
        Course c = courseController.search(idCourse);
        if (c == null) {
            System.out.println("Course pas trouvé");
        } else {
            affMsg(c.toString());
            special(c);
        }
    }

    public void modifier() {
        System.out.println("Indiquez le numéro de la ligne");
        int n = choixElt(lc);
        Course c = lc.get(n - 1);
        String nom = modifyIfNotBlank("Nom de la course : ", c.getNom());
        BigDecimal priceMoney = new BigDecimal(modifyIfNotBlank("PriceMoney de la couse : ", "" + c.getPriceMoney()));
        String date = modifyIfNotBlank("Date de la course :  ", c.getDateCourse() + "");
        LocalDate dateCourse = !date.equals("null") ? LocalDate.parse(date) : null;
        int km = Integer.parseInt(modifyIfNotBlank("Km : ", "" + c.getKm()));
        Course maj = courseController.update(new Course(c.getIdCourse(), nom, priceMoney, dateCourse, km));
        if (maj != null) {
            affMsg("mise à jour effectuée : " + maj);
        } else {
            affMsg("mise à jour infructueuse");
        }
    }

    @Override
    public Course selectionner() {
        update(courseController.getAll());
        int n = choixListe(lc);
        Course c = lc.get(n - 1);
        return c;
    }

    private void special(Course c) {
        do {
            int choix = choixListe(Arrays.asList("Liste des pilotes avec place et gain", "Gain total de la course",
                    "Liste des pays des pilotes", "Vainqueur de la course", "Ajout d'un pilote à la course", "Retrait d'un pilote à la course",
                    "Enregistrement d'un résultat", "Modification d'un pilote", "Liste des coureurs du pays de la course", "Classement complet",
                    "Menu principal de course")
            );
            switch (choix) {
                case 1 -> listePilotesPlaceGain(c);
                case 2 -> gainTotal(c);
                case 3 -> listePaysPilotes(c);
                case 4 -> vainqueur(c);
                case 5 -> courseController.addPilote(c);
                case 6 -> courseController.supPilote();
                case 7 -> courseController.resultat();
                case 8 -> courseController.modif();
                case 9 -> listePilotesDuPays(c);
                case 10 -> courseController.classementComplet();
                case 11 -> {
                    return;
                }
            }
        } while (true);
    }

    public void listePilotesPlaceGain(Course c) {
        List<ListePilotesPlaceGain> liste = courseController.listePilotesPlaceGain(c);
        if (liste.isEmpty()) {
            System.out.println("La liste est null\n");
        } else {
            System.out.println("Liste pour la course " + c.getNom() + " : ");
            System.out.println(liste + "\n");
        }
    }

    public void gainTotal(Course c) {
        BigDecimal t = new BigDecimal(0);
        t = t.add(courseController.gainTotal(c));
        if (t.equals(BigDecimal.ZERO)) {
            System.out.println("Aucun gain total pour la course : " + c.getNom() + "\n");
        } else {
            System.out.println("La course du " + c.getNom() + " a pour gain total : " + t + "\n");
        }
    }

    public void listePaysPilotes(Course c) {
        List<Pays> liste = courseController.listePaysPilotes(c);
        if (liste.isEmpty()) {
            System.out.println("La liste est null\n");
        } else {
            System.out.println("Liste des pays pour la course " + c.getNom() + " : ");
            System.out.println(liste + "\n");
        }
    }

    public void vainqueur(Course c) {
        Pilote p;
        p = courseController.vainqueur(c);
        if (p == null) {
            System.out.println("Aucun vainqueur pour la course : " + c.getNom() + "\n");
        } else {
            System.out.println("La course du " + c.getNom() + " a pour vainqueur : " + p + "\n");
        }
    }

    public void listePilotesDuPays(Course c) {
        List<Pilote> liste = courseController.listePilotesDuPays(c);
        if (liste.isEmpty()) {
            System.out.println("La liste est null\n");
        } else {
            System.out.println("Liste des pilotes pour le pays " + c.getVille().getPays().getNom() + " de la course " + c.getNom() + " : ");
            System.out.println(liste + "\n");
        }
    }
}
