package model;

public class TypeBiere {

    private int id = 0;
    private String libelle;
    private int nombreArticle;

    public TypeBiere() {
    }

    public TypeBiere(int id) {
        this.id = id;
    }

    public TypeBiere(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public TypeBiere(int id, String libelle, int nombreArticle) {
        this.id = id;
        this.libelle = libelle;
        this.nombreArticle=nombreArticle;
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

    public int getNombreArticle() {
        return nombreArticle;
    }

    public void setNombreArticle(int nombreArticle) {
        this.nombreArticle = nombreArticle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeBiere typeBiere = (TypeBiere) o;

        return id == typeBiere.id;
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
