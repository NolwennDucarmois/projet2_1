package automobile.métier;

/**
 * classe métier de gestion d'une ville
 * @author Nolwenn Ducarmois
 * @version 1.0
 */
public class Ville {
    /**
     * compteur d'incrementation pour l'identifiant idVille
     */
    protected static int id_act = 1;
    /**
     * identifiant unique d'une ville
     */
    protected int idVille;
    /**
     * nom d'une ville
     */
    protected String nom;
    /**
     * latitude de la ville
     */
    protected double latitude;
    /**
     * longitude de la ville
     */
    protected double longitude;
    /**
     * pays contenant la ville
     */
    protected Pays pays;

    /**
     * constructeur par defaut de la classe Ville
     */
    public Ville() {

    }

    /**
     * constructeur parametre
     * @param nom       nom de la ville
     * @param latitude  latitude de la ville
     * @param longitude longitude de la ville
     * @param pays      pays contenant la ville
     */
    public Ville(String nom, double latitude, double longitude, Pays pays) {
        this.idVille = id_act++;
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pays = pays;
    }

    /**
     * getter idVille
     * @return identifiant de la ville
     */
    public int getIdVille() {
        return idVille;
    }

    /**
     * setter idVille
     * @param idVille nouveau identifiant de la ville
     */
    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }

    /**
     * getter nom
     * @return nom de la ville
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter nom
     * @param nom nouveau nom de la ville
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter latitude
     * @return latitude de la ville
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * setter latitude
     * @param latitude nouvelle latitude de la ville
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * getter longitude
     * @return longitude de la ville
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * setter longitude
     * @param longitude nouvelle longitude de la ville
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * getter pays
     * @return pays de la ville
     */
    public Pays getPays() {
        return pays;
    }

    /**
     * setter pays
     * @param pays nouveau pays de la ville
     */
    public void setPays(Pays pays) {
        this.pays = pays;
    }

    /**
     * methode d'affichage de toutes les infos de l'objet de la classe Ville
     * @return toutes les infos sur l'objet
     */
    @Override
    public String toString() {
        return "Ville{" +
                "idVille=" + idVille +
                ", nom='" + nom + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", pays=" + pays +
                '}';
    }
}
