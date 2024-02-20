package automobile.métier;

import java.math.BigDecimal;

public class ListePilotesPlaceGain {
    protected Pilote pilote;
    protected int place;
    protected BigDecimal gain;

    public ListePilotesPlaceGain(){

    }
    public ListePilotesPlaceGain(Pilote pilote, int place, BigDecimal gain){
        this.pilote=pilote;
        this.place=place;
        this.gain=gain;
    }

    public Pilote getPilote() {
        return pilote;
    }

    public void setPilote(Pilote pilote) {
        this.pilote = pilote;
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

    @Override
    public String toString() {
        return place + ".\t "+pilote+" "+gain+"€";
    }
}
