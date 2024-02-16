package automobile.métier;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Course {
    protected int idCourse;
    protected String nom;
    protected BigDecimal priceMoney;
    protected LocalDate dateCourse;
    protected int km;
    protected Ville ville;
    protected List<Classement> listClassement = new ArrayList<>();

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
    public void listePilotesPlaceGain(){
        // tous les coureurs participer à la course + place + gain
    }
    public void gainTotal(){
        // total de tous les gains pour la course
    }
    public void listePaysPilotes(){
        //
    }
    public void vainqueur(){
        // meilleur place
    }
    public void addPilote(Pilote pilote){

    }
    public void supPilote(Pilote pilote){
        // chercher le pilote dans le classement

        listClassement.remove(pilote);
    }
    public void resultat(Pilote pilote, Classement place, Classement gain){
        // enregistrer un pilote à une course quand il est inscrit
        // màj du classement en fait
    }
    public void modif(Pilote pilote, Classement place, Classement gain){
        // changer la place si contestation
    }
    public void listePilotesDuPays(){

    }
    public boolean classementComplet(){
        // est-ce que pour tous les coureurs inscrit à la course il y a un classement
        return true;
    }
}


// Liste CoursetHeure
//List<?>
// classe CoursetHeure
// #cours
// #heure
