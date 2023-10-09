package com.example.projets;

import com.mongodb.client.MongoCollection;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.bson.Document;

import java.util.List;

@Named
@RequestScoped
public class DetailHeberg {
    MongoDBConnection connection=new MongoDBConnection();
    public MongoDBConnection getConnection() {
        return connection;
    }

    public void setConnection(MongoDBConnection connection) {
        this.connection = connection;
    }


    List<Document> hebergements;

    public List<Document> getHebergements() {
        return hebergements;
    }

    public void setHebergements(List<Document> hebergements) {
        this.hebergements = hebergements;
    }

    public String detailHebergement(String nom) {
        hebergements=connection.getHeberg(nom);
        return "detail.xhtml";
    }
    private MongoDBConnection mongoDBUtil=new MongoDBConnection();

    public String addReservation() {
        try {
            MongoCollection<Document> collection = mongoDBUtil.getCollection("booking", "reservation");

            Document newReservation = new Document()
                    .append("idHebergement", "6522e721ad5beddfddd99145")
                    .append("idUser", "652363a78a1289100f7ed469");

            collection.insertOne(newReservation);
        } catch (Exception e) {
            e.printStackTrace();
            return "error.xhtml"; // Gérer les erreurs d'enregistrement
        }
        return "home.xhtml";
    }
}
