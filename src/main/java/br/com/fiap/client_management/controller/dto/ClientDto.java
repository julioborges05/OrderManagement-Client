package br.com.fiap.client_management.controller.dto;

import br.com.fiap.client_management.domain.Client;

import java.time.LocalDate;

public record ClientDto(Long id, String name, String cpf, LocalDate birthDate, AddressDto address) {

    public ClientDto(Client client) {
        this(client.getId(), client.getName(), client.getCpf(), client.getBirthDate(), new AddressDto(client.getAddress()));
    }

}
