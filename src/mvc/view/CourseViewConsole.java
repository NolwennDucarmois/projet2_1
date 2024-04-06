package mvc.view;

import automobile.metier.Course;

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
        System.out.println("Nom : ");
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
        int idCourse = Integer.parseInt(sc.nextLine());
        courseController.search(idCourse);
    }

    public void modifier() {
        int n = choixElt(lc);
        Course c = lc.get(n - 1);
        String nom = modifyIfNotBlank("Nom de la course : ", c.getNom());
        BigDecimal priceMoney = new BigDecimal(modifyIfNotBlank("PriceMoney de la couse : ", "" + c.getPriceMoney()));
        LocalDate dateCourse = LocalDate.parse(modifyIfNotBlank("Date de la course : ", "" + c.getDateCourse()));
        int km = Integer.parseInt(modifyIfNotBlank("Km : ", "" + c.getKm()));
        Course cmaj = courseController.update(new Course(c.getIdCourse(), nom, priceMoney, dateCourse, km));
        if (cmaj != null) {
            affMsg("mise à jour effectuée : " + cmaj);
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

}
