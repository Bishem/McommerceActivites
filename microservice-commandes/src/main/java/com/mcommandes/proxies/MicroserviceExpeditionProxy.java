package com.mcommandes.proxies;

import com.mcommandes.beans.ExpeditionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "zuul-server")
@RibbonClient(name = "microservice-expedition")
public interface MicroserviceExpeditionProxy {

    @GetMapping(value = "/microservice-expedition/expedition/{idCommande}")
    Optional<ExpeditionBean> etatExpedition(@PathVariable("idCommande") int idCommande);

    @PutMapping(value = "/microservice-expedition/expedition")
    ResponseEntity<ExpeditionBean> updateExpedition(@RequestBody ExpeditionBean expeditionBean);
}
