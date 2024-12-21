package com.ism.repository.list;

import com.ism.entities.Client;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {
    private List<Client> clients = new ArrayList<>();

    // Méthode pour obtenir les clients avec un compte
    public List<Client> getClientsWithAccounts() {
        List<Client> clientsWithAccounts = new ArrayList<>();
        for (Client client : clients) {
            if (client.hasAccount()) { 
                clientsWithAccounts.add(client);
            }
        }
        return clientsWithAccounts;
    }

    // Méthode pour obtenir les clients sans compte
    public List<Client> getClientsWithoutAccounts() {
        List<Client> clientsWithoutAccounts = new ArrayList<>();
        for (Client client : clients) {
            if (!client.hasAccount()) { // Filtre pour les clients sans compte
                clientsWithoutAccounts.add(client);
            }
        }
        return clientsWithoutAccounts;
    }

    // Méthode pour ajouter un client
    public void addClient(Client client) {
        clients.add(client);
    }

    // Méthode pour obtenir tous les clients
    public List<Client> getAllClients() {
        return clients;
    }
}
