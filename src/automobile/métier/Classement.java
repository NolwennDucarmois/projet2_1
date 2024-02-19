package automobile.métier;

import java.math.BigDecimal;

public class Classement {
    protected int idClassement;
    protected int place;
    protected BigDecimal gain;
    protected Pilote pilote;
    public Classement(){

    }
    public Classement(int idClassement, int place, BigDecimal gain, Pilote pilote){
        this.idClassement=idClassement;
        this.place=place;
        this.gain=gain;
        this.pilote=pilote;
    }

    public int getIdClassement() {
        return idClassement;
    }

    public void setIdClassement(int idClassement) {
        this.idClassement = idClassement;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public BigDecimal getGain() {
        return gain;
    }

    public void setGain(BigDecimal gain) {
        this.gain = gain;
    }

    public Pilote getPilote() {
        return pilote;
    }

    public void setPilote(Pilote pilote) {
        this.pilote = pilote;
    }
}
