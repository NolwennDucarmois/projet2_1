package mvc.controller;

import automobile.metier.*;
import mvc.model.DAOCourse;
import mvc.view.CourseAbstractView;

import java.math.BigDecimal;
import java.util.List;

public class CourseController {
    private DAOCourse model;
    private CourseAbstractView view;

    public CourseController(DAOCourse model, CourseAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Course> getAll() {
        return model.getCourse();
    }

    public Course addCourse(Course course) {
        return model.addCourse(course);
    }

    public boolean removeCourse(Course course) {
        return model.removeCourse(course);
    }

    public Course update(Course course) {
        return model.updateCourse(course);
    }

    public Course search(int idCourse) {
        return model.readCourse(idCourse);
    }

    public List<ListePilotesPlaceGain> listePilotesPlaceGain(Course course) {
        return model.listePilotesPlaceGain(course);
    }

    public void gainTotal(Course course) {
        model.gainTotal(course);
    }

    public List<Pays> listePaysPilotes() {
        return model.listePaysPilotes();
    }

    public void vainqueur(Course course) {
        model.vainqueur(course);
    }

    public boolean addPilote() {
        return model.addPilote();
    }

    public boolean supPilote() {
        return model.supPilote();
    }

    public Classement resultat() {
        return model.resultat();
    }

    public boolean modif() {
        return model.modif();
    }

    public List<Pilote> listePilotesDuPays() {
        return model.listePilotesDuPays();
    }

    public boolean classementComplet() {
        return model.classementComplet();
    }
}
