package com.example.projets;

import com.mongodb.client.MongoCollection;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.bson.Document;

import java.io.Serializable;

@Named("userController")
@SessionScoped
public class UserController implements Serializable {

    private MongoDBConnection mongoDBUtil=new MongoDBConnection();

    private User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String register() {
        try {
            MongoCollection<Document> collection = mongoDBUtil.getCollection("booking", "users");

            Document newUser = new Document()
                    .append("firstName", user.getFirstName())
                    .append("lastName", user.getLastName())
                    .append("email", user.getEmail())
                    .append("password", user.getPassword())
                    .append("city", user.getCity()) // Ville
                    .append("phoneNumber", user.getPhoneNumber()) // Numéro de téléphone
                    .append("nationality", user.getNationality()) // Nationalité
                    .append("gender", user.getGender()); // Genre

                     collection.insertOne(newUser);
        } catch (Exception e) {
            e.printStackTrace();
            return "error.xhtml"; // Gérer les erreurs d'enregistrement
        }

        return "login.xhtml"; // Rediriger vers une page de succès
    }
}
