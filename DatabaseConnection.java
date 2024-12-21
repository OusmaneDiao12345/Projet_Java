package com.ism.core;

import java.sql.*;

public class DatabaseConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/projet_file_rouge";
    private static final String USER = "root"; // Remplacez par votre nom d'utilisateur
    private static final String PASS = ""; // Remplacez par votre mot de passe

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connexion réussie !");
            return conn;
        } catch (SQLException e) {
            System.err.println("Erreur de connexion :");
            e.printStackTrace(); // Importe pour afficher le détail de l'erreur
            return null;
        }
    }

    // ... (votre méthode closeConnection) ...
}