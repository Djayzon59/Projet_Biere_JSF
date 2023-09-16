package model;

public class Article {
    private int id;
    private String libelle;
    private Float prixAchat;
    private int volume;
    private float titrage;
    private Marque marque;
    private Couleur couleur;
    private TypeBiere typeBiere;

    public Article() {
        couleur = new Couleur();
        typeBiere = new TypeBiere();
        marque = new Marque();
    }

    public Article(int id, String libelle, float prixAchat, int volume, float titrage, Marque marque, Couleur couleur, TypeBiere typeBiere) {
        this.id = id;
        this.libelle = libelle;
        this.prixAchat = prixAchat;
        this.volume = volume;
        this.titrage = titrage;
        this.marque = marque;
        this.couleur = couleur;
        this.typeBiere = typeBiere;
    }

    public Article(int id, String libelle, Float prixAchat, int volume, float titrage) {
        this.id = id;
        this.libelle = libelle;
        this.prixAchat = prixAchat;
        this.volume = volume;
        this.titrage = titrage;
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

    public Float getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(Float prixAchat) {
        this.prixAchat = prixAchat;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public float getTitrage() {
        return titrage;
    }

    public void setTitrage(float titrage) {
        this.titrage = titrage;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public TypeBiere getType() {
        return typeBiere;
    }

    public void setType(TypeBiere type) {
        this.typeBiere = type;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
