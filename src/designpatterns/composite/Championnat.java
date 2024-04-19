package designpatterns.composite;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Championnat extends Element {
    private String nom;
    private Set<Element> elts = new HashSet<>();

    public Championnat() {

    }

    public Championnat(int id, String nom) {
        super(id);
        this.nom = nom;
    }


    @Override
    public BigDecimal total() {
        BigDecimal t = new BigDecimal(0);
        for (Element e : elts) {
            t = t.add(e.total());
        }
        return t;
    }

    public String getNom() {
        return nom;
    }

    public Set<Element> getElts() {
        return elts;
    }

    @Override
    public String toString() {
        StringBuilder affichage = new StringBuilder(getId() + " : " + nom + "\n");
        for (Element e : elts) {
            affichage.append(e + "\n");
        }
        return affichage + "\t--> valeur totale de " + nom + " = " + total() + "\n";
    }
}
