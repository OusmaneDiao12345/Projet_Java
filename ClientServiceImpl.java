package com.ism.services.impl;

import java.util.Date;
import java.util.List;

import com.ism.entities.Article;
import com.ism.entities.Client;
import com.ism.entities.Dette;
import com.ism.entities.Payment;
import com.ism.repository.list.ClientRepository;

public class ClientServiceImpl {
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Créer un nouveau client
    public void createClient(String surname, String telephone, String adresse) {
        Client client = new Client();
        client.setSurname(surname);
        client.setTelephone(telephone);
        client.setAdresse(adresse);
        clientRepository.addClient(client);  // Enregistrement du client
    }

    // Lister tous les clients
    public List<Client> listClients() {
        return clientRepository.getAllClients();
    }

    // Lister les clients ayant des comptes
    public List<Client> listClientsWithAccounts() {
        return clientRepository.getClientsWithAccounts();
    }

    // Lister les clients sans comptes
    public List<Client> listClientsWithoutAccounts() {
        return clientRepository.getClientsWithoutAccounts();
    }

    // Rechercher un client par téléphone
    public Client findClientByPhone(String telephone) {
        return clientRepository.getAllClients().stream()
                .filter(client -> client.getTelephone().equals(telephone))
                .findFirst()
                .orElse(null);
    }

    // Afficher tous les clients
    public List<Client> show() {
        List<Client> clients = clientRepository.getAllClients();
        if (clients.isEmpty()) {
            System.out.println("Aucun client trouvé.");
        } else {
            for (Client client : clients) {
                System.out.println(client);
            }
        }
        return clients;
    }

    // Créer une dette pour un client
    public void createDette(Client client, Date date, double debtAmount, List<Article> articles) {
        Dette debt = new Dette(date, debtAmount, articles);  // Crée une dette avec les articles associés
        client.addDebt(debt);  // Ajoute la dette à la liste de dettes du client
        clientRepository.addClient(client);  // Enregistrer le client avec la nouvelle dette
    }

    // Lister les dettes non soldées (montant restant > 0)
    public List<Dette> getNonSettledDette(Client client) {
        return client.getDette().stream()
                .filter(dette -> dette.getRemainingAmount() > 0)  // Filtre les dettes non réglées
                .toList();
    }
    

    // Ajouter un paiement à une dette
    public void addPayment(Dette debt, Date date, double paymentAmount) {
        Payment payment = new Payment(date, paymentAmount);  // Crée un paiement
        debt.addPayment(payment);  // Ajoute le paiement à la dette
        debt.setAmountPaid(debt.getAmountPaid() + paymentAmount);  // Met à jour le montant payé
        debt.setRemainingAmount(debt.getAmount() - debt.getAmountPaid());  // Met à jour le montant restant
    }

    // Filtrer les dettes par statut
    public List<Dette> filterDebtsByStatus(Client client, String status) {
        return client.getDette().stream()
                .filter(dette -> dette.getStatus().equalsIgnoreCase(status))  // Filtre par statut
                .toList();
    }

    public Dette getDetteById(Client client, Long debtId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDetteById'");
    }
}
