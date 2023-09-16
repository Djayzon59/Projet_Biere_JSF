package dao;

import model.Marque;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MarqueDAO extends DAO<Marque, Marque, Integer> {
    @Override
    public Marque getByID(Integer integer) {
        return null;
    }

    @Override
    public ArrayList<Marque> getAll() {
        ResultSet rs;
        ArrayList<Marque> liste = new ArrayList<Marque>();
        try (Statement stmt = connexion.createStatement()) {
            String strCmd = "SELECT ID_MARQUE, NOM_MARQUE, NB_ARTICLES = (select count(*) from ARTICLE as A where A.ID_MARQUE = M.ID_MARQUE) from MARQUE as M order by ID_MARQUE";
            rs = stmt.executeQuery(strCmd);
            while (rs.next()) {
                liste.add(new Marque(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<Marque> getLike(Marque objet) {
        return null;
    }

    @Override
    public boolean insert(Marque marque) {
        boolean retour = true;
        String sqlRequest = "insert into MARQUE values (?,?,?)";
        try (PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequest, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, marque.getLibelle());
            preparedStatement.setInt(2,marque.getPays().getId());
            preparedStatement.setInt(3, marque.getFabricant().getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next())
                marque.setId(rs.getInt(1));
            rs.close();

        } catch (SQLException e) { retour = false;
        System.out.print(e);}
        return retour;
    }

    @Override
    public boolean update(Marque marque) {
        boolean retour = true;
        String sqlRequest = "update Marque set nom_marque = ? where id_marque = ?";
        try(PreparedStatement pStmt = connexion.prepareStatement(sqlRequest)) {
            pStmt.setString(1, marque.getLibelle());
            pStmt.setInt(2, marque.getId());
            int rowUpdated = pStmt.executeUpdate();
            if (rowUpdated == 0) {
                retour = false;
                marque.setId(0);
            }
        }catch (SQLException e){ retour = false;}
        return retour;

    }

    @Override
    public boolean delete(Marque marque) {
        boolean retour = true;
        String sqlRequest = "delete FROM marque where ID_MARQUE=?";
        try (PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequest)) {
            preparedStatement.setInt(1, marque.getId());
            if (preparedStatement.executeUpdate() == 0) retour = false;
        } catch (SQLException e) {
            retour = false;
        }
        return retour;
    }
}
