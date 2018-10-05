package com.mpaiement.web.controller;

import com.mpaiement.beans.CommandeBean;
import com.mpaiement.beans.ExpeditionBean;
import com.mpaiement.dao.PaiementDao;
import com.mpaiement.model.Paiement;
import com.mpaiement.proxies.MicroserviceCommandeProxy;
import com.mpaiement.proxies.MicroserviceExpeditionProxy;
import com.mpaiement.web.exceptions.PaiementExistantException;
import com.mpaiement.web.exceptions.PaiementImpossibleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PaiementController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PaiementDao paiementDao;

    @Autowired
    MicroserviceCommandeProxy microserviceCommandeProxy;

    @Autowired
    MicroserviceExpeditionProxy microserviceExpeditionProxy;

    @PostMapping(value = "/paiement")
    public ResponseEntity<Paiement> payerUneCommande(@RequestBody Paiement paiement) {

        testerExistancePaiement(paiement);
        Paiement nouveauPaiement = paiementDao.save(paiement);
        modifierEtatCommande(paiement);

        return new ResponseEntity<>(nouveauPaiement, HttpStatus.CREATED);
    }

    private void testerExistancePaiement(@RequestBody Paiement paiement) {

        Optional<Paiement> paiementExistant = paiementDao.findByidCommande(paiement.getIdCommande());

        if (paiementExistant.isPresent()) {
            throw new PaiementExistantException("Cette commande est déjà payée");
        }
    }

    private void modifierEtatCommande(@RequestBody Paiement paiement) {

        CommandeBean commande = microserviceCommandeProxy.recupererUneCommande(paiement.getIdCommande()).get();

        ResponseEntity<ExpeditionBean> nouvelleExpedition = microserviceExpeditionProxy.ajouterExpedition(new ExpeditionBean(commande.getId(), 0));

        if (!nouvelleExpedition.hasBody()) {
            throw new PaiementImpossibleException("Impossible d'expedier cette commande");
        } else {
            commande.setCommandePayee(true);
            ResponseEntity<CommandeBean> commandeModifiee = microserviceCommandeProxy.updateCommande(commande);

            if (!commandeModifiee.getStatusCode().is2xxSuccessful()) {
                throw new PaiementImpossibleException("La Commande N°" + commandeModifiee.getBody().getId() + "liée a ce paiement n'as pas put etre mise a jour");
            }
        }
    }
}
