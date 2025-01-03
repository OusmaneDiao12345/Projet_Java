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
    private List<Dette> dette = new ArrayList<>(); 
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<User> comptes = new ArrayList<>();  


    public void addDebt(Dette debt) {
        this.dette.add(debt);  
    }

   
    public boolean hasAccount() {
        return !comptes.isEmpty();
    }
}
