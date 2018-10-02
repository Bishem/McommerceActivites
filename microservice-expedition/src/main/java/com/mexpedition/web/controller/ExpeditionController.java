package com.mexpedition.web.controller;


import com.mexpedition.dao.ExpeditionDao;
import com.mexpedition.model.Expedition;
import com.mexpedition.web.exceptions.ExpeditionNotFoundException;
import com.mexpedition.web.exceptions.ImpossibleAjouterExpeditionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ExpeditionController {

    @Autowired
    ExpeditionDao expeditionDao;

	@PostMapping (value = "/expedition")
	public ResponseEntity<Expedition> ajouterExpedition(@RequestBody Expedition expedition){

		Expedition nouvelleExpedition = expeditionDao.save(expedition);

		if(nouvelleExpedition == null) throw new ImpossibleAjouterExpeditionException("Impossible d'ajouter cette commande");

		return new ResponseEntity<Expedition>(expedition, HttpStatus.CREATED);
	}

    @GetMapping(value = "/expedition/{idCommande}")
    public Optional<Expedition> etatExpedition(@PathVariable int idCommande){

        Optional<Expedition> expedition = expeditionDao.findByIdCommande(idCommande);

        if(!expedition.isPresent()) throw new ExpeditionNotFoundException("Cette expedition n'existe pas");

        return expedition;
    }

	@PutMapping(value = "/expedition")
	public void updateExpedition(@RequestBody Expedition expedition) {

		expeditionDao.save(expedition);
	}
}
