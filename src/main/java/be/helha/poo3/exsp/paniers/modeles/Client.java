package be.helha.poo3.exsp.paniers.modeles;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {

    // Attributs du client
    private int id;  // Identifiant unique du client
    private String prenom;  // Prénom du client
    private String nom;  // Nom du client
    private String commune;  // Commune ou ville de résidence du client

    // Liste des réservations (ou paniers) associées à ce client
    private List<Reservation> paniers = new ArrayList<>();

    // Constructeurs

    // Constructeur par défaut
    public Client() {}

    // Constructeur avec paramètres pour initialiser un client
    public Client(String prenom, String nom, String commune) {
        this.prenom = prenom;
        this.nom = nom;
        this.commune = commune;
    }

    // Getters et Setters

    // Retourne l'identifiant du client
    public int getId() {
        return id;
    }

    // Modifie l'identifiant du client
    public void setId(int id) {
        this.id = id;
    }

    // Retourne le prénom du client
    public String getPrenom() {
        return prenom;
    }

    // Modifie le prénom du client
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    // Retourne le nom du client
    public String getNom() {
        return nom;
    }

    // Modifie le nom du client
    public void setNom(String nom) {
        this.nom = nom;
    }

    // Retourne la commune du client
    public String getCommune() {
        return commune;
    }

    // Modifie la commune du client
    public void setCommune(String commune) {
        this.commune = commune;
    }

    // Retourne la liste des paniers associés au client
    public List<Reservation> getPaniers() {
        return paniers;
    }

    // Modifie la liste des paniers associés au client
    public void setPaniers(List<Reservation> paniers) {
        this.paniers = paniers;
    }

    // Méthode equals : pour comparer deux objets Client
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                Objects.equals(prenom, client.prenom) &&
                Objects.equals(nom, client.nom) &&
                Objects.equals(commune, client.commune) &&
                Objects.equals(paniers, client.paniers);
    }

    // Méthode hashCode : pour générer un hash unique basé sur les attributs du client
    @Override
    public int hashCode() {
        return Objects.hash(id, prenom, nom, commune, paniers);
    }

    // Méthode toString : pour représenter l'objet Client sous forme de chaîne de caractères
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", commune='" + commune + '\'' +
                '}';
    }
}
