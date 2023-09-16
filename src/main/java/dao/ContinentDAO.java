package dao;

import model.Continent;
import model.Couleur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ContinentDAO extends DAO<Continent, Continent, Integer> {
    @Override
    public Continent getByID(Integer integer) {
        return null;
    }

    @Override
    public ArrayList<Continent> getAll() {
        ResultSet rs;
        ArrayList<Continent> liste = new ArrayList<Continent>();
        try (Statement stmt = connexion.createStatement()) {
            String strCmd = "SELECT ID_CONTINENT, NOM_CONTINENT from continent order by ID_CONTINENT";
            rs = stmt.executeQuery(strCmd);
            while (rs.next()) {liste.add(new Continent(rs.getInt(1), rs.getString(2)));}
            rs.close();
        } catch (Exception e) { e.printStackTrace();}
        return liste;
    }

    @Override
    public ArrayList<Continent> getLike(Continent objet) {
        return null;
    }


    @Override
    public boolean insert(Continent objet) {
        return false;
    }

    @Override
    public boolean update(Continent objet) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Continent object) {
        return false;
    }
}
