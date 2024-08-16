package be.helha.poo3.exsp.paniers.dao;

import be.helha.poo3.exsp.paniers.modeles.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface pour les opérations de persistance sur les objets Client.
 *
 * Cette interface définit les méthodes nécessaires pour effectuer les
 * opérations CRUD (Create, Read, Update, Delete) sur les objets Client
 * dans la base de données.
 */
@Repository
public interface ClientDao extends Dao {

    /**
     * Ajoute un nouveau client dans la base de données.
     *
     * @param client Le client à ajouter.
     * @return true si l'ajout a réussi, false sinon.
     */
    boolean ajouterClient(Client client);

    /**
     * Récupère un client à partir de son identifiant unique.
     *
     * @param id L'identifiant du client.
     * @return Le client correspondant à l'identifiant, ou null s'il n'existe pas.
     */
    Client getClient(int id);

    /**
     * Récupère la liste de tous les clients.
     *
     * @return Une liste de tous les clients présents dans la base de données.
     */
    List<Client> listerClients();

    /**
     * Supprime un client de la base de données en utilisant son identifiant.
     *
     * @param id L'identifiant du client à supprimer.
     * @return true si la suppression a réussi, false sinon.
     */
    boolean supprimerClient(int id);

    /**
     * Modifie les informations d'un client existant dans la base de données.
     *
     * @param client Le client avec les nouvelles informations à mettre à jour.
     * @return true si la modification a réussi, false sinon.
     */
    boolean modifierClient(Client client);
}
