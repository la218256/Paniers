package be.helha.poo3.exsp.paniers.daoimpl;

import be.helha.poo3.exsp.paniers.dao.ClientDao;
import be.helha.poo3.exsp.paniers.dao.Dao;
import be.helha.poo3.exsp.paniers.dao.ReservationDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DaoFactory est une classe singleton qui fournit des instances de différents DAOs (Data Access Objects)
 * pour accéder aux données de la base de données. Elle gère également la connexion à la base de données.
 */
public class DaoFactory implements Dao {

    // Chemins vers les fichiers de configuration
    private static final String FICHIER_CONFIGURATION = "src/main/resources/config.json";
    private static final String FICHIER_CONFIGURATION_TESTS = "src/main/resources/configTest.json";

    // Instances des DAOs spécifiques (client, réservation)
    private static ClientDao clientDaoInstance;
    private static ReservationDao reservationDaoInstance;

    // Instance unique de DaoFactory (Singleton)
    private static final DaoFactory INSTANCE = new DaoFactory();

    // Objet de persistance qui contient la configuration et les DAOs
    private Persistance persistance;


    /**
     * Retourne l'unique instance de DaoFactory (Singleton).
     *
     * @return L'instance unique de DaoFactory.
     */
    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    // Constructeur privé pour empêcher l'instantiation externe (Singleton)
    private DaoFactory() {
        try {
            // Charge la configuration à partir du fichier spécifié
            this.persistance = ParserConfig.lireConfiguration(FICHIER_CONFIGURATION);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de charger une configuration spécifique pour les tests.
     */
    public void setTestConfiguration() {
        try {
            // Charge la configuration des tests
            this.persistance = ParserConfig.lireConfiguration(FICHIER_CONFIGURATION_TESTS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne une connexion à la base de données en utilisant l'URL définie dans la configuration.
     *
     * @return Une connexion à la base de données.
     * @throws SQLException Si une erreur survient lors de la connexion.
     */
    public Connection getConnexion() throws SQLException {
        Connection connexion = null;
        try {
            // Crée une connexion à la base de données en utilisant l'URL de persistance
            connexion = DriverManager.getConnection(persistance.getUrl());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connexion;
    }

    /**
     * Méthode générique pour retourner n'importe quel type de DAO implémenté.
     *
     * @param interfaceDao L'interface du DAO demandé.
     * @param <T> Le type de DAO à retourner.
     * @return Une instance du DAO demandé.
     */
    public <T extends Dao> T getDaoImpl(Class<T> interfaceDao) {
        // Vérifie si le DAO demandé est ClientDao et retourne une nouvelle instance si nécessaire
        if (interfaceDao == ClientDao.class) {
            return (T) new ClientDaoImpl();
        }
        // Vérifie si le DAO demandé est ReservationDao et retourne une nouvelle instance si nécessaire
        else if (interfaceDao == ReservationDao.class) {
            return (T) new ReservationDaoImpl();
        }
        // Retourne une instance du DAO en utilisant la persistance
        return (T) this.persistance.getDaoImpl(interfaceDao);
    }
}


