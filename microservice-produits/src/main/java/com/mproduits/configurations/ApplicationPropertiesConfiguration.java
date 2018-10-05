package com.mproduits.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mes-configs")
@Getter
@Setter
public class ApplicationPropertiesConfiguration {

    private int limitDeProduits;
}
