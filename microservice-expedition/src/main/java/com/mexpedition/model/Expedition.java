package com.mexpedition.model;

import javax.persistence.*;

@Entity
public class Expedition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private Integer idCommande;
    private Integer etat;

    public Expedition() {

    }

    public Expedition(int idCommande, Integer etat) {

        this.idCommande = idCommande;
        this.etat = etat;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public Integer getIdCommande() {

        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {

        this.idCommande = idCommande;
    }

    public Integer getEtat() {

        return etat;
    }

    public void setEtat(Integer etat) {

        this.etat = etat;
    }

    @Override
    public String toString() {

        return "Expedition{" +
                "id=" + id +
                ", idCommande=" + idCommande +
                ", etat=" + etat +
                '}';
    }
}
