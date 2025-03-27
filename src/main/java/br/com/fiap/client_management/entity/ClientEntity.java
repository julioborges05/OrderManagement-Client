package br.com.fiap.client_management.entity;

import io.micrometer.common.util.StringUtils;

import java.time.LocalDate;

public class ClientEntity {

    private String name;
    private String cpf;
    private LocalDate birthDate;
    private AddressEntity address;

    public ClientEntity(String name, String cpf, LocalDate birthDate, AddressEntity address) {
        validateClientName(name);
        validateClientCpf(cpf);
        validateClientBirthDate(birthDate);
        validateClientAddress(address);

        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.address = address;
    }

    private void validateClientName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name is required");
        }
    }

    private void validateClientCpf(String cpf) {
        if (StringUtils.isBlank(cpf)) {
            throw new IllegalArgumentException("Cpf is required");
        }
    }

    private void validateClientBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("Birth date is required");
        }

        if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth date must be in the past");
        }
    }

    private void validateClientAddress(AddressEntity address) {
        if (address == null) {
            throw new IllegalArgumentException("Address is required");
        }
    }


}
