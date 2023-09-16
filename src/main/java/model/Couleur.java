package model;

public class Couleur {
    private int id = 0;
    private String libelle;
    private int nombreArticles;


    public Couleur() {
    }

    public Couleur(int id) {
        this.id = id;
    }

    public Couleur(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Couleur(int id, String libelle, int nombreArticles) {
        this.id = id;
        this.libelle = libelle;
        this.nombreArticles = nombreArticles;
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

        Couleur couleur = (Couleur) o;

        return id == couleur.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return  libelle ;
    }
}
