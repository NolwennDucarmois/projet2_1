package mvc.model;

import automobile.metier.*;
import mvc.observer.Subject;

import java.math.BigDecimal;
import java.util.List;

public abstract class DAOCourse extends Subject {
    public abstract Course addCourse(Course course);

    public abstract boolean removeCourse(Course course);

    public abstract Course updateCourse(Course course);

    public abstract Course readCourse(int idCourse);

    public abstract List<Course> getCourse();

    public abstract List<ListePilotesPlaceGain> listePilotesPlaceGain(Course course);

    public abstract BigDecimal gainTotal(Course course);

    public abstract List<Pays> listePaysPilotes(Course course);

    public abstract Pilote vainqueur(Course course);

    public abstract boolean addPilote(Course c);

    public abstract boolean supPilote();

    public abstract Classement resultat();

    public abstract boolean modif();

    public abstract List<Pilote> listePilotesDuPays(Course course);

    public abstract boolean classementComplet();
}
