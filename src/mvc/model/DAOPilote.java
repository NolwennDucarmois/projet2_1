package mvc.model;

import automobile.metier.Pilote;
import mvc.observer.Subject;

import java.util.List;

public abstract class DAOPilote extends Subject {
    public abstract Pilote addPilote(Pilote course);

    public abstract boolean removePilote(Pilote course);

    public abstract Pilote updatePilote(Pilote course);

    public abstract Pilote readPilote(int idPilote);

    public abstract List<Pilote> getPilote();
}