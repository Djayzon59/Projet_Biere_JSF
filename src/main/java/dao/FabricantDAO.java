package dao;

import model.Fabricant;
import model.Marque;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FabricantDAO extends DAO<Fabricant, Fabricant, Integer> {
    @Override
    public Fabricant getByID(Integer integer) {
        return null;
    }

    @Override
    public ArrayList<Fabricant> getAll() {
        ResultSet rs;
        ArrayList<Fabricant> liste = new ArrayList<Fabricant>();
        try (Statement stmt = connexion.createStatement()) {
            String strCmd = "SELECT ID_FABRICANT, NOM_FABRICANT, NB_MARQUES = (select count(*) from MARQUE as M where M.ID_FABRICANT = F.ID_FABRICANT) from FABRICANT as F order by ID_FABRICANT";
            rs = stmt.executeQuery(strCmd);
            while (rs.next()) {
                liste.add(new Fabricant(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<Fabricant> getLike(Fabricant objet) {
        return null;
    }

    @Override
    public boolean insert(Fabricant objet) {
        return false;
    }

    @Override
    public boolean update(Fabricant objet) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Fabricant object) {
        return false;
    }
}
