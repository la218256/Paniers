package be.helha.poo3.exsp.paniers.daoimpl;

public class ClientDaoImpl {

        // Requêtes SQL utilisées dans les méthodes
        private static final String GET = "SELECT * FROM clients WHERE id = ?";
        private static final String AJOUT = "INSERT INTO clients (prenom, nom, commune) VALUES (?,?,?)";
        private static final String MAJ = "UPDATE clients SET prenom = ?, nom = ?, commune = ? WHERE id = ?";
        private static final String LISTER = "SELECT * FROM clients ORDER BY clients.id";
        private static final String SUPPRIMER = "DELETE FROM clients WHERE id = ?";

        private DaoFactory daoFactory;

        // Constructeur de la classe qui initialise la daoFactory
        public ClientDaoImpl() {
            this.daoFactory = DaoFactory.getInstance();
        }
    }


