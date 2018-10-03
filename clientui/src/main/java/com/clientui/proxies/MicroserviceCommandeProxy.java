package com.clientui.proxies;

import com.clientui.beans.CommandeBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "zuul-server")
@RibbonClient(name = "microservice-commandes")
public interface MicroserviceCommandeProxy {

	@GetMapping(value = "/microservice-commandes/commandes/{id}")
	Optional<CommandeBean> recupererUneCommande(@PathVariable("id") int id);

	@PostMapping(value = "/microservice-commandes/commandes")
	ResponseEntity<CommandeBean> ajouterCommande(@RequestBody CommandeBean commande);
}
