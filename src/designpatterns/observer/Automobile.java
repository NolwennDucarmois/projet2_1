package designpatterns.observer;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Automobile {
    public static void main(String[] args) {
        Pays pays1 = new Pays("PB", "Pays-Bas", "neerlandais");
        Pays pays2 = new Pays("AN", "Angleterre", "anglais");
        Course c1 = new Course(1, "Gp de Monaco", new BigDecimal(20000), LocalDate.of(2024, 4, 5), 15000);
        Course c2 = new Course(2, "Gp de Spa", new BigDecimal(8500), LocalDate.of(2024, 5, 5), 7000);
        Pilote p1 = new Pilote("VM", "Verstappen", "Max", LocalDate.of(1997, 9, 30), pays1);
        Pilote p2 = new Pilote("HL", "Hamilton", "Lewis", LocalDate.of(1985, 1, 7), pays2);
        c1.addObserver(p1);
        c1.addObserver(p2);
        c2.addObserver(p2);

        c1.setDateCourse(LocalDate.of(2024, 3, 1));
        c2.setDateCourse(LocalDate.of(2024, 5, 4));

    }
}
