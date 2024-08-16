package be.helha.poo3.exsp.paniers.daoimpl;

import be.helha.poo3.exsp.paniers.dao.ClientDao;
import be.helha.poo3.exsp.paniers.dao.Dao;
import be.helha.poo3.exsp.paniers.dao.ReservationDao;

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
}


