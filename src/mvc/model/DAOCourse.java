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

    public abstract boolean addPilote(Course c, Pilote pi);

    public abstract boolean supPilote(Course c, Pilote pi);

    public abstract Classement resultat(Course c, Pilote pi, int place, BigDecimal gain);

    public abstract boolean modif(Course c, Pilote pi, int place, BigDecimal gain);

    public abstract List<Pilote> listePilotesDuPays(Course course);

    public abstract boolean classementComplet(Course course);
}
