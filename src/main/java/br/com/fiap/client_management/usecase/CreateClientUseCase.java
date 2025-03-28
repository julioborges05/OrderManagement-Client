package br.com.fiap.client_management.usecase;

import br.com.fiap.client_management.domain.Address;
import br.com.fiap.client_management.domain.Client;

import java.time.LocalDate;

public class CreateClientUseCase {

    public static Client createClient(String name, String cpf, LocalDate birthDate, String street, String number,
                                      String complement, String cep, String city) {
        Address address = new Address(street, number, complement, cep, city);

        return new Client(name, cpf, birthDate, address);
    }

}
