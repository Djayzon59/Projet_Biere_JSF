package model;

public class Pays {
    private int id;
    private String libelle;
    private Continent continent;
    private int nombreMarques;


    public Pays() {
        continent=new Continent();
    }

    public Pays(int id) {
        this.id = id;
    }

    public Pays(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
        continent = new Continent();
    }

    public Pays(int id, String libelle, int nombreMarques, Continent continent) {
        this.id = id;
        this.libelle = libelle;
        this.continent=continent;
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

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public int getNombreMarques() {
        return nombreMarques;
    }

    public void setNombreMarques(int nombreMarques) {
        this.nombreMarques = nombreMarques;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pays pays = (Pays) o;

        return id == pays.id;
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
