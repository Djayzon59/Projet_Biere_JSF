package model;

public class Fabricant {
    private int id = 0;
    private String libelle;
    private int nombreMarques;


    public Fabricant() {
    }

    public Fabricant(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Fabricant(int id, String libelle, int nombreMarques) {
        this.id = id;
        this.libelle = libelle;
        this.nombreMarques = nombreMarques;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fabricant fabricant = (Fabricant) o;

        return id == fabricant.id;
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
