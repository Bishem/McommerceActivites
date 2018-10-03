package com.mproduits.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titre;
	private String description;
	private String image;
	private Double prix;

	public Product() {

	}

	public Product(String titre, String description, String image, Double prix) {

		this.titre = titre;
		this.description = description;
		this.image = image;
		this.prix = prix;
	}

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public String getTitre() {

		return titre;
	}

	public void setTitre(String titre) {

		this.titre = titre;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public String getImage() {

		return image;
	}

	public void setImage(String image) {

		this.image = image;
	}

	public Double getPrix() {

		return prix;
	}

	public void setPrix(Double prix) {

		this.prix = prix;
	}

	@Override
	public String toString() {

		return "Product{" +
				"id=" + id +
				", titre='" + titre + '\'' +
				", description='" + description + '\'' +
				", image='" + image + '\'' +
				", prix=" + prix +
				'}';
	}
}
