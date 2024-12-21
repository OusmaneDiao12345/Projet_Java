package com.ism.repository.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ism.entities.User;
import com.ism.enums.Role;
import com.ism.repository.RepositoryUser;

public class UserRepositoryBD implements RepositoryUser<User>{

    @Override
    public void insert(User user) {
        int nbre=0;
        Connection connect;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect=DriverManager.getConnection("jdbc:mysql://localhost:8889/projet_fil_rouge","root","root");
            java.sql.Statement statement = connect.createStatement();
            nbre =  statement.executeUpdate(String.format("INSERT INTO `User` (`login`, `password`, `role`, `statut`) VALUES ('%s', '%s', '%s', '%s')",user.getLogin(),user.getPassword(),user.getRole(),user.getStatut()));
            System.err.println("Connection BD etablie");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement du driver");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données");
        }
    }

    @Override
    public List<User> selectAll() {
        List<User> comptes=new ArrayList<>();
        Connection connect;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect=DriverManager.getConnection("jdbc:mysql://localhost:8889/projet_fil_rouge","root","root");
            java.sql.Statement statement = connect.createStatement();
            ResultSet rs =  statement.executeQuery("SELECT * from `User`");
            while(rs.next()){
                User user=new User();
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                String typeString = rs.getString("role");
                user.setRole(Role.valueOf(typeString));
                user.setStatut(rs.getString("statut"));
                comptes.add(user);
            }
            System.err.println("Connection BD etablie");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement du driver");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données");
        }
        return comptes;
    }

    @Override
    public List<User> findByRole(Role role) {
        List<User> comptes=new ArrayList<>();
        Connection connect;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect=DriverManager.getConnection("jdbc:mysql://localhost:8889/projet_fil_rouge","root","root");
            java.sql.Statement statement = connect.createStatement();
            ResultSet rs =  statement.executeQuery(String.format("SELECT * FROM `User` WHERE `role` like  '%s'", role));
            if(rs.next()){
                User user=new User();
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                String typeString = rs.getString("role");
                user.setRole(Role.valueOf(typeString));
                user.setStatut(rs.getString("statut"));
                comptes.add(user);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement du driver");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données");
        }
        return comptes;
    }

    @Override
    public User getByLogin(String login) {
        User user=null;
        Connection connect;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect=DriverManager.getConnection("jdbc:mysql://localhost:8889/projet_fil_rouge","root","root");
            java.sql.Statement statement = connect.createStatement();
            ResultSet rs =  statement.executeQuery(String.format("SELECT * FROM `User` WHERE `login` like  '%s'", login));
            if(rs.next()){
                user=new User();
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                String typeString = rs.getString("role");
                user.setRole(Role.valueOf(typeString));
                user.setStatut(rs.getString("statut"));
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement du driver");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données");
        }
        return user;
    }
}
