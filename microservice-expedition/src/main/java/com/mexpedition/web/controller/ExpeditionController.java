package com.mexpedition.web.controller;


import com.mexpedition.dao.ExpeditionDao;
import com.mexpedition.model.Expedition;
import com.mexpedition.web.exceptions.ExpeditionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ExpeditionController {

    @Autowired
    ExpeditionDao expeditionDao;

    @PostMapping(value = "/expedition")
    public ResponseEntity<Expedition> ajouterExpedition(@RequestBody Expedition expedition) {

        Expedition nouvelleExpedition = expeditionDao.save(expedition);

        return new ResponseEntity<>(nouvelleExpedition, HttpStatus.CREATED);
    }

    @GetMapping(value = "/expedition/{idCommande}")
    public Optional<Expedition> etatExpedition(@PathVariable int idCommande) {

        Optional<Expedition> expedition = expeditionDao.findByIdCommande(idCommande);

        if (!expedition.isPresent()) {
            throw new ExpeditionNotFoundException("Cette expedition n'existe pas");
        } else {
            return expedition;
        }
    }

    @PutMapping(value = "/expedition")
    public ResponseEntity<Expedition> updateExpedition(@RequestBody Expedition expedition) {

        Expedition expeditionMModifiee = expeditionDao.save(expedition);

        return new ResponseEntity<>(expeditionMModifiee, HttpStatus.CREATED);
    }
}
