package automobile.métier;

import java.util.ArrayList;
import java.util.List;

/**
 * classe métier de gestion d'un pays
 * @author Nolwenn Ducarmois
 * @version 1.0
 */
public class Pays {
    /**
     * compteur d'incrémentation pour l'identifiant idPays
     */
    protected static int id_act = 1;
    /**
     * identifiant unique d'un pays
     */
    protected int idPays;
    /**
     * sigle d'un pays
     */
    protected String sigle;
    /**
     * nom d'un pays
     */
    protected String nom;
    /**
     * langue d'un pays
     */
    protected String langue;
    /**
     * liste des pilotes dans ce pays
     */
    protected List<Pilote> listPilote = new ArrayList<>();

    /**
     * constructeur par defaut
     */
    public Pays() {

    }

    /**
     * constructeur parametre
     * @param sigle  sigle du pays
     * @param nom    nom du pays
     * @param langue langue parlée dans le pays
     */
    public Pays(String sigle, String nom, String langue) {
        this.idPays = id_act++;
        this.sigle = sigle;
        this.nom = nom;
        this.langue = langue;
    }

    /**
     * getter idPays
     * @return identifiant du pays
     */
    public int getIdPays() {
        return idPays;
    }

    /**
     * setter idPays
     * @param idPays nouveau identifiant du pays
     */
    public void setIdPays(int idPays) {
        this.idPays = idPays;
    }

    /**
     * getter sigle
     * @return sigle du pays
     */
    public String getSigle() {
        return sigle;
    }

    /**
     * setter sigle
     * @param sigle nouveau sigle du pays
     */
    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    /**
     * getter nom
     * @return nom du pays
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter nom
     * @param nom nouveau nom du pays
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter langue
     * @return langue parlée dans le pays
     */
    public String getLangue() {
        return langue;
    }

    /**
     * setter langue
     * @param langue nouvelle langue du pays
     */
    public void setLangue(String langue) {
        this.langue = langue;
    }

    /**
     * getter pilote
     * @return la liste des pilotes dans ce pays
     */
    public List<Pilote> getListPilote() {
        return listPilote;
    }

    /**
     * setter pilote
     * @param listPilote nouvelle liste de pilotes pour ce pays
     */
    public void setListPilote(List<Pilote> listPilote) {
        this.listPilote = listPilote;
    }

    /**
     * méthode d'affichage
     */
    @Override
    public String toString() {
        return "Pays{" +
                "idPays=" + idPays +
                ", sigle='" + sigle + '\'' +
                ", nom='" + nom + '\'' +
                ", langue='" + langue + '\'' +
                ", listPilote=" + listPilote +
                '}';
    }
}
