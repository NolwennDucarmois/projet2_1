package mvc.model;

import automobile.metier.*;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModelCourseDB extends DAOCourse {

    protected Connection dbConnect;

    public ModelCourseDB() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("Erreur de connexion");
            System.exit(1);
        }
    }

    @Override
    public Course addCourse(Course course) {
        String query1 = "insert into APICOURSE(nom, priceMoney, dateCourse, km) values(?,?,?,?)";
        String query2 = "select idCourse from APICOURSE where nom=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, course.getNom());
            pstm1.setBigDecimal(2, course.getPriceMoney());
            pstm1.setDate(3, Date.valueOf(course.getDateCourse()));
            pstm1.setInt(4, course.getKm());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setString(1, course.getNom());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int idCourse = rs.getInt(1);
                    course.setIdCourse(idCourse);
                    notifyObservers();
                    return course;
                } else {
                    System.err.println("record introuvable");
                    return null;
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql : " + e);
            return null;
        }
    }

    @Override
    public boolean removeCourse(Course course) {
        String query = "delete from APICOURSE where idCourse = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, course.getIdCourse());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql : " + e);
            return false;
        }
    }

    @Override
    public Course updateCourse(Course course) {
        String query = "update APICOURSE set nom=?, priceMoney=?, dateCourse=?, km=? where idCourse=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, course.getNom());
            pstm.setBigDecimal(2, course.getPriceMoney());
            pstm.setDate(3, Date.valueOf(course.getDateCourse()));
            pstm.setInt(4, course.getKm());
            pstm.setInt(5, course.getIdCourse());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) {
                return readCourse(course.getIdCourse());
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Erreur sql : " + e);
            return null;
        }
    }

    @Override
    public Course readCourse(int idCourse) {
        String query = "select * from APICOURSE where idCourse = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idCourse);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String nom = rs.getString(2);
                BigDecimal priceMoney = rs.getBigDecimal(3);
                LocalDate dateCourse = rs.getDate(4).toLocalDate();
                int km = rs.getInt(5);
                Course c = new Course(idCourse, nom, priceMoney, dateCourse, km);
                return c;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Erreur sql : " + e);
            return null;
        }
    }

    @Override
    public List<Course> getCourse() {
        List<Course> lc = new ArrayList<>();
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
                lc.add(c);
            }
            return lc;
        } catch (SQLException e) {
            System.err.println("Erreur sql : " + e);
            return null;
        }
    }

    @Override
    public List<ListePilotesPlaceGain> listePilotesPlaceGain(Course course) {
        return null;
    }

    @Override
    public void gainTotal(Course course) {
        BigDecimal total = new BigDecimal(0);
        String query = "select gain from apiclassement where idcourse = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, course.getIdCourse());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                total = total.add(rs.getBigDecimal("gain"));
            }
        } catch (SQLException e) {
            System.err.println("erreur sql : " + e);
        }
        if (total.equals(BigDecimal.ZERO)) {
            System.out.println("La course " + course.getNom() + " n'a aucun gain" + "\n");
        } else {
            System.out.println("La course " + course.getNom() + " a un gain total de : " + total + "\n");
        }
    }

    @Override
    public List<Pays> listePaysPilotes() {
        return null;
    }

    @Override
    public void vainqueur(Course course) {
        Pilote p = null;
        String query = "select * from apiclassement cl join apipilote pi on cl.idpilote = pi.idpilote where idcourse = ? and place = 1";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, course.getIdCourse());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                // aide par SatckOverflow pour savoir que je pouvais mettre les noms des colonnes pour qu'ils reconnaissent mieux quand il y a plusieurs tables
                p = new Pilote(rs.getInt("idPilote"), rs.getString("matricule"), rs.getString("nom"), rs.getString("prenom"));
            }
        } catch (SQLException e) {
            System.err.println("erreur sql : " + e);
        }
        if (p == null) {
            System.out.println("Aucun vainqueur pour la course : " + course.getNom() + "\n");
        } else {
            System.out.println(p + "\n");
        }
    }

    @Override
    public boolean addPilote() {
        return false;
    }

    @Override
    public boolean supPilote() {
        return false;
    }

    @Override
    public Classement resultat() {
        return null;
    }

    @Override
    public boolean modif() {
        return false;
    }

    @Override
    public List<Pilote> listePilotesDuPays() {
        return null;
    }

    @Override
    public boolean classementComplet() {
        return false;
    }

    @Override
    public List getNotification() {
        return getCourse();
    }
}
