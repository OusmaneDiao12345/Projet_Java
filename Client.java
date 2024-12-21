package com.ism.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String surname;
    private String telephone;
    private String adresse;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Dette> dette = new ArrayList<>();  // Liste des dettes du client
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<User> comptes = new ArrayList<>();  // Liste des comptes utilisateur du client

    // Méthode pour ajouter une dette à la liste des dettes du client
    public void addDebt(Dette debt) {
        this.dette.add(debt);  // Ajoute la dette à la liste
    }

    // Méthode pour vérifier si le client a au moins un compte
    public boolean hasAccount() {
        return !comptes.isEmpty();
    }
}
