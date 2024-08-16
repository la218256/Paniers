package be.helha.poo3.exsp.paniers.daoimpl;



import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.nio.charset.StandardCharsets;

/**
 * La classe ParserConfig est responsable de la lecture et de la validation
 * des configurations de persistance à partir d'un fichier JSON.
 */
public class ParserConfig {

    /**
     * Lit le fichier de configuration spécifié et renvoie une instance de la classe Persistance.
     *
     * @param fichierConfiguration Le chemin vers le fichier de configuration JSON.
     * @return Une instance de la classe Persistance contenant les configurations.
     * @throws Exception Si la validation échoue ou si une erreur survient lors de la lecture du fichier.
     */
    public static Persistance lireConfiguration(String fichierConfiguration) throws Exception {
        // Crée un FileReader pour lire le fichier avec encodage UTF-8
        FileReader fr = new FileReader(fichierConfiguration, StandardCharsets.UTF_8);
        JsonReader reader = new JsonReader(fr);

        // Utilise Gson pour désérialiser le JSON en une instance de Persistance
        Gson gson = new Gson();
        Persistance persistance = gson.fromJson(reader, Persistance.class);

        // Ferme les ressources utilisées pour lire le fichier
        reader.close();
        fr.close();

        // Valide la configuration lue
        validation(persistance);

        // Retourne l'objet Persistance
        return persistance;
    }}
