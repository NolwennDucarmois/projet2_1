package automobile.metier;

import java.math.BigDecimal;

/**
 * classe metier de gestion des pilotes + place + gain
 * @author Nolwenn Ducarmois
 * @version 1.0
 */
public class ListePilotesPlaceGain {
    /**
     * pilote
     */
    protected Pilote pilote;
    /**
     * place du pilote
     */
    protected int place;
    /**
     * gain du pilote
     */
    protected BigDecimal gain;

    /**
     * constructeur par defaut
     */
    public ListePilotesPlaceGain() {

    }

    /**
     * constructeur parametre
     * @param pilote pilote
     * @param place  place du pilote
     * @param gain   gain du pilote
     */
    public ListePilotesPlaceGain(Pilote pilote, int place, BigDecimal gain) {
        this.pilote = pilote;
        this.place = place;
        this.gain = gain;
    }

    /**
     * getter pilote
     * @return pilote
     */
    public Pilote getPilote() {
        return pilote;
    }

    /**
     * setter pilote
     * @param pilote nouveau pilote
     */
    public void setPilote(Pilote pilote) {
        this.pilote = pilote;
    }

    /**
     * getter place
     * @return place du pilote
     */
    public int getPlace() {
        return place;
    }

    /**
     * setter place
     * @param place nouvelle place du pilote
     */
    public void setPlace(int place) {
        this.place = place;
    }

    /**
     * getter gain
     * @return gain du pilote
     */
    public BigDecimal getGain() {
        return gain;
    }

    /**
     * setter gain
     * @param gain nouveau gain du pilote
     */
    public void setGain(BigDecimal gain) {
        this.gain = gain;
    }

    /**
     * methode d'affichage de toutes les infos de l'objet de la classe ListePilotesPlaceGain
     * @return toutes les infos sur l'objet
     */
    @Override
    public String toString() {
        return place + ".\t " + pilote + " " + gain + "€";
    }
}
