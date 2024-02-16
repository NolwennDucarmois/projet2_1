package automobile.métier;


import java.util.ArrayList;
import java.util.List;

public class Pays {
    protected int idPays;
    protected String sigle;
    protected String nom;
    protected String langue;
    protected List<Pilote> listPilote = new ArrayList<>();

    public Pays(){

    }
    public Pays(int idPays, String sigle, String nom, String langue){
        this.idPays=idPays;
        this.sigle=sigle;
        this.nom=nom;
        this.langue=langue;
    }

    public int getIdPays() {
        return idPays;
    }

    public void setIdPays(int idPays) {
        this.idPays = idPays;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public List<Pilote> getListPilote() {
        return listPilote;
    }

    public void setListPilote(List<Pilote> listPilote) {
        this.listPilote = listPilote;
    }
}
