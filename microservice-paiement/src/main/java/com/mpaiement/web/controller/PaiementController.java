package com.mpaiement.web.controller;

import com.mpaiement.beans.CommandeBean;
import com.mpaiement.dao.PaiementDao;
import com.mpaiement.model.Paiement;
import com.mpaiement.proxies.MicroserviceCommandeProxy;
import com.mpaiement.web.exceptions.PaiementExistantException;
import com.mpaiement.web.exceptions.PaiementImpossibleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PaiementController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PaiementDao paiementDao;

	@Autowired
	MicroserviceCommandeProxy microserviceCommandeProxy;

	@PostMapping(value = "/paiement")
	public ResponseEntity<Paiement> payerUneCommande(@RequestBody Paiement paiement) {

		log.info("----------- into paiement");

		testerExistancePaiement(paiement);

		log.info("----------- test OK");

		Paiement nouveauPaiement = paiementDao.save(paiement);

		log.info("----------- paiement saved");

		modifierEtatCommande(paiement);

		log.info("----------- commande updated");

		return new ResponseEntity<>(nouveauPaiement, HttpStatus.CREATED);
	}

	private void testerExistancePaiement(@RequestBody Paiement paiement) {

		log.info("----------- into test");

		Optional<Paiement> paiementExistant = paiementDao.findByidCommande(paiement.getIdCommande());

		log.info("----------- checked db");

		if (paiementExistant.isPresent()) {
			throw new PaiementExistantException("Cette commande est déjà payée");
		}

		log.info("----------- done testing");
	}

	private void modifierEtatCommande(@RequestBody Paiement paiement) {

		log.info("----------- into update");

		CommandeBean commande = microserviceCommandeProxy.recupererUneCommande(paiement.getIdCommande()).get();

		log.info("----------- found commande");

		commande.setCommandePayee(true);

		log.info("----------- changed commande state");

		ResponseEntity<CommandeBean> commandeModifiee = microserviceCommandeProxy.updateCommande(commande);

		log.info("----------- commande sent");

		if (!commandeModifiee.getStatusCode().is2xxSuccessful()) {
			throw new PaiementImpossibleException("La Commande N°" + commandeModifiee.getBody().getId() + "liée a ce paiement n'as pas put etre mise a jour");
		}

		log.info("done updating");
	}
}
