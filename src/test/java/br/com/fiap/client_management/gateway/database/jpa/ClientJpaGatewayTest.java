package br.com.fiap.client_management.gateway.database.jpa;

import br.com.fiap.client_management.config.ClientConfig;
import br.com.fiap.client_management.domain.Address;
import br.com.fiap.client_management.domain.Client;
import br.com.fiap.client_management.gateway.database.jpa.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(ClientConfig.class)
public class ClientJpaGatewayTest {

    private ClientJpaGateway clientJpaGateway;
    private Client clientCreated;

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        clientJpaGateway = new ClientJpaGateway(clientRepository);

        Client client = new Client("Client name",
                "12345678900",
                LocalDate.of(1990, 1, 1),
                new Address("Street", "123", "Apt 1", "12345-678", "City")
        );
        clientCreated = clientJpaGateway.create(client);
    }

    @Test
    void findByCpf_throwsWhenClientNotFound() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> clientJpaGateway.findByCpf("0000")
        );

        assertEquals("Client not found", exception.getMessage());
    }

    @Test
    void findByCpf_returnsClientWhenFound() {
        Client foundClient = clientJpaGateway.findByCpf(clientCreated.getCpf());

        assertNotNull(foundClient.getId());
        assertEquals(clientCreated.getName(), foundClient.getName());
        assertEquals(clientCreated.getCpf(), foundClient.getCpf());
        assertEquals(clientCreated.getBirthDate(), foundClient.getBirthDate());
        assertEquals(clientCreated.getAddress().getStreet(), foundClient.getAddress().getStreet());
        assertEquals(clientCreated.getAddress().getNumber(), foundClient.getAddress().getNumber());
        assertEquals(clientCreated.getAddress().getComplement(), foundClient.getAddress().getComplement());
        assertEquals(clientCreated.getAddress().getZipCode(), foundClient.getAddress().getZipCode());
        assertEquals(clientCreated.getAddress().getCity(), foundClient.getAddress().getCity());
    }

    @Test
    void create_throwsWhenClientAlreadyExists() {
        Client clientWithId = new Client(clientCreated.getId(), clientCreated.getName(), clientCreated.getCpf(),
                clientCreated.getBirthDate(), clientCreated.getAddress());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> clientJpaGateway.create(clientWithId)
        );

        assertEquals("Client already exists", exception.getMessage());
    }

    @Test
    void create_throwsWhenClientAlreadyExists2() {
        Client clientWithoutId = new Client(clientCreated.getName(), clientCreated.getCpf(),
                clientCreated.getBirthDate(), clientCreated.getAddress());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> clientJpaGateway.create(clientWithoutId)
        );

        assertEquals("Client already exists", exception.getMessage());
    }

    @Test
    void create_clientCreated() {
        Client client = new Client("Client name",
                "12345",
                LocalDate.of(1990, 1, 1),
                new Address("Street", "123", "Apt 1", "12345-678", "City")
        );

        Client createdClient = clientJpaGateway.create(client);

        assertNotNull(createdClient.getId());
        assertEquals(client.getName(), createdClient.getName());
        assertEquals(client.getCpf(), createdClient.getCpf());
        assertEquals(client.getBirthDate(), createdClient.getBirthDate());
        assertEquals(client.getAddress().getStreet(), createdClient.getAddress().getStreet());
        assertEquals(client.getAddress().getNumber(), createdClient.getAddress().getNumber());
        assertEquals(client.getAddress().getComplement(), createdClient.getAddress().getComplement());
        assertEquals(client.getAddress().getZipCode(), createdClient.getAddress().getZipCode());
        assertEquals(client.getAddress().getCity(), createdClient.getAddress().getCity());
    }

    @Test
    void findById() {
        Client foundClient = clientJpaGateway.findById(clientCreated.getId());

        assertNotNull(foundClient.getId());
        assertEquals(clientCreated.getName(), foundClient.getName());
        assertEquals(clientCreated.getCpf(), foundClient.getCpf());
        assertEquals(clientCreated.getBirthDate(), foundClient.getBirthDate());
        assertEquals(clientCreated.getAddress().getStreet(), foundClient.getAddress().getStreet());
        assertEquals(clientCreated.getAddress().getNumber(), foundClient.getAddress().getNumber());
        assertEquals(clientCreated.getAddress().getComplement(), foundClient.getAddress().getComplement());
        assertEquals(clientCreated.getAddress().getZipCode(), foundClient.getAddress().getZipCode());
        assertEquals(clientCreated.getAddress().getCity(), foundClient.getAddress().getCity());
    }

    @Test
    void update_throwsWhenClientNotFound() {
        Client client = new Client(999L,
                "Client name",
                "12345678900",
                LocalDate.of(1990, 1, 1),
                new Address("Street", "123", "Apt 1", "12345-678", "City")
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> clientJpaGateway.update(client)
        );

        assertEquals("Client not found", exception.getMessage());
    }

    @Test
    void update_clientUpdated() {
        Client client = new Client(clientCreated.getId(),
                "Updated name",
                clientCreated.getCpf(),
                LocalDate.of(1990, 1, 1),
                new Address("Street", "123", "Apt 1", "12345-678", "City")
        );

        Client updatedClient = clientJpaGateway.update(client);

        assertNotNull(updatedClient.getId());
        assertEquals(client.getName(), updatedClient.getName());
        assertEquals(client.getCpf(), updatedClient.getCpf());
        assertEquals(client.getBirthDate(), updatedClient.getBirthDate());
        assertEquals(client.getAddress().getStreet(), updatedClient.getAddress().getStreet());
        assertEquals(client.getAddress().getNumber(), updatedClient.getAddress().getNumber());
        assertEquals(client.getAddress().getComplement(), updatedClient.getAddress().getComplement());
        assertEquals(client.getAddress().getZipCode(), updatedClient.getAddress().getZipCode());
        assertEquals(client.getAddress().getCity(), updatedClient.getAddress().getCity());
    }

    @Test
    void delete_throwsWhenClientNotFound() {
        Client client = new Client(999L,
                "Client name",
                "12345678900",
                LocalDate.of(1990, 1, 1),
                new Address("Street", "123", "Apt 1", "12345-678", "City")
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> clientJpaGateway.delete(client)
        );

        assertEquals("Client not found", exception.getMessage());
    }

    @Test
    void delete_clientDeleted() {
        clientJpaGateway.delete(clientCreated);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> clientJpaGateway.findById(clientCreated.getId())
        );

        assertEquals("Client not found", exception.getMessage());
    }

    @Test
    void getClientIsActive() {
        boolean isActive = clientJpaGateway.getClientIsActive(clientCreated.getId());

        assertTrue(isActive);
    }

    @Test
    void getClientIsActive_throwsWhenClientNotFound() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> clientJpaGateway.getClientIsActive(999L)
        );

        assertEquals("Client not found", exception.getMessage());
    }

}
