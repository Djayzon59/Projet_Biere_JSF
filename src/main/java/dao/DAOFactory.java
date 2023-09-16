package dao;

public class DAOFactory {

    public static CouleurDAO getCouleurDAO(){return new CouleurDAO();}
    public static TypeDAO getTypeDAO(){return new TypeDAO();}
    public static MarqueDAO getMarqueDAO(){return new MarqueDAO();}
    public static PaysDAO getPaysDAO(){return new PaysDAO();}
    public static FabricantDAO getFabricantDAO(){return new FabricantDAO();}
    public static ContinentDAO getContinentDAO(){return new ContinentDAO();}
    public static ArticleDAO getArticleDAO(){return new ArticleDAO();}



}
