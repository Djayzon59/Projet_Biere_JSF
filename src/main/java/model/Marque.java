package model;

public class Marque {

    private int id =0;
    private String libelle;
    private Pays pays;
    private Fabricant fabricant;
    private int nombreArticles;

    public Marque() {
        fabricant=new Fabricant();
        pays=new Pays();
    }

    public Marque(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
        pays= new Pays();
        fabricant=new Fabricant();
    }

    public Marque(int id, String libelle, int nombreArticles) {
        this.id = id;
        this.libelle = libelle;
        this.nombreArticles = nombreArticles;
        pays=new Pays();
        fabricant=new Fabricant();
    }
    public Marque(int id, String libelle, Pays pays, Fabricant fabricant){
        this.id = id;
        this.libelle = libelle;
        this.pays = pays;
        this.fabricant = fabricant;
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


    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Fabricant getFabricant() {
        return fabricant;
    }

    public void setFabricant(Fabricant fabricant) {
        this.fabricant = fabricant;
    }

    public int getNombreArticles() {
        return nombreArticles;
    }

    public void setNombreArticles(int nombreArticles) {
        this.nombreArticles = nombreArticles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Marque marque = (Marque) o;

        return id == marque.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
