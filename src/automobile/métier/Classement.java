package automobile.métier;

import java.math.BigDecimal;

/**
 * classe métier de gestion d'un classement
 * @author Nolwenn Ducarmois
 * @version 1.0
 */
public class Classement {
    /**
     * compteur d'incrémentation pour l'identifiant idClassement
     */
    protected static int id_act = 1;
    /**
     * identifiant unique du classement
     */
    protected int idClassement;
    /**
     * place d'un pilote dans classement
     */
    protected int place;
    /**
     * gain d'un pilote dans classement
     */
    protected BigDecimal gain;
    /**
     * pilote du classement
     */
    protected Pilote pilote;

    /**
     * constructeur par defaut de la ville Classement
     */
    public Classement() {

    }

    /**
     * constructeur parametre
     * @param place        place dans le classement
     * @param gain         gain obtenu du classement
     * @param pilote       pilote du classement
     */
    public Classement(int place, BigDecimal gain, Pilote pilote) {
        this.idClassement = id_act++;
        this.place = place;
        this.gain = gain;
        this.pilote = pilote;
    }

    /**
     * getter idClassement
     * @return identifiant du classement
     */
    public int getIdClassement() {
        return idClassement;
    }

    /**
     * setter idClassement
     * @param idClassement nouveau identifiant du classement
     */
    public void setIdClassement(int idClassement) {
        this.idClassement = idClassement;
    }

    /**
     * getter place
     * @return place d'un pilote du classement
     */
    public int getPlace() {
        return place;
    }

    /**
     * setter place
     * @param place nouvelle place d'un pilote du classement
     */
    public void setPlace(int place) {
        this.place = place;
    }

    /**
     * getter gain
     * @return gain d'un pilote du classement
     */
    public BigDecimal getGain() {
        return gain;
    }

    /**
     * setter gain
     * @param gain nouveau gain d'un pilote du classement
     */
    public void setGain(BigDecimal gain) {
        this.gain = gain;
    }

    /**
     * getter pilote
     * @return pilote du classement
     */
    public Pilote getPilote() {
        return pilote;
    }

    /**
     * setter pilote
     * @param pilote nouveau pilote du classement
     */
    public void setPilote(Pilote pilote) {
        this.pilote = pilote;
    }

    /**
     * méthode d'affichage automatique
     */
    @Override
    public String toString() {
        return "Classement{" +
                "idClassement=" + idClassement +
                ", place=" + place +
                ", gain=" + gain +
                ", pilote=" + pilote +
                '}';
    }
}
