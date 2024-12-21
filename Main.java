package com.ism;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.ism.entities.Article;
import com.ism.entities.Client;
import com.ism.entities.Dette;
import com.ism.entities.User;
import com.ism.enums.Role;
import com.ism.repository.list.ClientRepository; 
import com.ism.repository.bd.UserRepositoryBD;
import com.ism.services.impl.ClientServiceImpl;
import com.ism.services.impl.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        int choix;
        Scanner scanner = new Scanner(System.in);

        // Créer une instance de ClientRepository et des services Client et User
        ClientRepository clientRepository = new ClientRepository();
        ClientServiceImpl clientServiceImpl = new ClientServiceImpl(clientRepository);
        UserRepositoryBD userRepository = new UserRepositoryBD();
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository);

        Client client = null; // Déclarer le client pour éviter des erreurs de portée

        do {
            System.out.println("1 - Créer Client");
            System.out.println("2 - Lister Clients");
            System.out.println("3 - Rechercher Client par Téléphone");
            System.out.println("4 - Créer Utilisateur");
            System.out.println("5 - Lister Utilisateurs");
            System.out.println("6 - Créer une Dette");
            System.out.println("7 - Ajouter un Paiement");
            System.out.println("8 - Lister les Dettes non soldées");
            System.out.println("9 - Filtrer les Dettes par Statut");
            System.out.println("10 - Quitter");

            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la ligne restante après nextInt

            switch (choix) {
                case 1:
                    client = new Client();
                    System.out.println("Entrer le surnom:");
                    client.setSurname(scanner.nextLine());
                    System.out.println("Entrer le Téléphone:");
                    client.setTelephone(scanner.nextLine());
                    System.out.println("Entrer l'adresse:");
                    client.setAdresse(scanner.nextLine());

                    clientServiceImpl.createClient(client.getSurname(), client.getTelephone(), client.getAdresse());
                    System.out.println("Client créé avec succès !");
                    break;

                case 2:
                    List<Client> clients = clientServiceImpl.listClients();
                    if (clients.isEmpty()) {
                        System.out.println("Aucun client trouvé.");
                    } else {
                        clients.forEach(System.out::println);
                    }
                    break;

                case 3:
                    System.out.println("Entrez le numéro de téléphone à rechercher:");
                    String tel = scanner.nextLine();
                    client = clientServiceImpl.findClientByPhone(tel);
                    System.out.println(client != null ? client : "Client non trouvé.");
                    break;

                case 4:
                    User user = new User();
                    System.out.println("Entrer le login:");
                    user.setLogin(scanner.nextLine());
                    System.out.println("Entrer le mot de passe:");
                    user.setPassword(scanner.nextLine());
                    user.setRole(saisieRole(scanner));

                    userServiceImpl.createUser(user.getLogin(), user.getPassword(), user.getRole());
                    System.out.println("Utilisateur créé avec succès !");
                    break;

                case 5:
                    List<User> users = userServiceImpl.show();
                    if (users.isEmpty()) {
                        System.out.println("Aucun utilisateur trouvé.");
                    } else {
                        users.forEach(System.out::println);
                    }
                    break;

                case 6:
                    // Créer une Dette
                    System.out.println("Créer une Dette:");
                    if (client == null) {
                        System.out.println("Aucun client sélectionné. Vous devez d'abord créer un client.");
                        break;
                    }
                    System.out.println("Entrez le montant:");
                    double debtAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consommer la ligne restante

                    List<Article> articles = new ArrayList<>(); // Remplir cette liste d'articles si nécessaire
                    clientServiceImpl.createDette(client, new Date(), debtAmount, articles);
                    System.out.println("Dette créée avec succès.");
                    break;

                case 7:
                    // Ajouter un Paiement
                    System.out.println("Ajouter un Paiement:");
                    if (client == null) {
                        System.out.println("Aucun client sélectionné. Vous devez d'abord créer un client.");
                        break;
                    }
                    System.out.println("Entrez l'ID de la Dette:");
                    Long debtId = scanner.nextLong();
                    scanner.nextLine(); // Consommer la ligne restante

                    Dette debt = clientServiceImpl.getDetteById(client, debtId); // Méthode pour trouver la dette par ID
                    if (debt != null) {
                        System.out.println("Entrez le montant du paiement:");
                        double paymentAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consommer la ligne restante

                        clientServiceImpl.addPayment(debt, new Date(), paymentAmount);
                        System.out.println("Paiement ajouté avec succès.");
                    } else {
                        System.out.println("Dette introuvable.");
                    }
                    break;

                case 8:
                    // Lister les Dettes non soldées
                    if (client == null) {
                        System.out.println("Aucun client sélectionné. Vous devez d'abord créer un client.");
                        break;
                    }
                    List<Dette> nonSettledDebts = clientServiceImpl.getNonSettledDette(client);
                    nonSettledDebts.forEach(System.out::println);
                    break;

                case 9:
                    // Filtrer les Dettes par Statut
                    System.out.println("Filtrer les Dettes par Statut:");
                    System.out.println("Entrer le statut (En Cours, Annulé):");
                    String status = scanner.nextLine();

                    List<Dette> filteredDebts = clientServiceImpl.filterDebtsByStatus(client, status);
                    filteredDebts.forEach(System.out::println);
                    break;

                case 10:
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
                    break;
            }

        } while (choix != 10);

        scanner.close();
    }

    public static Role saisieRole(Scanner scanner) {
        int role;
        do {
            System.out.println("Choisir un rôle:");
            System.out.println("1 - Boutiquier");
            System.out.println("2 - Admin");
            role = scanner.nextInt();
            scanner.nextLine(); // Consommer le saut de ligne
        } while (role < 1 || role > 2);
        return Role.values()[role - 1];
    }
}
