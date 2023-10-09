package com.example.projets;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import org.bson.Document;

import java.io.Serializable;

@ManagedBean(name = "loginBean", eager = true)
@SessionScoped
public class LoginBean implements Serializable {


    private User user = new User();
    private boolean loggedIn;



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String login() {
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient("localhost", 27017); // Adresse et port de votre serveur MongoDB
            MongoDatabase database = mongoClient.getDatabase("booking"); // Remplacez par le nom de votre base de données
            MongoCollection<Document> usersCollection = database.getCollection("users"); // Remplacez par le nom de votre collection d'utilisateurs

            Document query = new Document("email", getUser().getEmail()).append("password", getUser().getPassword());
            Document userDocument = usersCollection.find(query).first();
            if (usersCollection.countDocuments(query) == 1) {
                user = new User();
                user.setEmail(userDocument.getString("email"));
                user.setFirstName(userDocument.getString("firstName"));
                user.setLastName(userDocument.getString("lastName"));
                loggedIn = true;
                return "collections";
            } else {
                loggedIn = false;
                return "login";
            }
        } finally {
            if (mongoClient != null) {
                mongoClient.close();
            }
        }
    }
}
