package com.ism.repository.bd;

import com.ism.entities.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ClientRepositoryJPA {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("projet_fil_rouge_PU");

    public void insert(Client client) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
        em.close();
    }

    public List<Client> selectAll() {
        EntityManager em = emf.createEntityManager();
        List<Client> clients = em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
        em.close();
        return clients;
    }
}
