package dao;

import model.Continent;
import model.Pays;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PaysDAO extends DAO<Pays, Pays, Integer> {
    @Override
    public Pays getByID(Integer integer) {
        return null;
    }

    @Override
    public ArrayList<Pays> getAll() {
        ResultSet rs;
        ArrayList<Pays> liste = new ArrayList<Pays>();
        try (Statement stmt = connexion.createStatement()) {
            String strCmd = "SELECT P.ID_PAYS, P.NOM_PAYS, COUNT(M.ID_PAYS) AS NB_MARQUE, P.ID_CONTINENT, C.NOM_CONTINENT\n" +
                    "FROM PAYS AS P\n" +
                    "LEFT JOIN MARQUE AS M ON M.ID_PAYS = P.ID_PAYS\n" +
                    "JOIN CONTINENT AS C ON C.ID_CONTINENT = P.ID_CONTINENT\n" +
                    "GROUP BY P.ID_PAYS, P.NOM_PAYS, P.ID_CONTINENT, C.NOM_CONTINENT\n" +
                    "ORDER BY P.ID_PAYS";
            rs = stmt.executeQuery(strCmd);
            while (rs.next()) {
                liste.add(new Pays(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        new Continent(rs.getInt(4), rs.getString(5))));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<Pays> getLike(Pays objet) {
        return null;
    }

    @Override
    public boolean insert(Pays pays) {
        boolean retour = true;
        String sqlRequest = "insert into Pays values (?,?)";
        try (PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequest, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, pays.getLibelle());
            preparedStatement.setInt(2, pays.getContinent().getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next())
                pays.setId(rs.getInt(1));
            rs.close();

        } catch (SQLException e) {
            retour = false;
        }
        return retour;
    }

    @Override
    public boolean update(Pays pays) throws SQLException {
        boolean retour = true;
        String sqlRequest = "update Pays set nom_pays = ? where id_pays = ?";
        try (PreparedStatement pStmt = connexion.prepareStatement(sqlRequest)) {
            pStmt.setString(1, pays.getLibelle());
            pStmt.setInt(2, pays.getId());
            int rowUpdated = pStmt.executeUpdate();
            if (rowUpdated == 0) {
                retour = false;
                pays.setId(0);
            }
        } catch (SQLException e) {
            retour = false;
        }
        return retour;
    }

    @Override
    public boolean delete(Pays pays) {
        boolean retour = true;
        String sqlRequest = "delete FROM pays where ID_PAYS=?";
        try (PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequest)) {
            preparedStatement.setInt(1, pays.getId());
            if (preparedStatement.executeUpdate() == 0) retour = false;
        } catch (SQLException e) {
            retour = false;
        }
        return retour;
    }
}
