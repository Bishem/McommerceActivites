package com.mproduits.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("mes-configs")
public class ApplicationPropertiesConfiguration {

    @Getter
    @Setter
    private int limitDeProduits;
}
