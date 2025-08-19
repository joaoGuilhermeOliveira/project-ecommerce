package com.ecommerce.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.ecommerce.store.keycloak.KeycloakProperties;

@SpringBootApplication
@EnableConfigurationProperties(KeycloakProperties.class)
public class EcommerceStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceStoreApplication.class, args);
	}

}
