package com.ism.entities;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Article {
    private String reference;
    private String libelle;
    private int prix;
    private int qteStock;
    private List<Detail> details = new ArrayList<>();
    public Article() {
    }
    
}