package com.mpaiement.beans;

import java.util.Date;

public class CommandeBean {

	private Integer id;
	private Integer productId;
	private Date dateCommande;
	private Integer quantite;
	private Boolean commandePayee;

	public CommandeBean() {

	}

	public CommandeBean(Integer productId, Date dateCommande, Integer quantite, Boolean commandePayee) {

		this.productId = productId;
		this.dateCommande = dateCommande;
		this.quantite = quantite;
		this.commandePayee = commandePayee;
	}

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public Integer getProductId() {

		return productId;
	}

	public void setProductId(Integer productId) {

		this.productId = productId;
	}

	public Date getDateCommande() {

		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {

		this.dateCommande = dateCommande;
	}

	public Integer getQuantite() {

		return quantite;
	}

	public void setQuantite(Integer quantite) {

		this.quantite = quantite;
	}

	public Boolean getCommandePayee() {

		return commandePayee;
	}

	public void setCommandePayee(Boolean commandePayee) {

		this.commandePayee = commandePayee;
	}

	@Override
	public String toString() {

		return "CommandeBean{" +
				"id=" + id +
				", productId=" + productId +
				", dateCommande=" + dateCommande +
				", quantite=" + quantite +
				", commandePayee=" + commandePayee +
				'}';
	}
}