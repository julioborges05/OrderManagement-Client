package br.com.fiap.client_management.gateway;

import br.com.fiap.client_management.domain.Client;

public interface ClientGateway {

    Client findByCpf(String cpf);

    Client create(Client client);

    Client findById(Long id);

    Client update(Client client);

    void delete(Client client);
}
