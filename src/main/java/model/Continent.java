package model;

import java.util.Objects;

public class Continent {
    private int id ;
    private String libelle;


    public Continent() {
    }

    public Continent(int id) {
        this.id = id;
    }

    public Continent(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
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

        Continent continent = (Continent) o;

        return id == continent.id;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (libelle != null ? libelle.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return libelle ;
    }
}
