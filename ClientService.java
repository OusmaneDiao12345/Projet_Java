package com.ism.services;

import java.util.Date;
import java.util.List;

import com.ism.entities.Article;
import com.ism.entities.Client;
import com.ism.entities.Dette;
import com.ism.entities.Payment;
import com.ism.repository.list.ClientRepository;

public class ClientService {
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Créer un nouveau client
    public void createClient(String surname, String telephone, String adresse) {
        Client client = new Client();
        client.setSurname(surname);
        client.setTelephone(telephone);
        client.setAdresse(adresse);
        clientRepository.addClient(client); // Ajoute le client au dépôt
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

    // Créer une dette pour un client
    public void createDette(Client client, Date date, double amount, List<Article> articles) {
        Dette dette = new Dette(date, amount, articles); // Crée une nouvelle dette avec les paramètres fournis
        client.addDebt(dette); // Ajoute cette dette à la liste de dettes du client
    }

    // Enregistrer un paiement pour une dette
    public void addPayment(Dette debt, Date date, double amount) {
        Payment payment = new Payment(date, amount); // Crée un paiement
        debt.addPayment(payment); // Ajoute le paiement à la liste des paiements de la dette
        debt.setAmountPaid(debt.getAmountPaid() + amount); // Met à jour le montant payé
        debt.setRemainingAmount(debt.getAmount() - debt.getAmountPaid()); // Met à jour le montant restant
    }

    // Lister les dettes non soldées (montant restant > 0)
    public List<Dette> getNonSettledDebts(Client client) {
        return client.getDette().stream()  // Utilisez getDettes() pour récupérer la liste des dettes du client
                .filter(debt -> debt.getRemainingAmount() > 0) // Filtre les dettes avec un montant restant
                .toList();
    }

    // Filtrer les dettes par statut
    public List<Dette> filterDebtsByStatus(Client client, String status) {
        // En supposant que "status" est un attribut de Dette, comme "En Cours" ou "Annulé"
        return client.getDette().stream()  // Utilisez getDettes() pour récupérer la liste des dettes du client
                .filter(dette -> dette.getStatus().equalsIgnoreCase(status)) // Filtre les dettes par statut
                .toList();
    }
}
