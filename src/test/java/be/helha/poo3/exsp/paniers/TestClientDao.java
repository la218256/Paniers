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

    // Déclaration d'un DAO Client pour interagir avec la base de données
    private static ClientDao clientDao = (ClientDao) DaoFactory.getInstance().getDaoImpl(ClientDao.class);

    // Déclaration de plusieurs objets Client pour les tests
    private static Client client1;
    private static Client client2;
    private static Client client3;
    private static Client client4;
    private static Client clientTest;

    // Liste pour stocker les clients ajoutés au cours des tests
    private static List<Client> clientAjoutees = new ArrayList<>();

    // Méthode exécutée une seule fois avant tous les tests pour initialiser les objets nécessaires
    @BeforeAll
    public static void setUp() {
        DaoFactory.getInstance().setTestConfiguration();  // Configuration de la DAO en mode test

        // Initialisation des objets Client avec des données de test
        client1 = new Client("Ahmed", "KHM", "JUMET");
        client2 = new Client("Ahmed", "KKK", "JUET");
        client3 = new Client("Ahmed", "ASD", "JUT");
        client4 = new Client("Ahmed", "ASD", "JUT");
        clientTest = new Client("Ah", "AS", "JU");
    }

    // Test pour vérifier l'ajout d'un client dans la base de données
    @Test
    @Order(1)  // Indique que ce test doit être exécuté en premier
    public void testAjouter() {
        boolean result = false;
        result = clientDao.ajouterClient(client1);  // Ajout du client1 à la base de données
        assertTrue(result);  // Vérifie que l'ajout a réussi
    }

    // Test pour vérifier que la liste des clients contient bien les clients ajoutés
    @Test
    @Order(2)  // Ce test est exécuté en deuxième
    public void testLister() {
        clientDao.ajouterClient(client2);  // Ajout du client2
        clientAjoutees.add(client2);  // Ajout du client2 à la liste de suivi

        clientDao.ajouterClient(client3);  // Ajout du client3
        clientAjoutees.add(client3);  // Ajout du client3 à la liste de suivi
        clientAjoutees.add(client1);  // Ajout du client1 à la liste de suivi

        List<Client> clientsRecuperees = clientDao.listerClients();  // Récupère tous les clients de la base de données

        // Vérifie que les clients ajoutés sont bien présents dans la liste récupérée
        assertTrue(clientsRecuperees.contains(client1));
        assertTrue(clientsRecuperees.contains(client2));
        assertTrue(clientsRecuperees.contains(client3));
    }

    // Test pour vérifier la récupération d'un client spécifique par son ID
    @Test
    @Order(3)  // Ce test est exécuté en troisième
    public void testSimpleGetClient() {
        clientDao.ajouterClient(clientTest);  // Ajout d'un client de test

        Client recupered = clientDao.getClient(clientTest.getId());  // Récupération du client par son ID
        assertNotNull(recupered);  // Vérifie que le client récupéré n'est pas null
    }

    // Test pour vérifier la modification des informations d'un client
    @Test
    @Order(4)  // Ce test est exécuté en quatrième
    public void testModifier() {
        clientDao.ajouterClient(client4);  // Ajout du client4

        client4.setPrenom("TOTO");  // Modification du prénom du client
        assertTrue(clientDao.modifierClient(client4));  // Vérifie que la modification a réussi

        Client clientRecuperees = clientDao.getClient(client4.getId());  // Récupère le client modifié
        assertEquals("TOTO", clientRecuperees.getPrenom());  // Vérifie que le prénom a bien été modifié
    }

    // Test pour vérifier la suppression des clients ajoutés
    @Test
    @Order(5)  // Ce test est exécuté en dernier
    public void testSupp() {
        // Suppression des clients un par un et vérification que chaque suppression a réussi
        assertTrue(clientDao.supprimerClient(client1.getId()));
        assertTrue(clientDao.supprimerClient(client2.getId()));
        assertTrue(clientDao.supprimerClient(client3.getId()));
        assertTrue(clientDao.supprimerClient(client4.getId()));
        assertTrue(clientDao.supprimerClient(clientTest.getId()));
    }
}
