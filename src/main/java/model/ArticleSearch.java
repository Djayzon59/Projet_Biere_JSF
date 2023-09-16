package model;

public class ArticleSearch {
    private int id;
    private String libelle;
    private int volume;
    private float titrageMin;
    private float titrageMax;
    private Marque marque;
    private Pays pays;
    private Continent continent;
    private Fabricant fabricant;
    private Couleur couleur;
    private TypeBiere typeBiere;

    public ArticleSearch() {
        marque = new Marque();
        fabricant = new Fabricant();
        couleur = new Couleur();
        typeBiere = new TypeBiere();
        pays = new Pays();
        continent =new Continent();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getVolume() {
        return volume;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }


    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public Fabricant getFabricant() {
        return fabricant;
    }

    public void setFabricant(Fabricant fabricant) {
        this.fabricant = fabricant;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public TypeBiere getTypeBiere() {
        return typeBiere;
    }

    public void setTypeBiere(TypeBiere typeBiere) {
        this.typeBiere = typeBiere;
    }

    public float getTitrageMin() {
        return titrageMin;
    }

    public void setTitrageMin(float titrageMin) {
        this.titrageMin = titrageMin;
    }

    public float getTitrageMax() {
        return titrageMax;
    }

    public void setTitrageMax(float titrageMax) {
        this.titrageMax = titrageMax;
    }

    @Override
    public String toString() {
        return "ArticleSearch{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", volume=" + volume +
                ", titrageMin=" + titrageMin +
                ", titrageMax=" + titrageMax +
                ", marque=" + marque +
                ", continent=" + continent +
                ", fabricant=" + fabricant +
                ", couleur=" + couleur +
                ", typeBiere=" + typeBiere +
                '}';
    }
}
