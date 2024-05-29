package mvc.model;

import automobile.metier.Pilote;
import myconnections.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModelPiloteDB extends DAOPilote {
    protected Connection dbConnect;

    public ModelPiloteDB() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            System.exit(1);
        }
    }

    @Override
    public Pilote addPilote(Pilote pilote) {
        String query1 = "insert into APIPILOTE(matricule,nom,prenom,datenaiss) values (?,?,?,?)";
        String query2 = "select idPilote from APIPILOTE where matricule=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, pilote.getMatricule());
            pstm1.setString(2, pilote.getNom());
            pstm1.setString(3, pilote.getPrenom());
            pstm1.setDate(4, Date.valueOf(pilote.getDateNaiss()));
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setString(1, pilote.getMatricule());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int idPilote = rs.getInt(1);
                    pilote.setIdPilote(idPilote);
                    notifyObservers();
                    return pilote;
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
    public boolean removePilote(Pilote pilote) {
        String query = "delete from APIPILOTE where idPilote=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, pilote.getIdPilote());
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
    public Pilote updatePilote(Pilote pilote) {
        String query = "update APIPILOTE set matricule=?, nom=?, prenom=?, dateNaiss=? where idPilote=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, pilote.getMatricule());
            pstm.setString(2, pilote.getNom());
            pstm.setString(3, pilote.getPrenom());
            pstm.setDate(4, Date.valueOf(pilote.getDateNaiss()));
            pstm.setInt(5, pilote.getIdPilote());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) {
                return readPilote(pilote.getIdPilote());
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql : " + e);
            return null;
        }
    }

    @Override
    public Pilote readPilote(int idPilote) {
        String query = "select * from APIPILOTE where idPilote=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idPilote);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                LocalDate dateNaiss = rs.getDate(5).toLocalDate();
                Pilote pi = new Pilote(idPilote, matricule, nom, prenom, dateNaiss);
                return pi;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql : " + e);
            return null;
        }
    }

    @Override
    public List<Pilote> getPilote() {
        List<Pilote> lp = new ArrayList<>();
        String query = "select * from APIPILOTE";
        try (Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int idPilote = rs.getInt(1);
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                LocalDate dateNaiss = rs.getDate(5).toLocalDate();
                Pilote pi = new Pilote(idPilote, matricule, nom, prenom, dateNaiss);
                lp.add(pi);
            }
            return lp;
        } catch (SQLException e) {
            System.err.println("erreur sql : " + e);
            return null;
        }
    }

    @Override
    public List getNotification() {
        return getPilote();
    }
}
