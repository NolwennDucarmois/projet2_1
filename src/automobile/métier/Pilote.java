package automobile.metier;

import java.time.LocalDate;
public class Pilote {
    protected int idPilote;
    protected String matricule;
    protected String nom;
    protected String prenom;
    protected LocalDate dateNaiss;
    protected automobile.metier.Pays pays;

    public Pilote(){

    }

    public Pilote(int idPilote, String matricule, String nom, String prenom, LocalDate dateNaiss, automobile.metier.Pays pays) {
        this.idPilote = idPilote;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.pays = pays;
    }

    public int getIdPilote() {
        return idPilote;
    }

    public void setIdPilote(int idPilote) {
        this.idPilote = idPilote;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(LocalDate dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public automobile.metier.Pays getPays() {
        return pays;
    }

    public void setPays(automobile.metier.Pays pays) {
        this.pays = pays;
    }
}
