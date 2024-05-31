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
        List<ListePilotesPlaceGain> liste = new ArrayList<>();
        String query = "select * from apiclassement cl join apipilote pi on cl.idPilote=pi.idPilote where cl.idCourse = ? order by cl.place";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, course.getIdCourse());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int idPilote = rs.getInt("idpilote");
                String matriculePilote = rs.getString("matricule");
                String nomPilote = rs.getString("nom");
                String prenomPilote = rs.getString("prenom");
                LocalDate dateNaissPilote = rs.getDate("dateNaiss").toLocalDate();
                Pilote pi = new Pilote(idPilote, matriculePilote, nomPilote, prenomPilote, dateNaissPilote);
                int place = rs.getInt("place");
                BigDecimal gain = rs.getBigDecimal("gain");
                ListePilotesPlaceGain li = new ListePilotesPlaceGain(pi, place, gain);
                liste.add(li);
            }
        } catch (SQLException e) {
            System.err.println("erreur sql : " + e);
        }
        return liste;
    }

    @Override
    public BigDecimal gainTotal(Course course) {
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
        return total;
    }

    @Override
    public List<Pays> listePaysPilotes(Course course) {
        List<Pays> liste = new ArrayList<>();
        String query = "SELECT distinct p.idPays, p.sigle, p.nom, p.langue \n" +
                "FROM apipays p \n" +
                "JOIN apipilote pi ON p.idPays=pi.idPays\n" +
                "JOIN apiclassement cl ON cl.idPilote = pi.idPilote\n" +
                "WHERE cl.idCourse = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, course.getIdCourse());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int idPays = rs.getInt("idPays");
                String sigle = rs.getString("sigle");
                String nomPays = rs.getString("nom");
                String langue = rs.getString("langue");
                Pays p = new Pays(idPays, sigle, nomPays, langue);
                if (!liste.contains(p)) {
                    liste.add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println("erreur sql : " + e);
        }
        return liste;
    }

    @Override
    public Pilote vainqueur(Course course) {
        Pilote p = null;
        String query = "select * from apiclassement cl join apipilote pi on cl.idpilote = pi.idpilote where cl.idcourse = ? and cl.place = 1";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, course.getIdCourse());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                // aide par SatckOverflow pour savoir que je pouvais mettre les noms des colonnes pour qu'ils reconnaissent mieux quand il y a plusieurs tables
                p = new Pilote(rs.getInt("idPilote"), rs.getString("matricule"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("datenaiss").toLocalDate());
            }
        } catch (SQLException e) {
            System.err.println("erreur sql : " + e);
        }
        return p;
    }

    @Override
    public boolean addPilote(Course course, Pilote pi) {
        String query1 = "insert into apiclassement(place, gain, idpilote, idCourse) values(?,?,?,?)";
        String query2 = "select idclassement from apiclassement where place=? and gain=? and idPilote=? and idCourse=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2)
        ) {
            pstm1.setInt(1, 0);
            pstm1.setBigDecimal(2, BigDecimal.ZERO);
            pstm1.setInt(3, pi.getIdPilote());
            pstm1.setInt(4, course.getIdCourse());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setInt(1, 0);
                pstm2.setBigDecimal(2, BigDecimal.ZERO);
                pstm2.setInt(3, pi.getIdPilote());
                pstm2.setInt(4, course.getIdCourse());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    return true;
                } else {
                    System.err.println("record introuvable");
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql : " + e);
            return false;
        }
    }

    @Override
    public boolean supPilote(Course c, Pilote pi) {
        String query = "delete from apiclassement where idcourse = ? and idpilote = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, c.getIdCourse());
            pstm.setInt(2, pi.getIdPilote());
            int n = pstm.executeUpdate();
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
    public Classement resultat() {
        return null;
    }

    @Override
    public boolean modif() {
        return false;
    }

    @Override
    public List<Pilote> listePilotesDuPays(Course course) {
        List<Pilote> liste = new ArrayList<>();
        String query = "select pi.* from apipilote pi\n" +
                "JOIN apiclassement cl on cl.idpilote = pi.idPilote\n" +
                "JOIN apicourse c on c.idcourse=cl.idcourse\n" +
                "JOIN apiville v on v.idVille=c.idVille\n" +
                "JOIN apipays p on p.idPays=v.idpays and p.idpays=pi.idpays\n" +
                "WHERE c.idCourse=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, course.getIdCourse());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int idPilote = rs.getInt("idpilote");
                String matriculePilote = rs.getString("matricule");
                String nomPilote = rs.getString("nom");
                String prenomPilote = rs.getString("prenom");
                LocalDate dateNaissPilote = rs.getDate("dateNaiss").toLocalDate();
                Pilote pi = new Pilote(idPilote, matriculePilote, nomPilote, prenomPilote, dateNaissPilote);
                liste.add(pi);
            }
        } catch (SQLException e) {
            System.err.println("erreur sql : " + e);
        }
        return liste;
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
