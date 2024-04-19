package designpatterns.composite;

import java.math.BigDecimal;

public abstract class Element {
    private int id;

    public Element() {

    }

    public Element(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public abstract BigDecimal total();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        final Element other = (Element) o;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        return hash;
    }
}