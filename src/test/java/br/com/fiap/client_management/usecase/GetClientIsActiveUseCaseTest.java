package br.com.fiap.client_management.usecase;

import br.com.fiap.client_management.gateway.database.jpa.ClientJpaGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class GetClientIsActiveUseCaseTest {

    @Mock
    private ClientJpaGateway clientJpaGateway;

    private GetClientIsActiveUseCase getClientIsActiveUseCase;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        getClientIsActiveUseCase = new GetClientIsActiveUseCase(clientJpaGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void throwExceptionWhenClientIdIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> getClientIsActiveUseCase.get(null)
        );

        assertEquals("Client ID cannot be null", exception.getMessage());
    }

    @Test
    void throwExceptionWhenClientIdIsLessThanOrEqualToZero() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> getClientIsActiveUseCase.get(0L)
        );

        assertEquals("Client ID must be greater than zero", exception.getMessage());
    }

    @Test
    void returnTrueWhenClientIsActive() {
        Long clientId = 1L;
        boolean expectedResult = true;

        when(clientJpaGateway.getClientIsActive(clientId)).thenReturn(expectedResult);

        boolean result = getClientIsActiveUseCase.get(clientId);

        assertEquals(expectedResult, result);
    }

}
