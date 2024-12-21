package com.ism.entities;
import java.util.ArrayList;
import java.util.List;

import com.ism.enums.Role;

import lombok.Data;
@Data
public class User {
    private String login;
    private String password;
    private Client client;
    private Role role;
    private String statut;
    private List<Dette> dettes = new ArrayList<>();

    
}
