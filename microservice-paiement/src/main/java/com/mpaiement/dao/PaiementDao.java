package com.mpaiement.dao;

import com.mpaiement.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaiementDao extends JpaRepository<Paiement, Integer> {

    Optional<Paiement> findByidCommande(int idCommande);
}
