package automobile.métier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * classe métier de gestion d'une course
 * @author Nolwenn Ducarmois
 * @version 1.0
 */
public class Course {
    /**
     * compteur d'incrémentation pour l'identifiant idCourse
     */
    protected static int id_act = 1;
    /**
     * identifiant unique de course
     */
    protected int idCourse;
    /**
     * nom de la course
     */
    protected String nom;
    /**
     * priceMoney de la course
     */
    protected BigDecimal priceMoney;
    /**
     * date où la course se deroule
     */
    protected LocalDate dateCourse;
    /**
     * km total de la course
     */
    protected int km;
    /**
     * ville où se déroule la course
     */
    protected Ville ville;
    /**
     * liste des classements pour la course
     */
    protected List<Classement> listClassement = new ArrayList<>();
    /**
     * liste des pilotes + place + gain de la course
     */
    protected List<ListePilotesPlaceGain> listePilotesPlaceGains = new ArrayList<>();

    /**
     * constructeur par défaut
     */
    public Course() {

    }

    /**
     * constructeur paramétré
     * @param nom        nom de la course
     * @param priceMoney priceMoney de la course
     * @param dateCourse date de quand se déroule la course
     * @param km         km total de la course
     * @param ville      ville où se déroule la course
     */
    public Course(String nom, BigDecimal priceMoney, LocalDate dateCourse, int km, Ville ville) {
        this.idCourse = id_act++;
        this.nom = nom;
        this.priceMoney = priceMoney;
        this.dateCourse = dateCourse;
        this.km = km;
        this.ville = ville;
    }

    /**
     * getter idCourse
     * @return identifiant de la course
     */
    public int getIdCourse() {
        return idCourse;
    }

    /**
     * setter idCourse
     * @param idCourse nouveau identifiant de la course
     */
    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    /**
     * getter nom
     * @return nom de la course
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter nom
     * @param nom nouveau nom de la course
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter priceMoney
     * @return priceMoney de la course
     */
    public BigDecimal getPriceMoney() {
        return priceMoney;
    }

    /**
     * setter priceMoney
     * @param priceMoney nouveau prime max de la course
     */
    public void setPriceMoney(BigDecimal priceMoney) {
        this.priceMoney = priceMoney;
    }

    /**
     * getter dateCourse
     * @return date de la course
     */
    public LocalDate getDateCourse() {
        return dateCourse;
    }

    /**
     * setter dateCourse
     * @param dateCourse nouvelle date pour la course
     */
    public void setDateCourse(LocalDate dateCourse) {
        this.dateCourse = dateCourse;
    }

    /**
     * getter km
     * @return km total de la course
     */
    public int getKm() {
        return km;
    }

    /**
     * setter km
     * @param km nouvelle distance totale en km de la course
     */
    public void setKm(int km) {
        this.km = km;
    }

    /**
     * getter ville
     * @return ville où se déroule la course
     */
    public Ville getVille() {
        return ville;
    }

    /**
     * setter ville
     * @param ville nouvelle ville pour la course
     */
    public void setVille(Ville ville) {
        this.ville = ville;
    }

    /**
     * getter classement
     * @return la liste des classement pour cette course
     */
    public List<Classement> getListClassement() {
        return listClassement;
    }

    /**
     * setter classement
     * @param listClassement nouvelle liste pour le classement de la course
     */
    public void setListClassement(List<Classement> listClassement) {
        this.listClassement = listClassement;
    }

    /**
     * getter listePilotesPlaceGain
     * @return la liste des pilotes + place + gain
     */
    public List<ListePilotesPlaceGain> getListePilotesPlaceGains() {
        return listePilotesPlaceGains;
    }

    /**
     * setter listePilotesPlaceGain
     * @param listePilotesPlaceGains nouvelle liste pour les pilotes dans le classement de la course
     */
    public void setListePilotesPlaceGains(List<ListePilotesPlaceGain> listePilotesPlaceGains) {
        this.listePilotesPlaceGains = listePilotesPlaceGains;
    }

    /**
     * méthode d'affichage
     */
    @Override
    public String toString() {
        return "Course{" +
                "idCourse=" + idCourse +
                ", nom='" + nom + '\'' +
                ", priceMoney=" + priceMoney +
                ", dateCourse=" + dateCourse +
                ", km=" + km +
                ", ville=" + ville +
                ", listClassement=" + listClassement +
                ", listePilotesPlaceGains=" + listePilotesPlaceGains +
                '}';
    }

    public void listePilotesPlaceGain() {
        // tous les coureurs qui participe à la course + place + gain
        for (Classement c : listClassement) {
            listePilotesPlaceGains.add(new ListePilotesPlaceGain(c.pilote, c.place, c.gain));
        }
        for (int i = 0; i < listePilotesPlaceGains.size(); i++) {
            System.out.println(listePilotesPlaceGains.get(i));
        }
    }

    public BigDecimal gainTotal() {
        // total de tous les gains pour la course
        BigDecimal total = new BigDecimal(0);
        for (Classement c : listClassement) {
            total.add(c.gain);
        }
        return total;
    }

    public List<Pays> listePaysPilotes() {
        // tous les pays des pilotes (1 seule fois)
        // trouver les pilotes qui participent à la course et prendre leur pays
        List<Pays> liste = new ArrayList<>();
        for (Classement c : listClassement) {
            Pays pays = c.pilote.pays;
            if (!liste.contains(pays)) {
                liste.add(pays);
            }
        }
        return liste;
    }

    public Pilote vainqueur() {
        // meilleur place
        Pilote p = null;
        for (Classement c : listClassement) {
            if (c.place == 1) {
                p = c.pilote;
                break;
            }
        }
        return p;
    }

    public void addPilote(Pilote pilote) {
        Classement ajout = new Classement();
        ajout.setPilote(pilote);
    }

    public void supPilote(Pilote pilote) {
        // chercher le pilote dans le classement
        for (Classement c : listClassement) {
            if (c.pilote == pilote) {
                listClassement.remove(c.pilote);
            }
        }
    }

    public void resultat(Pilote pilote, int place, BigDecimal gain) {
        //TODO classe resultat()
        // enregistrer un pilote à une course quand il est inscrit
        // màj du classement en fait
    }

    public void modif(Pilote pilote, int place, BigDecimal gain) {
        // changer la place si contestation
        for (Classement c : listClassement) {
            if (c.pilote.equals(pilote)) {
                c.setPlace(place);
                c.setGain(gain);
                break;
            }
        }
    }

    public List<Pilote> listePilotesDuPays() {
        // liste pilotes d'on le pays et le meme que la course
        Pays course = ville.pays;
        List<Pilote> liste = new ArrayList<>();
        for (Classement c : listClassement) {
            Pays pays = c.pilote.pays;
            if (course.equals(pays)) {
                liste.add(c.pilote);
            }
        }
        return liste;
    }

    public boolean classementComplet() {
        // est-ce que un pilote inscrit est bien dans le classement
        for (Classement c : listClassement) {
            if (c.pilote == null) {
                return false;
            }
        }
        return true;
    }
}
