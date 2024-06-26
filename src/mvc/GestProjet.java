package mvc;

import mvc.controller.CourseController;
import mvc.controller.PaysController;
import mvc.controller.PiloteController;
import mvc.controller.VilleController;
import mvc.model.*;
import mvc.view.*;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestProjet {
    private DAOCourse cm;
    private DAOCourse cmh;
    private DAOPilote pm;
    private DAOPays pam;
    private DAOPays pamh;
    private DAOVille vm;

    private CourseController cc;
    private PiloteController pc;
    private PaysController pac;
    private VilleController vc;

    private CourseAbstractView cv;
    private PiloteAbstractView pv;
    private PaysAbstractView pav;
    private VilleAbstractView vv;

    public void gestion() {
        cmh = new ModelCourseDBHyb();
        //cm = new ModelCourseDB();
        cv = new CourseViewConsole();
        //cc = new CourseController(cm, cv);
        cc = new CourseController(cmh, cv);
        cmh.addObserver(cv);
        //cm.addObserver(cv);

        pm = new ModelPiloteDB();
        pv = new PiloteViewConsole();
        pc = new PiloteController(pm, pv);
        pm.addObserver(pv);

        //pam = new ModelPaysDB();
        pamh = new ModelPaysDBHyb();
        pav = new PaysViewConsole();
        //pac = new PaysController(pam, pav);
        pac = new PaysController(pamh, pav);
        //pam.addObserver(pav);
        pamh.addObserver(pav);

        vm = new ModelVilleDB();
        vv = new VilleViewConsole();
        vc = new VilleController(vm, vv);
        vm.addObserver(vv);

        cv.setPiloteView(pv);

        List<String> loptions = Arrays.asList("Courses", "Pilotes", "Pays", "Villes", "FIN");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch) {
                case 1:
                    cv.menu();
                    break;
                case 2:
                    pv.menu();
                    break;
                case 3:
                    pav.menu();
                    break;
                case 4:
                    vv.menu();
                    break;
                case 5:
                    System.out.println("Fin du programme");
                    System.exit(0);
            }
        } while (true);
    }

    public static void main(String[] args) {
        GestProjet gp = new GestProjet();
        gp.gestion();
    }
}
