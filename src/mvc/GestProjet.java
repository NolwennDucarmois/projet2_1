package mvc;

import mvc.controller.CourseController;
import mvc.model.DAOCourse;
import mvc.model.ModelCourseDB;
import mvc.view.CourseAbstractView;
import mvc.view.CourseViewConsole;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestProjet {
    private DAOCourse cm;
    private CourseController cc;
    private CourseAbstractView cv;

    public void gestion() {
        cm = new ModelCourseDB();
        cv = new CourseViewConsole();
        cc = new CourseController(cm, cv);
        cm.addObserver(cv);

        List<String> loptions = Arrays.asList("course", "fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch) {
                case 1:
                    cv.menu();
                    break;
                case 2:
                    System.exit(0);
            }
        } while (true);
    }

    public static void main(String[] args) {
        GestProjet gp = new GestProjet();
        gp.gestion();
    }
}
