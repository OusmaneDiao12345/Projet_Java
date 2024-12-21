package com.ism.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "dette")
public class Dette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private double amount;  
    private double amountPaid = 0.0;  
    private double remainingAmount;  
    private String status;  

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;  

    @OneToMany(mappedBy = "dette", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();  

    @ManyToMany
    @JoinTable(
        name = "dette_article",
        joinColumns = @JoinColumn(name = "dette_id"),
        inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Article> articles = new ArrayList<>();  

    
    public Dette(Date date, double amount, List<Article> articles) {
        this.date = date;
        this.amount = amount;
        this.remainingAmount = amount;
        this.articles = articles;
        this.status = "En Cours";  
    }

    
    public void addPayment(Payment payment) {
        this.payments.add(payment);
    }
}
