package automobile.metier;

public class Ville {
    protected int idVille;
    protected String nom;
    protected double latitude;
    protected double longitude;
    protected automobile.metier.Pays pays;
    public Ville(){

    }
    public Ville(int idVille, String nom, double latitude, double longitude, automobile.metier.Pays pays){
        this.idVille=idVille;
        this.nom=nom;
        this.latitude=latitude;
        this.longitude=longitude;
        this.pays=pays;
    }

    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public automobile.metier.Pays getPays() {
        return pays;
    }

    public void setPays(automobile.metier.Pays pays) {
        this.pays = pays;
    }
}
