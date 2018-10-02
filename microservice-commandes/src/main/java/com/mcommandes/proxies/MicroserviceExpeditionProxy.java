package com.mcommandes.proxies;

import com.mcommandes.beans.ExpeditionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "zuul-server")
@RibbonClient(name = "microservice-expedition")
public interface MicroserviceExpeditionProxy {

	@PostMapping(value = "/microservice-expedition/expedition")
	ResponseEntity<ExpeditionBean> ajouterExpedition(@RequestBody ExpeditionBean expeditionBean);

	@GetMapping(value = "/microservice-expedition/expedition/{idCommande}")
	Optional<ExpeditionBean> etatExpedition(@PathVariable("idCommande") int idCommande);

	@PutMapping(value = "/microservice-expedition/expedition")
	void updateExpedition(@RequestBody ExpeditionBean expeditionBean);
}
