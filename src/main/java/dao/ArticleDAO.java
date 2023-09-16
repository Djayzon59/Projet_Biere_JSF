package dao;

import model.*;

import java.sql.*;
import java.util.ArrayList;

public class ArticleDAO extends DAO<Article, ArticleSearch, Integer> {
    @Override
    public Article getByID(Integer integer) {
        return null;
    }

    @Override
    public ArrayList<Article> getAll() {
        ResultSet rs;
        ArrayList<Article> liste = new ArrayList<Article>();
        try (Statement stmt = connexion.createStatement()) {
            String strCmd = "select A.ID_ARTICLE, A.NOM_ARTICLE, A.PRIX_ACHAT, A.VOLUME, A.TITRAGE,\n" +
                    "       M.ID_MARQUE, M.NOM_MARQUE,\n" +
                    "       C.ID_COULEUR, C.NOM_COULEUR,\n" +
                    "       T.ID_TYPE, T.NOM_TYPE\n" +
                    "from ARTICLE AS A\n" +
                    "JOIN MARQUE AS M on A.ID_MARQUE = M.ID_MARQUE\n" +
                    "LEFT JOIN COULEUR C on A.ID_COULEUR = C.ID_COULEUR\n" +
                    "LEFT JOIN TYPEBIERE T on A.ID_TYPE = T.ID_TYPE\n";

            rs = stmt.executeQuery(strCmd);
            while (rs.next()) {
                liste.add(new Article(rs.getInt(1), rs.getString(2),
                        rs.getFloat(3), rs.getInt(4),
                        rs.getFloat(5),
                        new Marque(rs.getInt(6), rs.getString(7)),
                        new Couleur(rs.getInt(8), rs.getString(9)),
                        new TypeBiere(rs.getInt(10), rs.getString(11))));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<Article> getLike(ArticleSearch articleSearch) {
        ResultSet rs;
        ArrayList<Article> liste = new ArrayList<>();
        try {
            String procedureStockee = "{call SP_QBE_VUE_ARTICLE (?,?,?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement cStmt = this.connexion.prepareCall(procedureStockee);
            cStmt.setString(1, articleSearch.getLibelle());
            cStmt.setFloat(2, articleSearch.getVolume());
            cStmt.setFloat(3, articleSearch.getTitrageMin());
            cStmt.setFloat(4, articleSearch.getTitrageMax());
            cStmt.setInt(5, articleSearch.getMarque().getId());
            cStmt.setInt(6, articleSearch.getFabricant().getId());
            if(articleSearch.getPays() == null) articleSearch.setPays(new Pays(0));
            cStmt.setInt(7, articleSearch.getPays().getId());
            if(articleSearch.getContinent() == null) articleSearch.setContinent(new Continent(0));
            cStmt.setInt(8, articleSearch.getContinent().getId());
            if(articleSearch.getCouleur() == null) articleSearch.setCouleur(new Couleur(0));
            cStmt.setInt(9, articleSearch.getCouleur().getId());
            if(articleSearch.getTypeBiere() == null) articleSearch.setTypeBiere(new TypeBiere(0));
            cStmt.setInt(10, articleSearch.getTypeBiere().getId());
            cStmt.setNull(11, Types.INTEGER);
            cStmt.setNull(12, Types.INTEGER);
            cStmt.execute();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                Article newArticle = new Article(rs.getInt(1), rs.getString(2), rs.getFloat(3),
                        rs.getInt(4), rs.getFloat(5));
                newArticle.setMarque(new Marque(rs.getInt(6), rs.getString(7)));
                newArticle.getMarque().setFabricant(new Fabricant(rs.getInt(8), rs.getString(9)));
                newArticle.getMarque().setPays(new Pays(rs.getInt(10), rs.getString(11)));
                newArticle.getMarque().getPays().setContinent(new Continent(rs.getInt(12), rs.getString(13)));
                newArticle.setCouleur(new Couleur(rs.getInt(14), rs.getString(15)));
                newArticle.setType(new TypeBiere(rs.getInt(16), rs.getString(17)));
                liste.add(newArticle);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return liste;
    }

    @Override
    public boolean insert(Article article) {
        boolean retour = true;
        String sqlRequest = "insert into Article values (?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequest, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, article.getLibelle());
            preparedStatement.setFloat(2, article.getPrixAchat());
            preparedStatement.setInt(3, article.getVolume());
            preparedStatement.setFloat(4, article.getTitrage());
            preparedStatement.setInt(5, article.getMarque().getId());
            if(article.getCouleur() == null) preparedStatement.setNull(6, Types.INTEGER);
            else preparedStatement.setInt(6, article.getCouleur().getId());
            if(article.getType() == null) preparedStatement.setNull(7, Types.INTEGER);
            else preparedStatement.setInt(7, article.getType().getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next())
                article.setId(rs.getInt(1));
            rs.close();
        } catch (SQLException e) {
            System.out.print(e);
            retour = false;
        }
        return retour;
    }

    @Override
    public boolean update(Article article) throws SQLException {
        boolean retour = true;
        String sqlRequest = "update Article set nom_article = ?, prix_achat = ?, volume = ?, titrage = ?, id_marque = ?, id_couleur = ?, id_type = ?  where id_article = ?";
        try (PreparedStatement pStmt = connexion.prepareStatement(sqlRequest)) {
            pStmt.setString(1, article.getLibelle());
            pStmt.setFloat(2, article.getPrixAchat());
            pStmt.setInt(3, article.getVolume());
            pStmt.setFloat(4, article.getTitrage());
            pStmt.setInt(5, article.getMarque().getId());
            if(article.getCouleur() == null) pStmt.setNull(6, Types.INTEGER);
            else pStmt.setInt(6, article.getCouleur().getId());
            if(article.getType() == null) pStmt.setNull(7, Types.INTEGER);
            else pStmt.setInt(7, article.getType().getId());
            pStmt.setInt(8, article.getId());
            int rowUpdated = pStmt.executeUpdate();
            if (rowUpdated == 0) {
                retour = false;
                article.setId(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            retour = false;
        }
        return retour;
    }

    @Override
    public boolean delete(Article article) {
        boolean retour = true;
        String sqlRequest = "delete FROM article where ID_ARTICLE=?";
        try (PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequest)) {
            preparedStatement.setInt(1, article.getId());
            if (preparedStatement.executeUpdate() == 0) retour = false;
        } catch (SQLException e) {
            retour = false;
        }
        return retour;
    }
}
