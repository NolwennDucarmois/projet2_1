package mvc.model;

import automobile.metier.Ville;
import myconnections.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModelVilleDB extends DAOVille {
    protected Connection dbConnect;

    public ModelVilleDB() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            System.exit(1);
        }
    }

    @Override
    public Ville addVille(Ville ville) {
        String query1 = "insert into APIVILLE (nom,latitude,longitude) values (?,?,?)";
        String query2 = "select idVille from APIVILLE where latitude=? and longitude=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, ville.getNom());
            pstm1.setDouble(2, ville.getLatitude());
            pstm1.setDouble(3, ville.getLongitude());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setDouble(1, ville.getLatitude());
                pstm2.setDouble(2, ville.getLongitude());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int idVille = rs.getInt(1);
                    ville.setIdVille(idVille);
                    notifyObservers();
                    return ville;
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
    public boolean removeVille(Ville ville) {
        String query = "delete from APIVILLE where idVille=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, ville.getIdVille());
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
    public Ville updateVille(Ville ville) {
        String query = "update APIVILLE set nom=?,latitude=?,longitude=? where idVille=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, ville.getNom());
            pstm.setDouble(2, ville.getLatitude());
            pstm.setDouble(3, ville.getLongitude());
            pstm.setInt(4, ville.getIdVille());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) {
                return readVille(ville.getIdVille());
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql : " + e);
            return null;
        }
    }

    @Override
    public Ville readVille(int idVille) {
        String query = "select * from APIVILLE where idVille=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idVille);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String nom = rs.getString(2);
                double latitude = rs.getDouble(3);
                double longitude = rs.getDouble(4);
                Ville v = new Ville(idVille, nom, latitude, longitude);
                return v;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql : " + e);
            return null;
        }
    }

    @Override
    public List<Ville> getVilles() {
        List<Ville> lv = new ArrayList<>();
        String query = "select * from APIVILLE";
        try (Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int idVille = rs.getInt(1);
                String nom = rs.getString(2);
                double latitude = rs.getDouble(3);
                double longitude = rs.getDouble(4);
                Ville v = new Ville(idVille, nom, latitude, longitude);
                lv.add(v);
            }
            return lv;
        } catch (SQLException e) {
            System.err.println("erreur sql : " + e);
            return null;
        }
    }

    @Override
    public List getNotification() {
        return getVilles();
    }
}
