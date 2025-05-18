package br.com.fiap.client_management.controller;

import br.com.fiap.client_management.controller.dto.ClientDto;
import br.com.fiap.client_management.domain.Address;
import br.com.fiap.client_management.domain.Client;
import br.com.fiap.client_management.gateway.database.jpa.ClientJpaGateway;
import br.com.fiap.client_management.usecase.GetClientIsActiveUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClientControllerTest {

    private ClientController clientController;

    @Mock
    private ClientJpaGateway clientJpaGateway;

    @Mock
    private GetClientIsActiveUseCase getClientIsActiveUseCase;

    private MockMvc mockMvc;
    private AutoCloseable autoCloseable;

    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        clientController = new ClientController(clientJpaGateway, getClientIsActiveUseCase);

        mockMvc = MockMvcBuilders
                .standaloneSetup(clientController)
                .build();
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getClientByCpf() throws Exception {
        Client client = new Client("John Doe",
                "12345678900",
                LocalDate.now(),
                new Address("Street", "1", "Apt 1", "12345-678", "City")
        );

        when(clientJpaGateway.findByCpf("12345678900")).thenReturn(client);

        mockMvc.perform(get("/client/getUser/{cpf}", "12345678900"))
                .andExpect(status().isOk());

        verify(clientJpaGateway, times(1)).findByCpf("12345678900");
    }

    @Test
    void createClient() {
        Client client = new Client("John Doe",
                "12345678900",
                LocalDate.now(),
                new Address("Street", "1", "Apt 1", "12345-678", "City")
        );
        ClientDto clientDto = new ClientDto(client);

        when(clientJpaGateway.create(client)).thenReturn(client);

        clientController.createClient(clientDto);

        verify(clientJpaGateway, times(1)).create(client);
    }

    @Test
    void updateClient() {
        Client newClient = new Client(1L,
                "John Doe",
                "12345678900",
                LocalDate.now(),
                new Address("Street", "1", "Apt 1", "12345-678", "City")
        );
        ClientDto newClientDto = new ClientDto(newClient);

        Client oldClient = new Client(1L,
                "Jane Doe",
                "12345678900",
                LocalDate.now(),
                new Address("Street", "2", "Apt 2", "87654-321", "New City")
        );

        when(clientJpaGateway.findById(1L)).thenReturn(oldClient);
        when(clientJpaGateway.update(oldClient)).thenReturn(newClient);

        clientController.updateClient(1L, newClientDto);

        verify(clientJpaGateway, times(1)).update(oldClient);
    }

    @Test
    void deleteClient() {
        Client client = new Client("John Doe",
                "12345678900",
                LocalDate.now(),
                new Address("Street", "1", "Apt 1", "12345-678", "City")
        );

        when(clientJpaGateway.findById(1L)).thenReturn(client);

        clientController.deleteClient(1L);

        verify(clientJpaGateway, times(1)).delete(client);
    }

    @Test
    void getClientIsActive() throws Exception {
        when(getClientIsActiveUseCase.get(1L)).thenReturn(true);

        mockMvc.perform(get("/client/api/isActive/{clientId}", 1L))
                .andExpect(status().isOk());

        verify(getClientIsActiveUseCase, times(1)).get(1L);
    }

}
