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

    public BigDecimal gainTotal(Course course) {
        return model.gainTotal(course);
    }

    public List<Pays> listePaysPilotes(Course course) {
        return model.listePaysPilotes(course);
    }

    public Pilote vainqueur(Course course) {
        return model.vainqueur(course);
    }

    public boolean addPilote(Course course, Pilote pi) {
        return model.addPilote(course, pi);
    }

    public boolean supPilote(Course c, Pilote pi) {
        return model.supPilote(c, pi);
    }

    public boolean resultat(Course c, Pilote pi, int place, BigDecimal gain) {
        return model.resultat(c, pi, place, gain);
    }

    public boolean modif(Course c, Pilote pi, int place, BigDecimal gain) {
        return model.modif(c, pi, place, gain);
    }

    public List<Pilote> listePilotesDuPays(Course course) {
        return model.listePilotesDuPays(course);
    }

    public boolean classementComplet(Course course) {
        return model.classementComplet(course);
    }
    public List<Pilote> getPilotesCourse(Course course){
        return model.getPilotesCourse(course);
    }
}
