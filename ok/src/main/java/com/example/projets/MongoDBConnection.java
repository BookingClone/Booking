package com.example.projets;

import com.mongodb.client.*;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import org.bson.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean
@RequestScoped

    public class MongoDBConnection implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(Booking.class.getName());

    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoDBConnection() {
        try {
            // Initialiser le client MongoDB et la base de données ici
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            database = mongoClient.getDatabase("booking");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Document> readHebergements() {
        List<Document> hebergements = new ArrayList<>();
        try {
            LOGGER.info("hello");
            // Récupérer la collection "hebergement" depuis la base de données
            MongoCollection<Document> hebergementCollection = database.getCollection("hebergement");

            // Créer un curseur pour parcourir les documents de la collection
            FindIterable<Document> iterDoc = hebergementCollection.find();
            for (Document doc : iterDoc) {
//                Hebergement hebergement = mapDocumentToHebergement(doc);
                hebergements.add(doc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hebergements;
    }

    public List<Document> filtreHebergements(String Destination) {
        List<Document> hebergements = new ArrayList<>();
        try {
            LOGGER.info("hello");
            // Récupérer la collection "hebergement" depuis la base de données
            MongoCollection<Document> hebergementCollection = database.getCollection("hebergement");

            Document query = new Document("lieu", Destination);
            MongoCursor<Document> cursor = hebergementCollection.find(query).iterator();

            while (cursor.hasNext()) {
                Document document = cursor.next();
                hebergements.add(document);
            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hebergements;
    }

    public MongoCollection<Document> getCollection(String databaseName, String collectionName) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        return database.getCollection(collectionName);
    }

    public List<Document> getHeberg(String nom) {
        List<Document> hebergements = new ArrayList<>();
        try {
            LOGGER.info("hello");
            // Récupérer la collection "hebergement" depuis la base de données
            MongoCollection<Document> hebergementCollection = database.getCollection("hebergement");

            Document query = new Document("nom", nom);
            MongoCursor<Document> cursor = hebergementCollection.find(query).iterator();

            while (cursor.hasNext()) {
                Document document = cursor.next();
                hebergements.add(document);
            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hebergements;
    }



}