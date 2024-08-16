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

    /**
     * Retourne une implémentation de DAO en fonction de l'interface DAO passée en paramètre.
     *
     * @param interfaceDao L'interface DAO pour laquelle une implémentation est requise.
     * @return Une instance de l'implémentation du DAO, ou null si aucune implémentation n'est trouvée.
     */
    public Dao getDaoImpl(Class<? extends Dao> interfaceDao) {
        Dao dao = null;
        Class<Dao> classeDaoImpl;
        Class<?>[] interfaces;

        try {
            // Parcourt les noms des DAO disponibles
            for (String nomDaoImpl : daos) {
                // Charge la classe DAO correspondante à partir de son nom
                classeDaoImpl = (Class<Dao>) Class.forName(nomDaoImpl);
                interfaces = (Class<?>[]) classeDaoImpl.getInterfaces();

                // Vérifie si la classe implémente l'interface demandée
                for (Class<?> i : interfaces) {
                    if (i.getName().equals(interfaceDao.getName())) {
                        // Crée une instance de l'implémentation du DAO
                        dao = (Dao) classeDaoImpl.getDeclaredConstructor().newInstance();
                        return dao;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retourne l'URL de la base de données ou de la source de persistance.
     *
     * @return L'URL de la source de persistance.
     */
    public String getUrl() {
        return url;
    }

}