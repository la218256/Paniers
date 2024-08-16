package be.helha.poo3.exsp.paniers.dao;

import be.helha.poo3.exsp.paniers.modeles.Reservation;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface pour les opérations de persistance sur les objets Reservation.
 *
 * Cette interface définit les méthodes nécessaires pour effectuer les
 * opérations CRUD (Create, Read, Update, Delete) sur les objets Reservation
 * dans la base de données.
 */
@Repository
public interface ReservationDao extends Dao {

    /**
     * Ajoute une nouvelle réservation dans la base de données.
     *
     * @param reservation La réservation à ajouter.
     * @return true si l'ajout a réussi, false sinon.
     */
    boolean ajouterReservation(Reservation reservation);

    /**
     * Récupère une réservation à partir de son identifiant unique.
     *
     * @param id L'identifiant de la réservation.
     * @return La réservation correspondant à l'identifiant, ou null si elle n'existe pas.
     */
    Reservation getReservation(int id);

    /**
     * Récupère la liste de toutes les réservations.
     *
     * @return Une liste de toutes les réservations présentes dans la base de données.
     */
    List<Reservation> listerReservation();

    /**
     * Supprime une réservation de la base de données en utilisant son identifiant.
     *
     * @param id L'identifiant de la réservation à supprimer.
     * @return true si la suppression a réussi, false sinon.
     */
    boolean supprimerReservation(int id);

    /**
     * Modifie les informations d'une réservation existante dans la base de données.
     *
     * @param reservation La réservation avec les nouvelles informations à mettre à jour.
     * @return true si la modification a réussi, false sinon.
     */
    boolean modifierReservation(Reservation reservation);
}


