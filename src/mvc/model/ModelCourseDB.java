package mvc.model;

import automobile.metier.Course;
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
                    System.out.println("record introuvable");
                    return null;
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
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
            System.out.println("erreur sql : " + e);
            return false;
        }
    }

    @Override
    public Course updateCourse(Course course) {
        String query = "update APICOURSE set priceMoney=? where idCourse=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setBigDecimal(1, course.getPriceMoney());
            pstm.setInt(2, course.getIdCourse());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) readCourse(course.getIdCourse());
            else return null;
        } catch (SQLException e) {
            System.out.println("Erreur sql : " + e);
            return null;
        }
        return course;
    }

    @Override
    public Course readCourse(int idCourse) {
        String query = "select * from APICOURSE where idCourse=?";
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
            System.out.println("Erreur sql : " + e);
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
            System.out.println("Erreur sql : " + e);
            return null;
        }
    }

    @Override
    public List getNotification() {
        return getCourse();
    }
}
