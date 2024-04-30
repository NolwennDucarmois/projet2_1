package mvc.model;

import automobile.metier.Pilote;
import mvc.observer.Subject;

import java.util.List;

public abstract class DAOPilote extends Subject {
    public abstract Pilote addPilote(Pilote pilote);

    public abstract boolean removePilote(Pilote pilote);

    public abstract Pilote updatePilote(Pilote pilote);

    public abstract Pilote readPilote(int idPilote);

    public abstract List<Pilote> getPilotes();
}
