package be.helha.poo3.exsp.paniers.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import
import be.helha.poo3.exsp.paniers.modeles.Client;

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

    // Méthode pour ajouter un nouveau client dans la base de données
    @Override
    public boolean ajouterClient(Client client) {
        boolean ajoutReussi = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // Obtention de la connexion à la base de données
            con = this.daoFactory.getConnexion();
            // Préparation de la requête SQL d'insertion
            ps = con.prepareStatement(AJOUT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, client.getPrenom());
            ps.setString(2, client.getNom());
            ps.setString(3, client.getCommune());
            // Exécution de la requête
            int resultat = ps.executeUpdate();
            if (resultat == 1) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    client.setId(rs.getInt(1));
                    ajoutReussi = true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Fermeture des ressources
            cloturer(rs, ps, con);
        }
        return ajoutReussi;
    }
    }


