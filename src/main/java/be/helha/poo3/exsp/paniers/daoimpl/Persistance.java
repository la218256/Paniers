package be.helha.poo3.exsp.paniers.daoimpl;
import be.helha.poo3.exsp.paniers.dao.Dao;

import java.util.HashSet;
import java.util.Set;

/**
 * La classe Persistance implémente l'interface Dao et gère la création et la
 * récupération des implémentations DAO dynamiquement à partir d'une collection
 * de classes DAO disponibles.
 */
public class Persistance implements Dao {

    // Un ensemble pour stocker les noms des classes DAO implémentées
    private Set<String> daos = new HashSet<>();

    // L'URL de la base de données ou une autre source de persistance
    private String url;

    // Constructeur par défaut
    public Persistance() {
        super();
    }

}