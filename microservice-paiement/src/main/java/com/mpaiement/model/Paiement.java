package com.mpaiement.model;

import javax.persistence.*;

@Entity
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private Integer idCommande;
    private Double montant;
    private Long numeroCarte;

    public Paiement() {

    }

    public Paiement(Integer idCommande, Double montant, Long numeroCarte) {

        this.idCommande = idCommande;
        this.montant = montant;
        this.numeroCarte = numeroCarte;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public Integer getIdCommande() {

        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {

        this.idCommande = idCommande;
    }

    public Double getMontant() {

        return montant;
    }

    public void setMontant(Double montant) {

        this.montant = montant;
    }

    public Long getNumeroCarte() {

        return numeroCarte;
    }

    public void setNumeroCarte(Long numeroCarte) {

        this.numeroCarte = numeroCarte;
    }

    @Override
    public String toString() {

        return "Paiement{" +
                "id=" + id +
                ", idCommande=" + idCommande +
                ", montant=" + montant +
                ", numeroCarte=" + numeroCarte +
                '}';
    }
}
