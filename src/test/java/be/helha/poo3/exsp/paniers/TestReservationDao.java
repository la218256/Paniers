package be.helha.poo3.exsp.paniers;

import be.helha.poo3.exsp.paniers.dao.ClientDao;
import be.helha.poo3.exsp.paniers.dao.ReservationDao;
import be.helha.poo3.exsp.paniers.daoimpl.DaoFactory;
import be.helha.poo3.exsp.paniers.modeles.Client;
import be.helha.poo3.exsp.paniers.modeles.Reservation;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestReservationDao {

    // Déclaration des objets DAO pour les clients et les réservations
    private static ClientDao clientDao = (ClientDao) DaoFactory.getInstance().getDaoImpl(ClientDao.class);
    private static ReservationDao reservationDao = DaoFactory.getInstance().getDaoImpl(ReservationDao.class);

    // Déclaration des objets Client pour les tests
    private static Client client1;
    private static Client client2;
    private static Client client3;
    private static Client clientTest;

    // Déclaration des objets Reservation pour les tests
    private static Reservation reservation1;
    private static Reservation reservation2;
    private static Reservation reservation3;
    private static Reservation reservationTest;

    private static List<Reservation> reservationsAjoutees = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        // Configuration du DAO pour les tests
        DaoFactory.getInstance().setTestConfiguration();

        // Initialisation des objets Client
        client1 = new Client("Ahmed", "KHM", "JUMET");
        client2 = new Client("Ahmed", "KKK", "JUET");
        client3 = new Client("Ahmed", "ASD", "JUT");
        clientTest = new Client("Ah", "AS", "JU");

        // Initialisation des objets Reservation avec les clients correspondants
        reservation1 = new Reservation(LocalDate.of(2022, 11, 13), 2, 0, client1);  // Le prix sera calculé lors de l'ajout
        reservation2 = new Reservation(LocalDate.now(), 3, 0, client2);  // Le prix sera calculé lors de l'ajout
        reservation3 = new Reservation(LocalDate.now(), 1, 0, client3);  // Le prix sera calculé lors de l'ajout
        reservationTest = new Reservation(LocalDate.now(), 5, 0, clientTest);  // Le prix sera calculé lors de l'ajout
    }

    @Test
    @Order(1)
    public void testAjouter() {
        // Test de l'ajout d'un client et d'une réservation
        assertTrue(clientDao.ajouterClient(client1));
        boolean result = reservationDao.ajouterReservation(reservation1);
        assertTrue(result);
    }

    @Test
    @Order(2)
    public void testLister() {
        // Test de l'ajout de plusieurs clients et réservations, puis de la récupération de la liste des réservations
        assertTrue(clientDao.ajouterClient(client2));
        assertTrue(clientDao.ajouterClient(client3));

        reservationDao.ajouterReservation(reservation2);
        reservationDao.ajouterReservation(reservation3);

        List<Reservation> reservationsRecuperees = reservationDao.listerReservation();

        // Vérification que les réservations ajoutées sont bien présentes dans la liste
        assertTrue(reservationsRecuperees.stream().anyMatch(r -> r.getId() == reservation2.getId()));
        assertTrue(reservationsRecuperees.stream().anyMatch(r -> r.getId() == reservation3.getId()));
    }

    @Test
    @Order(3)
    public void testSimpleGetReservation() {
        // Test de la récupération d'une réservation spécifique après ajout
        assertTrue(clientDao.ajouterClient(clientTest));

        reservationDao.ajouterReservation(reservationTest);

        Reservation recupered = reservationDao.getReservation(reservationTest.getId());
        assertNotNull(recupered);
        assertEquals(reservationTest.getQuantite(), recupered.getQuantite());
    }

    /*
    @Test
    @Order(4)
    public void testListerReservationParClient() {
        // Test de la récupération des réservations par client
        List<Reservation> reservations = reservationDao.listerReservationParClient(client1.getId());
        assertNotNull(reservations);
        assertFalse(reservations.isEmpty(), "La liste des réservations ne doit pas être vide");

        assertTrue(reservations.stream().anyMatch(r -> r.getId() == reservation1.getId()),
                "La réservation testée doit être présente dans la liste");
    }
    */

    @Test
    @Order(4)
    public void testModifier() {
        // Test de la modification d'une réservation existante
        reservation3.setQuantite(10);
        assertTrue(reservationDao.modifierReservation(reservation3));

        Reservation reservationRecuperee = reservationDao.getReservation(reservation3.getId());
        assertEquals(10, reservationRecuperee.getQuantite());
    }

    @Test
    @Order(5)
    public void testSupp() {
        // Test de la suppression des réservations et des clients associés
        assertTrue(reservationDao.supprimerReservation(reservation1.getId()));
        assertTrue(clientDao.supprimerClient(client1.getId()));
        assertTrue(reservationDao.supprimerReservation(reservation2.getId()));
        assertTrue(clientDao.supprimerClient(client2.getId()));
        assertTrue(reservationDao.supprimerReservation(reservation3.getId()));
        assertTrue(clientDao.supprimerClient(client3.getId()));
        assertTrue(reservationDao.supprimerReservation(reservationTest.getId()));
        assertTrue(clientDao.supprimerClient(clientTest.getId()));
    }
}

