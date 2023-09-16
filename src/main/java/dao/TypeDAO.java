package dao;

import model.TypeBiere;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TypeDAO extends DAO<TypeBiere,TypeBiere, Integer> {
    @Override
    public TypeBiere getByID(Integer integer) {
        return null;
    }

    @Override
    public ArrayList<TypeBiere> getAll() {
        ResultSet rs;
        ArrayList<TypeBiere> liste = new ArrayList<TypeBiere>();
        try (Statement stmt = connexion.createStatement()) {
            String strCmd = "SELECT ID_TYPE, NOM_TYPE, NB_ARTICLES = (select count(*) from ARTICLE as A where A.ID_TYPE = T.ID_TYPE) from TYPEBIERE as T order by ID_TYPE";
            rs = stmt.executeQuery(strCmd);
            while (rs.next()) {liste.add(new TypeBiere(rs.getInt(1), rs.getString(2), rs.getInt(3)));}
            rs.close();
        } catch (Exception e) { e.printStackTrace();}
        return liste;
    }


    @Override
    public boolean insert(TypeBiere typeBiere) {
        boolean retour = true;
        String sqlRequest = "insert into typebiere values (?)";
        try (PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequest, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, typeBiere.getLibelle());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next())
                typeBiere.setId(rs.getInt(1));
            rs.close();

        } catch (SQLException e) { retour = false;}
        return retour;
    }

    @Override
    public boolean update(TypeBiere typeBiere) throws SQLException {
        boolean retour = true;
        String sqlRequest = "update typebiere set nom_type = ? where id_type = ?";
        try(PreparedStatement pStmt = connexion.prepareStatement(sqlRequest)) {
            pStmt.setString(1, typeBiere.getLibelle());
            pStmt.setInt(2, typeBiere.getId());
            int rowUpdated = pStmt.executeUpdate();
            if (rowUpdated == 0) {
                retour = false;
                typeBiere.setId(0);
            }
        }catch (SQLException e){ retour = false;}
        return retour;
    }

    @Override
    public boolean delete(TypeBiere typeBiere) {
        boolean retour = true;
        String sqlRequest = "delete FROM typebiere where ID_TYPE=?";
        try (PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequest)) {
            preparedStatement.setInt(1, typeBiere.getId());
            if (preparedStatement.executeUpdate() == 0) retour = false;
        } catch (SQLException e) {
            retour = false;
        }
        return retour;
    }
    @Override
    public ArrayList<TypeBiere> getLike(TypeBiere objet) {
        return null;
    }
}
