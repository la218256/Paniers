package be.helha.poo3.exsp.paniers.daoimpl;

import be.helha.poo3.exsp.paniers.dao.ClientDao;
import be.helha.poo3.exsp.paniers.dao.ReservationDao;
import be.helha.poo3.exsp.paniers.modeles.Client;
import be.helha.poo3.exsp.paniers.modeles.Reservation;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Repository;


import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    // Méthode pour lire le prix du panier à partir d'un fichier JSON
    private int lirePrixPanierDepuisJson() {
        int prixPanier = 0;
        try (FileReader reader = new FileReader("src/main/resources/prix.json", StandardCharsets.UTF_8)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            prixPanier = jsonObject.get("prix_panier").getAsInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prixPanier;
    }

    // Ajoute une nouvelle réservation dans la base de données
    @Override
    public boolean ajouterReservation(Reservation reservation) {
        boolean ajoutReussi = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // Calcul du prix total de la réservation
            int prixPanier = lirePrixPanierDepuisJson();
            int prixTotal = reservation.getQuantite() * prixPanier;

            // Insertion de la réservation dans la base de données
            con = this.daoFactory.getConnexion();
            ps = con.prepareStatement(AJOUT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, reservation.getDateCommande().toString());
            ps.setInt(2, reservation.getQuantite());
            ps.setInt(3, prixTotal);
            ps.setInt(4, reservation.getClient().getId());

            int resultat = ps.executeUpdate();
            if (resultat == 1) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    reservation.setId(rs.getInt(1)); // Récupère l'ID généré
                    ajoutReussi = true;
                }
            } else {
                System.out.println("L'ajout a échoué, resultat = " + resultat);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            cloturer(rs, ps, con);
        }
        return ajoutReussi;
    }

    // Récupère une réservation spécifique par son ID
    @Override
    public Reservation getReservation(int id) {
        Reservation reservation = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = this.daoFactory.getConnexion();
            ps = con.prepareStatement(GET);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                // Récupération du client associé à la réservation
                Client client = daoFactory.getDaoImpl(ClientDao.class).getClient(rs.getInt("client_id"));

                // Conversion de la date de commande en LocalDate
                String dateStr = rs.getString("date_commande");
                LocalDate dateCommande = LocalDate.parse(dateStr);

                // Création de l'objet Reservation
                reservation = new Reservation(
                        dateCommande,
                        rs.getInt("quantite"),
                        rs.getInt("prix"),
                        client
                );
                reservation.setId(rs.getInt("id"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            cloturer(rs, ps, con);
        }
        return reservation;
    }

    // Récupère la liste de toutes les réservations
    @Override
    public List<Reservation> listerReservation() {
        List<Reservation> liste = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = this.daoFactory.getConnexion();
            ps = con.prepareStatement(LISTER);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");

                // Conversion de la date de commande en LocalDate
                String dateStr = rs.getString("date_commande");
                LocalDate dateCommande = LocalDate.parse(dateStr);

                int quantite = rs.getInt("quantite");
                int prix = rs.getInt("prix");

                // Récupération du client associé à la réservation
                int clientId = rs.getInt("client_id");
                Client client = daoFactory.getDaoImpl(ClientDao.class).getClient(clientId);

                // Création de l'objet Reservation
                Reservation reservation = new Reservation(dateCommande, quantite, prix, client);
                reservation.setId(id);
                liste.add(reservation);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            cloturer(rs, ps, con);
        }
        return liste;
    }

    // Supprime une réservation par son ID
    @Override
    public boolean supprimerReservation(int id) {
        boolean suppressionReussie = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = this.daoFactory.getConnexion();
            ps = con.prepareStatement(SUPPRIMER);
            ps.setInt(1, id);
            int nb = ps.executeUpdate();
            if (nb == 1)
                suppressionReussie = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            cloturer(null, ps, con);
        }
        return suppressionReussie;
    }

    // Modifie une réservation existante
    @Override
    public boolean modifierReservation(Reservation reservation) {
        boolean modificationReussie = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            // Calcul du nouveau prix total en fonction de la quantité et du prix unitaire
            int prixPanier = lirePrixPanierDepuisJson();
            int prixTotal = reservation.getQuantite() * prixPanier;

            con = this.daoFactory.getConnexion();
            ps = con.prepareStatement(MAJ);
            ps.setString(1, reservation.getDateCommande().toString());
            ps.setInt(2, reservation.getQuantite());
            ps.setInt(3, prixTotal);
            ps.setInt(4, reservation.getClient().getId());
            ps.setInt(5, reservation.getId());
            int resultat = ps.executeUpdate();
            if (resultat == 1) {
                modificationReussie = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            cloturer(null, ps, con);
        }
        return modificationReussie;
    }
}