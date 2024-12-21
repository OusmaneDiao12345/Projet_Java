package com.ism.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private double amount;

    @ManyToOne
    @JoinColumn(name = "dette_id")
    private Dette dette;  // Lien vers la dette concernée

    // Constructeur
    public Payment(Date date, double amount) {
        this.date = date;
        this.amount = amount;
    }
}
