package automobile.métier;

public class ListePilotePays {
    protected Pilote pilote;
    protected Pays pays;
    public ListePilotePays(){

    }

    public ListePilotePays(Pilote pilote, Pays pays) {
        this.pilote = pilote;
        this.pays = pays;
    }

    public Pilote getPilote() {
        return pilote;
    }

    public void setPilote(Pilote pilote) {
        this.pilote = pilote;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }
}
