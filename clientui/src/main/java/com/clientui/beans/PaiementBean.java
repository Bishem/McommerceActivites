package com.clientui.beans;

public class PaiementBean {

    private Integer id;
    private Integer idCommande;
    private Double montant;
    private Long numeroCarte;

    public PaiementBean() {

    }

    public PaiementBean(Integer idCommande, Double montant, Long numeroCarte) {

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

        return "PaiementBean{" +
                "id=" + id +
                ", idCommande=" + idCommande +
                ", montant=" + montant +
                ", numeroCarte=" + numeroCarte +
                '}';
    }
}
