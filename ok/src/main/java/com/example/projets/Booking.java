package com.example.projets;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Named;
import org.bson.Document;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class Booking  implements Serializable {
    MongoDBConnection connection=new MongoDBConnection();
    public MongoDBConnection getConnection() {
        return connection;
    }

    public void setConnection(MongoDBConnection connection) {
        this.connection = connection;
    }


    List<Document> hebergements=connection.readHebergements();

    public List<Document> getHebergements() {
        return hebergements;
    }

    public void setHebergements(List<Document> hebergements) {
        this.hebergements = hebergements;
    }

   private String Destination ;

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String filtrage(){
        hebergements=connection.filtreHebergements(Destination);
        return "collections";
    }



}
