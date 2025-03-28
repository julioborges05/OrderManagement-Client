package br.com.fiap.client_management.config;

import br.com.fiap.client_management.gateway.database.jpa.ClientJpaGateway;
import br.com.fiap.client_management.gateway.database.jpa.repository.ClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public ClientJpaGateway clientJpaGateway(ClientRepository clientRepository) {
        return new ClientJpaGateway(clientRepository);
    }

}
