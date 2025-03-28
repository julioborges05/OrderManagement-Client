package br.com.fiap.client_management.controller;

import br.com.fiap.client_management.controller.dto.AddressDto;
import br.com.fiap.client_management.controller.dto.ClientDto;
import br.com.fiap.client_management.domain.Address;
import br.com.fiap.client_management.domain.Client;
import br.com.fiap.client_management.gateway.database.jpa.ClientJpaGateway;
import br.com.fiap.client_management.usecase.CreateClientUseCase;
import br.com.fiap.client_management.usecase.UpdateClientUseCase;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientJpaGateway clientJpaGateway;

    public ClientController(ClientJpaGateway clientJpaGateway) {
        this.clientJpaGateway = clientJpaGateway;
    }

    @GetMapping("/getUser/{cpf}")
    public ClientDto getClientByCpf(@PathVariable("cpf") String cpf) {
        return new ClientDto(clientJpaGateway.findByCpf(cpf));
    }

    @PostMapping("/createClient")
    public ClientDto createClient(@RequestBody ClientDto clientDto) {
        AddressDto addressDto = clientDto.address();
        Client client = CreateClientUseCase.createClient(clientDto.name(), clientDto.cpf(), clientDto.birthDate(),
                addressDto.street(), addressDto.number(), addressDto.complement(), addressDto.zipCode(), addressDto.city());

        return new ClientDto(clientJpaGateway.create(client));
    }

    @PutMapping("/updateUser/{id}")
    public ClientDto updateClient(@PathVariable("id") Long clientId, @RequestBody ClientDto newClient) {
        Client client = clientJpaGateway.findById(clientId);
        Client updatedClient = UpdateClientUseCase.updateClient(client, toDomain(newClient));

        return new ClientDto(clientJpaGateway.update(updatedClient));
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteClient(@PathVariable("id") Long clientId) {
        Client client = clientJpaGateway.findById(clientId);

        clientJpaGateway.delete(client);
    }

    private Client toDomain(ClientDto clientDto) {
        AddressDto addressDto = clientDto.address();
        Address address = new Address(
                addressDto.street(),
                addressDto.number(),
                addressDto.complement(),
                addressDto.zipCode(),
                addressDto.city()
        );

        return new Client(
                clientDto.id(),
                clientDto.name(),
                clientDto.cpf(),
                clientDto.birthDate(),
                address
        );
    }

}
