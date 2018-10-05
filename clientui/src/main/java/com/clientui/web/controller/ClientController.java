package com.clientui.web.controller;

import com.clientui.beans.CommandeBean;
import com.clientui.beans.ExpeditionBean;
import com.clientui.beans.PaiementBean;
import com.clientui.beans.ProductBean;
import com.clientui.proxies.MicroserviceCommandeProxy;
import com.clientui.proxies.MicroserviceExpeditionProxy;
import com.clientui.proxies.MicroservicePaiementProxy;
import com.clientui.proxies.MicroserviceProduitsProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


@Controller
public class ClientController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MicroserviceProduitsProxy ProduitsProxy;
    @Autowired
    private MicroserviceCommandeProxy CommandesProxy;

    @Autowired
    private MicroserviceExpeditionProxy expeditionProxy;

    @Autowired
    private MicroservicePaiementProxy paiementProxy;

    @GetMapping("/")
    public String accueil(Model model) {

        log.info("Envoi requête vers microservice-produits");

        List<ProductBean> produits = ProduitsProxy.listeDesProduits();

        model.addAttribute("produits", produits);

        return "Accueil";
    }

    @GetMapping("/details-produit/{id}")
    public String ficheProduit(@PathVariable int id, Model model) {

        ProductBean produit = ProduitsProxy.recupererUnProduit(id);

        model.addAttribute("produit", produit);

        return "FicheProduit";
    }

    @GetMapping(value = "/commander-produit/{idProduit}/{prixProduit}")
    public String passerCommande(@PathVariable int idProduit, @PathVariable Double prixProduit, Model model) {

        Integer taxes = new Random().nextInt(10) + 10;
        Integer quantiteAleatoire = new Random().nextInt(9) + 1;
        Double montantCommande = (prixProduit + taxes) * quantiteAleatoire;

        CommandeBean commande = new CommandeBean();

        commande.setProductId(idProduit);
        commande.setQuantite(quantiteAleatoire);
        commande.setDateCommande(new Date());

        ResponseEntity<CommandeBean> commandeAjoutee = CommandesProxy.ajouterCommande(commande);

        model.addAttribute("commande", commandeAjoutee.getBody());
        model.addAttribute("montantCommande", montantCommande);

        return "Paiement";
    }

    @GetMapping(value = "/payer-commande/{idCommande}/{montantCommande}")
    public String payerCommande(@PathVariable int idCommande, @PathVariable Double montantCommande, Model model) {

        PaiementBean paiementAExcecuter = new PaiementBean();

        paiementAExcecuter.setIdCommande(idCommande);
        paiementAExcecuter.setMontant(montantCommande);
        paiementAExcecuter.setNumeroCarte(numcarte());

        ResponseEntity<PaiementBean> paiement = paiementProxy.payerUneCommande(paiementAExcecuter);

        CommandeBean commande = null;

        if (paiement.getStatusCode().is2xxSuccessful()) {
            commande = CommandesProxy.recupererUneCommande(idCommande).get();
        }

        model.addAttribute("commande", commande);
        model.addAttribute("montantCommande", montantCommande);

        return "Confirmation";
    }

    @GetMapping(value = "/suivi/{idCommande}/{montantCommande}")
    public String suivreExpedition(@PathVariable int idCommande, @PathVariable Double montantCommande, Model model) {

        CommandeBean commande = CommandesProxy.recupererUneCommande(idCommande).get();
        Optional<ExpeditionBean> expedition = expeditionProxy.etatExpedition(commande.getId());

        model.addAttribute("expedition", expedition.get());
        model.addAttribute("commande", commande);
        model.addAttribute("montantCommande", montantCommande);

        return "Expedition";
    }

    private Long numcarte() {

        return ThreadLocalRandom.current().nextLong(1000000000000000L, 9000000000000000L);
    }
}
