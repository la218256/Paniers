package be.helha.poo3.exsp.paniers;

import be.helha.poo3.exsp.paniers.dao.ClientDao;
import be.helha.poo3.exsp.paniers.daoimpl.DaoFactory;
import be.helha.poo3.exsp.paniers.modeles.Client;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)  // Définit l'ordre des tests
public class TestClientDao {

    // Déclaration statique du DAO client
    private static ClientDao clientDao = (ClientDao) DaoFactory.getInstance().getDaoImpl(ClientDao.class);
    private static Client client1;
    private static Client client2;
    private static Client client3;
    private static Client client4;
    private static Client clientTest;

    // Liste pour suivre les clients ajoutés
    private static List<Client> clientAjoutees = new ArrayList<>();

    // Initialisation des clients avant tous les tests
    @BeforeAll
    public static void setUp() {
        DaoFactory.getInstance().setTestConfiguration();  // Configuration pour les tests
        client1 = new Client("Ahmed", "KHM", "JUMET");
        client2 = new Client("Ahmed", "KKK", "JUET");
        client3 = new Client("Ahmed", "ASD", "JUT");
        client4 = new Client("Ahmed", "ASD", "JUT");
        clientTest = new Client("Ah", "AS", "JU");
    }

    // Test de l'ajout d'un client
    @Test
    @Order(1)
    public void testAjouter() {
        boolean result = false;
        result = clientDao.ajouterClient(client1);  // Ajout du client
        assertTrue(result);  // Vérification que l'ajout a réussi
    }

    // Test de la liste des clients
    @Test
    @Order(2)
    public void testLister() {
        clientDao.ajouterClient(client2);  // Ajout du client 2
        clientAjoutees.add(client2);  // Ajout à la liste de suivi

        clientDao.ajouterClient(client3);  // Ajout du client 3
        clientAjoutees.add(client3);  // Ajout à la liste de suivi
        clientAjoutees.add(client1);  // Ajout du client 1 à la liste de suivi

        List<Client> clientsRecuperees = clientDao.listerClients();  // Récupération de la liste des clients

        // Vérification que la liste récupérée contient bien les clients ajoutés
        assertTrue(clientsRecuperees.contains(client1));
        assertTrue(clientsRecuperees.contains(client2));
        assertTrue(clientsRecuperees.contains(client3));
    }

    // Test de la récupération d'un client par ID
    @Test
    @Order(3)
    public void testSimpleGetClient() {
        clientDao.ajouterClient(clientTest);  // Ajout du client de test

        Client recupered = clientDao.getClient(clientTest.getId());  // Récupération du client
        assertNotNull(recupered);  // Vérification que le client est bien récupéré
    }

    // Test de la modification d'un client
    @Test
    @Order(4)
    public void testModifier() {
        clientDao.ajouterClient(client4);  // Ajout du client 4

        client4.setPrenom("TOTO");  // Modification du prénom
        assertTrue(clientDao.modifierClient(client4));  // Vérification que la modification a réussi

        Client clientRecuperees = clientDao.getClient(client4.getId());  // Récupération du client modifié
        assertEquals("TOTO", clientRecuperees.getPrenom());  // Vérification que la modification est correcte
    }

    // Test de la suppression des clients
    @Test
    @Order(5)
    public void testSupp() {
        // Suppression des clients ajoutés et vérification que chaque suppression est réussie
        assertTrue(clientDao.supprimerClient(client1.getId()));
        assertTrue(clientDao.supprimerClient(client2.getId()));
        assertTrue(clientDao.supprimerClient(client3.getId()));
        assertTrue(clientDao.supprimerClient(client4.getId()));
        assertTrue(clientDao.supprimerClient(clientTest.getId()));
    }
}
