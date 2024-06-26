package demodb;

import automobile.metier.Course;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class GestCourse {
    private Scanner sc = new Scanner(System.in);
    private Connection dbConnect;

    public void gestion() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("Connexion établie");
        do {
            System.out.println("1.\tAjout\n2.\tRecherche\n3.\tModification\n4.\tSuppression\n5.\tTous\n6.\tFin");
            System.out.println("Choix ?");
            int choix = sc.nextInt();
            sc.skip("\n");
            switch (choix) {
                case 1:
                    ajout();
                    break;
                case 2:
                    recherche();
                    break;
                case 3:
                    modification();
                    break;
                case 4:
                    suppression();
                    break;
                case 5:
                    tous();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide, recommencez");
            }
        } while (true);
    }

    public void ajout() {
        System.out.println("Nom : ");
        String nom = sc.nextLine();
        System.out.println("PriceMoney : ");
        BigDecimal priceMoney = sc.nextBigDecimal();
        System.out.println("Date de la course : ");
        System.out.println("Jour : ");
        int jour = sc.nextInt();
        System.out.println("Mois : ");
        int mois = sc.nextInt();
        System.out.println("Année : ");
        int annee = sc.nextInt();
        LocalDate dateCourse = LocalDate.of(annee, mois, jour);
        System.out.println("Km de la course : ");
        int km = sc.nextInt();
        String query1 = "insert into APICOURSE(nom, priceMoney, dateCourse, km) values(?,?,?,?)";
        String query2 = "select idCourse from APICOURSE where nom=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, nom);
            pstm1.setBigDecimal(2, priceMoney);
            pstm1.setDate(3, Date.valueOf(dateCourse));
            pstm1.setInt(4, km);
            int n = pstm1.executeUpdate();
            System.out.println(n + " ligne insérée");
            if (n == 1) {
                pstm2.setString(1, nom);
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int idCourse = rs.getInt(1);
                    System.out.println("idCourse = " + idCourse);
                } else {
                    System.out.println("record introuvable");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur sql : " + e);
        }
    }

    public void recherche() {
        System.out.println("Id de la course à rechercher : ");
        int id_rechercher = sc.nextInt();
        String query = "select * from APICOURSE where idCourse=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, id_rechercher);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String nom = rs.getString(2);
                BigDecimal priceMoney = rs.getBigDecimal(3);
                LocalDate dateCourse = rs.getDate(4).toLocalDate();
                int km = rs.getInt(5);
                Course c = new Course(id_rechercher, nom, priceMoney, dateCourse, km);
                System.out.println(c);
            } else {
                System.out.println("Record introuvable");
            }
        } catch (SQLException e) {
            System.out.println("Erreur sql : " + e);
        }
    }

    public void modification() {
        System.out.println("Id de la course à modifier : ");
        int id_rechercher = sc.nextInt();
        sc.skip("\n");
        System.out.println("Nouveau priceMoney : ");
        BigDecimal priceMoney = sc.nextBigDecimal();
        String query = "update APICOURSE set priceMoney=? where idCourse=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setBigDecimal(1, priceMoney);
            pstm.setInt(2, id_rechercher);
            int n = pstm.executeUpdate();
            if (n != 0) {
                System.out.println(n + " ligne modifiée");
            } else {
                System.out.println("Record introuvable");
            }
        } catch (SQLException e) {
            System.out.println("Erreur sql : " + e);
        }
    }

    public void suppression() {
        System.out.println("id de la course à supprimer : ");
        int id_rechercher = sc.nextInt();
        String query = "delete from APICOURSE where idCourse = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, id_rechercher);
            int n = pstm.executeUpdate();
            if (n != 0) {
                System.out.println(n + " ligne supprimée");
            } else {
                System.out.println("Record introuvable");
            }
        } catch (SQLException e) {
            System.out.println("erreur sql : " + e);
        }
    }

    public void tous() {
        String query = "select * from APICOURSE";
        try (Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int idcourse = rs.getInt(1);
                String nom = rs.getString(2);
                BigDecimal priceMoney = rs.getBigDecimal(3);
                LocalDate dateCourse = rs.getDate(4).toLocalDate();
                int km = rs.getInt(5);
                Course c = new Course(idcourse, nom, priceMoney, dateCourse, km);
                System.out.println(c);
            }
        } catch (SQLException e) {
            System.out.println("Erreur sql : " + e);
        }
    }

    public static void main(String[] args) {

        GestCourse g = new GestCourse();
        g.gestion();
    }

}
