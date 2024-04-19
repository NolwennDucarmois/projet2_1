package designpatterns.composite;


import java.math.BigDecimal;
import java.time.LocalDate;

public class Automobile {
    public static void main(String[] args) {
        Course c1 = new Course(1, "Gp de Monaco", new BigDecimal(20000), LocalDate.of(2024, 4, 5), 15000);
        Course c2 = new Course(2, "Gp de Spa", new BigDecimal(8500), LocalDate.of(2024, 5, 5), 7000);
        Course c3 = new Course(3, "GP du Japon", new BigDecimal(15000), LocalDate.of(2024,6,8), 10000);

        Championnat champ1 = new Championnat(4, "Championnat1");
        Championnat champ2 = new Championnat(5, "Championnat2");
        Championnat champ3 = new Championnat(6, "Championnat3");

        champ1.getElts().add(c1);
        champ1.getElts().add(champ2);
        champ1.getElts().add(champ3);
        champ2.getElts().add(c2);
        champ3.getElts().add(c3);

        System.out.println(champ1);
        System.out.println(champ2);
        System.out.println(champ3);



    }
}
