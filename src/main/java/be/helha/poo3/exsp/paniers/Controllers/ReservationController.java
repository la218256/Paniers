package be.helha.poo3.exsp.paniers.Controllers;

import be.helha.poo3.exsp.paniers.dao.ReservationDao;
import be.helha.poo3.exsp.paniers.modeles.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    // Injection de dépendance pour le DAO des réservations
    @Autowired
    private ReservationDao reservationDao;

    // Méthode pour récupérer toutes les réservations
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationDao.listerReservation();
    }

    // Méthode pour récupérer une réservation spécifique par son ID
    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable int id) {
        return reservationDao.getReservation(id);
    }

    // Méthode pour ajouter une nouvelle réservation
    @PostMapping
    public boolean addReservation(@RequestBody Reservation reservation) {
        return reservationDao.ajouterReservation(reservation);
    }

    // Méthode pour mettre à jour une réservation existante
    @PutMapping("/{id}")
    public boolean updateReservation(@PathVariable int id, @RequestBody Reservation reservation) {
        reservation.setId(id);  // S'assurer que l'ID de la réservation est bien celui fourni dans l'URL
        return reservationDao.modifierReservation(reservation);
    }

    // Méthode pour supprimer une réservation par son ID
    @DeleteMapping("/{id}")
    public boolean deleteReservation(@PathVariable int id) {
        return reservationDao.supprimerReservation(id);
    }
}
