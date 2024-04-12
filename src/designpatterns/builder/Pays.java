package designpatterns.builder;

import java.util.*;

/**
 * classe metier de gestion d'un pays
 * @author Nolwenn Ducarmois
 * @version 1.0
 */
public class Pays {
    /**
     * compteur d'incrementation pour l'identifiant idPays
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
    protected Set<Pilote> listPilote = new HashSet<>();

    /**
     * constructeur par defaut
     */
    public Pays() {

    }

    public Pays(PaysBuilder pb) {
        this.idPays = pb.idPays;
        this.sigle = pb.sigle;
        this.nom = pb.nom;
        this.langue = pb.langue;
    }

    /**
     * getter idPays
     * @return identifiant du pays
     */
    public int getIdPays() {
        return idPays;
    }


    /**
     * getter sigle
     * @return sigle du pays
     */
    public String getSigle() {
        return sigle;
    }


    /**
     * getter nom
     * @return nom du pays
     */
    public String getNom() {
        return nom;
    }


    /**
     * getter langue
     * @return langue parlee dans le pays
     */
    public String getLangue() {
        return langue;
    }

    /**
     * getter pilote
     * @return la liste des pilotes dans ce pays
     */
    public Set<Pilote> getListPilote() {
        return listPilote;
    }

    /**
     * methode d'affichage de toutes les infos de l'objet de la classe Pays
     * @return toutes les infos sur l'objet
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o==null) return false;
        if(getClass() != o.getClass()) return false;
        final Pays other = (Pays) o;
        if(this.idPays!= other.idPays){
            return false;
        } else{
            return true;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.idPays;
        return hash;
    }

    public static class PaysBuilder{
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
        public PaysBuilder setIdPays(int idPays){
            this.idPays=id_act++;
            return this;
        }
        public PaysBuilder setSigle(String sigle){
            this.sigle=sigle;
            return this;
        }
        public PaysBuilder setNom(String nom){
            this.nom=nom;
            return this;
        }
        public PaysBuilder setLangue(String langue){
            this.langue=langue;
            return this;
        }
        public Pays build() throws Exception{
            if(idPays<=0||sigle==null||nom==null) throw new Exception("informations de construction incomplètes");
            return new Pays(this);
        }
    }
}
