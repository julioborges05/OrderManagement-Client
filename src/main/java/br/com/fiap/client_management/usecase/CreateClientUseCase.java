package br.com.fiap.client_management.usecase;

import br.com.fiap.client_management.entity.AddressEntity;
import br.com.fiap.client_management.entity.ClientEntity;

import java.time.LocalDate;

public class CreateClientUseCase {

    public static ClientEntity createClient(String name, String cpf, LocalDate birthDate, String street, String number,
                                            String complement, String cep, String city) {
        AddressEntity address = new AddressEntity(street, number, complement, cep, city);

        return new ClientEntity(name, cpf, birthDate, address);
    }

}
