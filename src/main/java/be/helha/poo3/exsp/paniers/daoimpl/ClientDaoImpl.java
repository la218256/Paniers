package be.helha.poo3.exsp.paniers.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    // Méthode utilitaire pour fermer les ressources JDBC (ResultSet, PreparedStatement, Connection)
    private void cloturer(ResultSet rs, PreparedStatement ps, Connection con) {
        try {
            if (rs != null)
                rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (ps != null)
                ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (con != null)
                con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    }


