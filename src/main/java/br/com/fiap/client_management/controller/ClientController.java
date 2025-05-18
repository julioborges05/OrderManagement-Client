package br.com.fiap.client_management.controller;

import br.com.fiap.client_management.controller.dto.AddressDto;
import br.com.fiap.client_management.controller.dto.ClientDto;
import br.com.fiap.client_management.domain.Address;
import br.com.fiap.client_management.domain.Client;
import br.com.fiap.client_management.gateway.database.jpa.ClientJpaGateway;
import br.com.fiap.client_management.usecase.GetClientIsActiveUseCase;
import br.com.fiap.client_management.usecase.CreateClientUseCase;
import br.com.fiap.client_management.usecase.UpdateClientUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientJpaGateway clientJpaGateway;
    private final GetClientIsActiveUseCase getClientIsActiveUseCase;

    public ClientController(ClientJpaGateway clientJpaGateway, GetClientIsActiveUseCase getClientIsActiveUseCase) {
        this.clientJpaGateway = clientJpaGateway;
        this.getClientIsActiveUseCase = getClientIsActiveUseCase;
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

    @PutMapping("/updateClient/{id}")
    public ClientDto updateClient(@PathVariable("id") Long clientId, @RequestBody ClientDto newClient) {
        Client client = clientJpaGateway.findById(clientId);
        Client updatedClient = UpdateClientUseCase.updateClient(client, toDomain(newClient));

        return new ClientDto(clientJpaGateway.update(updatedClient));
    }

    @DeleteMapping("/deleteClient/{id}")
    public void deleteClient(@PathVariable("id") Long clientId) {
        Client client = clientJpaGateway.findById(clientId);

        clientJpaGateway.delete(client);
    }

    @GetMapping("/api/isActive/{clientId}")
    public boolean getClientIsActive(@PathVariable Long clientId) {
        return getClientIsActiveUseCase.get(clientId);
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
