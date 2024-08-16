package be.helha.poo3.exsp.paniers.modeles;

import java.time.LocalDate;
import java.util.Objects;

public class Reservation {

    // Attributs de la réservation
    private int id;  // Identifiant unique de la réservation (de type int)
    private LocalDate dateCommande;  // Date de la commande
    private int quantite;  // Quantité de produits réservés
    private int prix;  // Prix total de la réservation
    private Client client;  // Client associé à la réservation

    // Constructeurs

    // Constructeur par défaut
    public Reservation() {}

    // Constructeur avec paramètres pour initialiser une réservation
    public Reservation(LocalDate dateCommande, int quantite, int prix, Client client) {
        this.dateCommande = dateCommande;
        this.quantite = quantite;
        this.prix = prix;  // Initialisation du prix total de la réservation
        this.client = client;
    }

    // Getters et Setters

    // Retourne l'identifiant de la réservation
    public int getId() {
        return id;
    }

    // Modifie l'identifiant de la réservation
    public void setId(int id) {
        this.id = id;
    }

    // Retourne la date de la commande
    public LocalDate getDateCommande() {
        return dateCommande;
    }

    // Modifie la date de la commande
    public void setDateCommande(LocalDate dateCommande) {
        this.dateCommande = dateCommande;
    }

    // Retourne la quantité de produits réservés
    public int getQuantite() {
        return quantite;
    }

    // Modifie la quantité de produits réservés
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    // Retourne le prix total de la réservation
    public int getPrix() {
        return prix;
    }

    // Modifie le prix total de la réservation
    public void setPrix(int prix) {
        this.prix = prix;
    }

    // Retourne le client associé à la réservation
    public Client getClient() {
        return client;
    }

    // Modifie le client associé à la réservation
    public void setClient(Client client) {
        this.client = client;
    }

    // Méthode equals : pour comparer deux objets Reservation
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id == that.id &&
                quantite == that.quantite &&
                prix == that.prix &&
                Objects.equals(dateCommande, that.dateCommande) &&
                Objects.equals(client, that.client);
    }

    // Méthode hashCode : pour générer un hash unique basé sur les attributs de la réservation
    @Override
    public int hashCode() {
        return Objects.hash(id, dateCommande, quantite, prix, client);
    }

    // Méthode toString : pour représenter l'objet Reservation sous forme de chaîne de caractères
    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", dateCommande=" + dateCommande +
                ", quantite=" + quantite +
                ", prix=" + prix +  // Ajout du prix dans le toString
                ", client=" + client.getId() +  // Affichage de l'ID du client associé
                '}';
    }
}
