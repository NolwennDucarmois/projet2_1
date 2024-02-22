package automobile.metier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * classe métier de gestion d'une course
 * @author Nolwenn Ducarmois
 * @version 1.0
 */
public class Course {
    /**
     * compteur d'incrementation pour l'identifiant idCourse
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
     * date ou la course se deroule
     */
    protected LocalDate dateCourse;
    /**
     * km total de la course
     */
    protected int km;
    /**
     * ville où se deroule la course
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
     * constructeur par defaut
     */
    public Course() {

    }

    /**
     * constructeur parametre
     * @param nom        nom de la course
     * @param priceMoney priceMoney de la course
     * @param dateCourse date de quand se déroule la course
     * @param km         km total de la course
     * @param ville      ville ou se déroule la course
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
     * @return ville ou se déroule la course
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
     * methode d'affichage de toutes les infos de l'objet de la classe Course
     * @return toutes les infos sur l'objet
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

    /**
     * methode qui affiche tous les coureurs qui participent à une course avec leur position et leur gain
     */
    public void listePilotesPlaceGain() {
        for (Classement c : listClassement) {
            listePilotesPlaceGains.add(new ListePilotesPlaceGain(c.pilote, c.place, c.gain));
        }
    }

    /**
     * methode qui calcule la somme des gains au total de la course
     * @return total retourne le total des gains
     */
    public BigDecimal gainTotal() {
        BigDecimal total = new BigDecimal(0);
        for (Classement c : listClassement) {
            total.add(c.gain);
        }
        return total;
    }

    /**
     * methode qui cherche tous les pays des pilotes qui participent à la course et les stocke 1 seule fois
     * @return liste la liste des pays
     */
    public List<Pays> listePaysPilotes() {
        List<Pays> liste = new ArrayList<>();
        for (Classement c : listClassement) {
            Pays pays = c.pilote.pays;
            if (!liste.contains(pays)) {
                liste.add(pays);
            }
        }
        return liste;
    }

    /**
     * methode qui chercher le vainqueur de la course
     * @return p le pilote vainqueur
     */
    public Pilote vainqueur() {
        Pilote p = null;
        for (Classement c : listClassement) {
            if (c.place == 1) {
                p = c.pilote;
                break;
            }
        }
        return p;
    }

    /**
     * methode qui permet d'ajouter un pilote à la liste des participants
     * @param pilote le pilote à ajouter à la course
     */
    public void addPilote(Pilote pilote) {
        Classement ajout = new Classement();
        ajout.setPilote(pilote);
    }

    /**
     * methode qui supprime un pilote de la liste des participants à une course
     * @param pilote le pilote à supprimer de la course
     */
    public void supPilote(Pilote pilote) {
        for (Classement c : listClassement) {
            if (c.pilote == pilote) {
                listClassement.remove(c.pilote);
            }
        }
    }

    /**
     * methode qui affiche le pilote dans le classement
     * @param pilote pilote à afficher
     * @param place place du pilote
     * @param gain gain du pilote
     */
    public void resultat(Pilote pilote, int place, BigDecimal gain) {
        System.out.println(place+" "+pilote.nom+" "+pilote.prenom+" "+gain);

    }

    /**
     * methode qui modifie la place et le gain d'un pilote si il y a contestation lors du classement de la course
     * @param pilote le pilote pour lequel on modifie
     * @param place  la nouvelle place du pilote
     * @param gain   le nouveau gain du pilote après contestation
     */
    public void modif(Pilote pilote, int place, BigDecimal gain) {
        for (Classement c : listClassement) {
            if (c.pilote.equals(pilote)) {
                c.setPlace(place);
                c.setGain(gain);
                break;
            }
        }
    }

    /**
     * methode qui cherche les pilotes ayant comme nationalite la même que celle ou se déroule la course
     * @return liste la liste des pilotes
     */
    public List<Pilote> listePilotesDuPays() {
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

    /**
     * methode qui retourne un boolean en fonction de si tous les pilotes inscrit à la course sont dans le classement
     * @return true | false
     */
    public boolean classementComplet() {
        for (Classement c : listClassement) {
            if (c.pilote == null) {
                return false;
            }
        }
        return true;
    }
}