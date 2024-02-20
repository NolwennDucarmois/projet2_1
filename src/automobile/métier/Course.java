package automobile.métier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Course {
    protected int idCourse;
    protected String nom;
    protected BigDecimal priceMoney;
    protected LocalDate dateCourse;
    protected int km;
    protected Ville ville;
    protected List<Classement> listClassement = new ArrayList<>();
    protected List<ListePilotesPlaceGain> listePilotesPlaceGains = new ArrayList<>();

    public Course(){

    }
    public Course(int idCourse, String nom, BigDecimal priceMoney, LocalDate dateCourse, int km, Ville ville){
        this.idCourse=idCourse;
        this.nom=nom;
        this.priceMoney=priceMoney;
        this.dateCourse=dateCourse;
        this.km=km;
        this.ville=ville;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigDecimal getPriceMoney() {
        return priceMoney;
    }

    public void setPriceMoney(BigDecimal priceMoney) {
        this.priceMoney = priceMoney;
    }

    public LocalDate getDateCourse() {
        return dateCourse;
    }

    public void setDateCourse(LocalDate dateCourse) {
        this.dateCourse = dateCourse;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public List<Classement> getListClassement() {
        return listClassement;
    }

    public void setListClassement(List<Classement> listClassement) {
        this.listClassement = listClassement;
    }

    public List<ListePilotesPlaceGain> getListePilotesPlaceGains() {
        return listePilotesPlaceGains;
    }

    public void setListePilotesPlaceGains(List<ListePilotesPlaceGain> listePilotesPlaceGains) {
        this.listePilotesPlaceGains = listePilotesPlaceGains;
    }

    public void listePilotesPlaceGain(){
        // tous les coureurs qui participe à la course + place + gain
        for (Classement c : listClassement){
            listePilotesPlaceGains.add(new ListePilotesPlaceGain(c.pilote, c.place, c.gain));
        }
        for(int i=0; i<listePilotesPlaceGains.size(); i++){
            System.out.println(listePilotesPlaceGains.get(i));
        }
    }
    public BigDecimal gainTotal(){
        // total de tous les gains pour la course
        BigDecimal total = new BigDecimal(0);
        for (Classement c : listClassement){
            total.add(c.gain);
        }
        return total;
    }
    public List<Pays> listePaysPilotes(){
        // tous les pays des pilotes (1 seule fois)
        // trouver les pilotes qui participent à la course et prendre leur pays
        List<Pays> liste= new ArrayList<>();
        for(Classement c : listClassement){
            Pays pays = c.pilote.pays;
            if (!liste.contains(pays)) {
                liste.add(pays);
            }
        }
        return liste;
    }
    public Pilote vainqueur(){
        // meilleur place
        Pilote p = null;
        for (Classement c : listClassement){
            if(c.place == 1){
                p = c.pilote;
                break;
            }
        }
        return p;
    }
    public void addPilote(Pilote pilote){
        Classement ajout = new Classement();
        ajout.setPilote(pilote);
    }
    public void supPilote(Pilote pilote){
        // chercher le pilote dans le classement
        for (Classement c : listClassement){
            if(c.pilote==pilote){
                listClassement.remove(c.pilote);
            }
        }
    }
    public void resultat(Pilote pilote, int place, BigDecimal gain){
        //TODO classe resultat()
        // enregistrer un pilote à une course quand il est inscrit
        // màj du classement en fait
    }
    public void modif(Pilote pilote, int place, BigDecimal gain){
        // changer la place si contestation
        for (Classement c : listClassement){
            if(c.pilote==pilote){
                c.setPlace(place);
                c.setGain(gain);
                break;
            }
        }

    }
    public List<Pilote> listePilotesDuPays(){
        // liste pilotes d'on le pays et le meme que la course
        Pays course = ville.pays;
        List<Pilote> liste= new ArrayList<>();
        for(Classement c : listClassement) {
            Pays pays = c.pilote.pays;
            if (course.equals(pays)){
                liste.add(c.pilote);
            }
        }
        return liste;
    }
    public boolean classementComplet(){
        for (Classement c : listClassement){
            if(c.pilote==null){
                return false;
            }
        }
        return true;
    }
}
