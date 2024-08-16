package be.helha.poo3.exsp.paniers.daoimpl;

import be.helha.poo3.exsp.paniers.dao.ReservationDao;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class ReservationDaoImpl implements ReservationDao {
    // Requêtes SQL utilisées dans les méthodes
    private static final String GET = "SELECT * FROM reservations WHERE id = ?";
    private static final String AJOUT = "INSERT INTO reservations (date_commande, quantite, prix, client_id) VALUES (?,?,?,?)";
    private static final String MAJ = "UPDATE reservations SET date_commande = ?, quantite = ?, prix = ?, client_id = ? WHERE id = ?";
    private static final String LISTER = "SELECT * FROM reservations ORDER BY reservations.id";
    private static final String SUPPRIMER = "DELETE FROM reservations WHERE id = ?";

    // Instance de la fabrique DAO pour obtenir les connexions et autres DAO
    private DaoFactory daoFactory;

    // Constructeur qui initialise la fabrique DAO
    public ReservationDaoImpl() {
        this.daoFactory = DaoFactory.getInstance();
    }

    // Méthode utilitaire pour fermer les ressources de la base de données
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