package be.helha.poo3.exsp.paniers.Controllers;

import be.helha.poo3.exsp.paniers.dao.ClientDao;
import be.helha.poo3.exsp.paniers.modeles.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    // Injection de dépendance pour le DAO des clients
    @Autowired
    private ClientDao clientDao;

    // Méthode pour récupérer tous les clients
    @GetMapping
    public List<Client> getAllClients() {
        return clientDao.listerClients();
    }

    // Méthode pour récupérer un client spécifique par son ID
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable int id) {
        return clientDao.getClient(id);
    }

    // Méthode pour ajouter un nouveau client
    @PostMapping
    public boolean addClient(@RequestBody Client client) {
        return clientDao.ajouterClient(client);
    }

    // Méthode pour mettre à jour un client existant
    @PutMapping("/{id}")
    public boolean updateClient(@PathVariable int id, @RequestBody Client client) {
        client.setId(id);  // S'assurer que l'ID du client est bien celui fourni dans l'URL
        return clientDao.modifierClient(client);
    }

    // Méthode pour supprimer un client par son ID
    @DeleteMapping("/{id}")
    public boolean deleteClient(@PathVariable int id) {
        return clientDao.supprimerClient(id);
    }
}