package automobile.metier;

import java.time.LocalDate;
import java.util.Objects;

/**
 * classe metier de gestion d'un pilote
 * @author Nolwenn Ducarmois
 * @version 1.0
 */
public class Pilote {
    /**
     * compteur d'incrementation pour l'identifiant idPilote
     */
    protected static int id_act = 1;
    /**
     * identifiant unique du pilote
     */
    protected int idPilote;
    /**
     * matricule du pilote
     */
    protected String matricule;
    /**
     * nom du pilote
     */
    protected String nom;
    /**
     * prenom du pilote
     */
    protected String prenom;
    /**
     * date de naissance du pilote
     */
    protected LocalDate dateNaiss;
    /**
     * nationalite du pilote
     */
    protected Pays pays;

    /**
     * constructeur par defaut
     */
    public Pilote() {

    }

    /**
     * constructeur parametre
     * @param matricule matricule du pilote
     * @param nom       nom du pilote
     * @param prenom    prenom du pilote
     * @param dateNaiss date de naissance du pilote
     * @param pays      nationalite du pilote
     */
    public Pilote(String matricule, String nom, String prenom, LocalDate dateNaiss, Pays pays) {
        this.idPilote = id_act++;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.pays = pays;
    }

    /**
     * getter idPilote
     * @return identifiant du pilote
     */
    public int getIdPilote() {
        return idPilote;
    }

    /**
     * setter idPilote
     * @param idPilote nouveau identifiant du pilote
     */
    public void setIdPilote(int idPilote) {
        this.idPilote = idPilote;
    }

    /**
     * getter matricule
     * @return matricule du pilote
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * setter matricule
     * @param matricule nouveau matricule du pilote
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * getter nom
     * @return nom du pilote
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter nom
     * @param nom nouveau nom du pilote
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter prenom
     * @return prenom du pilote
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * setter prenom
     * @param prenom nouveau prenom du pilote
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * getter dateNaiss
     * @return date de naissance du pilote
     */
    public LocalDate getDateNaiss() {
        return dateNaiss;
    }

    /**
     * setter dateNaiss
     * @param dateNaiss nouvelle date de naissance du pilote
     */
    public void setDateNaiss(LocalDate dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    /**
     * getter pays
     * @return nationalite du pilote
     */
    public Pays getPays() {
        return pays;
    }

    /**
     * setter pays
     * @param pays nouvelle nationalite du pilote
     */
    public void setPays(Pays pays) {
        this.pays = pays;
    }

    /**
     * methode d'affichage de toutes les infos de l'objet de la classe Pilote
     * @return toutes les infos sur l'objet
     */
    @Override
    public String toString() {
        return "Pilote{" +
                "idPilote=" + idPilote +
                ", matricule='" + matricule + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaiss=" + dateNaiss +
                ", pays=" + pays +
                '}';
    }

    /**
     * méthode pour comparer que 2 objets sont égales par leur identifiant unique
     * @param o l'objet Pilote qu'on compare
     * @return true | false selon que c'est égale ou non
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pilote pilote = (Pilote) o;
        return idPilote == pilote.idPilote;
    }

    /**
     * méthode qui retourne une valeur sur idPilote
     * @return la valeur
     */
    @Override
    public int hashCode() {
        return Objects.hash(idPilote);
    }
}
