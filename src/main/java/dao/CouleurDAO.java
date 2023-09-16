package dao;

import model.Couleur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CouleurDAO extends DAO<Couleur, Couleur, Integer> {


    @Override
    public ArrayList<Couleur> getAll() {
        ResultSet rs;
        ArrayList<Couleur> liste = new ArrayList<Couleur>();
        try (Statement stmt = connexion.createStatement()) {
            String strCmd = "SELECT ID_COULEUR, NOM_COULEUR, NB_ARTICLES = (select count(*) from ARTICLE as A where A.id_couleur = C.ID_COULEUR) from couleur as C order by ID_COULEUR";
            rs = stmt.executeQuery(strCmd);
            while (rs.next()) {liste.add(new Couleur(rs.getInt(1), rs.getString(2), rs.getInt(3)));}
            rs.close();
        } catch (Exception e) { e.printStackTrace();}
        return liste;
    }


    @Override
    public boolean insert(Couleur couleur) {
        boolean retour = true;
        String sqlRequest = "insert into Couleur values (?)";
        try (PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequest, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, couleur.getLibelle());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next())
                couleur.setId(rs.getInt(1));
            rs.close();

        } catch (SQLException e) { retour = false;}
        return retour;
    }

    @Override
    public boolean update(Couleur couleur) {
        boolean retour = true;
        String sqlRequest = "update Couleur set nom_couleur = ? where id_couleur = ?";
        try(PreparedStatement pStmt = connexion.prepareStatement(sqlRequest)) {
            pStmt.setString(1, couleur.getLibelle());
            pStmt.setInt(2, couleur.getId());
            int rowUpdated = pStmt.executeUpdate();
            if (rowUpdated == 0) {
                retour = false;
                couleur.setId(0);
            }
        }catch (SQLException e){ retour = false;}
        return retour;

    }

    @Override
    public boolean delete(Couleur couleur) {
        boolean retour = true;
        String sqlRequest = "delete FROM couleur where ID_COULEUR=?";
        try (PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequest)) {
            preparedStatement.setInt(1, couleur.getId());
            if (preparedStatement.executeUpdate() == 0) retour = false;
        } catch (SQLException e) {
            retour = false;
        }
        return retour;
    }

    @Override
    public Couleur getByID(Integer id) {
        Couleur couleur = null;
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("SELECT ID_COULEUR,NOM_COULEUR FROM couleur where ID_COULEUR=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                couleur = new Couleur(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return couleur;
    }

    @Override
    public ArrayList<Couleur> getLike(Couleur objet) {
        return null;
    }
}
