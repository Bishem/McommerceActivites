package com.clientui.beans;

public class ExpeditionBean {

    private int id;
    private Integer idCommande;
    private Integer etat;

    public ExpeditionBean() {

    }

    public ExpeditionBean(int idCommande, Integer etat) {

        this.idCommande = idCommande;
        this.etat = etat;
    }

    public ExpeditionBean(int id, Integer idCommande, Integer etat) {

        this.id = id;
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

        return "ExpeditionBean{" +
                "id=" + id +
                ", idCommande=" + idCommande +
                ", etat=" + etat +
                '}';
    }
}
