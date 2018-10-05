package com.mcommandes.web.controller;


import com.mcommandes.beans.ExpeditionBean;
import com.mcommandes.dao.CommandesDao;
import com.mcommandes.model.Commande;
import com.mcommandes.proxies.MicroserviceExpeditionProxy;
import com.mcommandes.web.exceptions.CommandeAjoutImpossibleException;
import com.mcommandes.web.exceptions.CommandeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CommandeController {

    @Autowired
    CommandesDao commandesDao;

    @Autowired
    MicroserviceExpeditionProxy microserviceExpeditionProxy;

    @GetMapping(value = "/commandes/{id}")
    public Optional<Commande> recupererUneCommande(@PathVariable int id) {

        Optional<Commande> commande = commandesDao.findById(id);

        if (!commande.isPresent()) {
            throw new CommandeNotFoundException("Cette commande n'existe pas");
        } else {
            return commande;
        }
    }

    @PostMapping(value = "/commandes")
    public ResponseEntity<Commande> ajouterCommande(@RequestBody Commande commande) {

        Commande nouvelleCommande = commandesDao.save(commande);

        return new ResponseEntity<>(commande, HttpStatus.CREATED);
    }

    @PutMapping(value = "/commandes")
    public ResponseEntity<Commande> updateCommande(@RequestBody Commande commande) {

        Commande commandeModifiee = commandesDao.save(commande);
        Optional<ExpeditionBean> expedition = microserviceExpeditionProxy.etatExpedition(commandeModifiee.getId());

        if (!expedition.isPresent()) {
            throw new CommandeAjoutImpossibleException("Impossible de mettre a jour cette commande");
        } else {

            expedition.get().setEtat(1);
            ResponseEntity<ExpeditionBean> expeditionModifiee = this.microserviceExpeditionProxy.updateExpedition(expedition.get());

            if (!expeditionModifiee.getStatusCode().is2xxSuccessful()) {
                throw new CommandeAjoutImpossibleException("Cette Commande n'as pas pu etre expediee");
            } else {
                return new ResponseEntity<>(commandeModifiee, HttpStatus.CREATED);
            }
        }
    }
}
