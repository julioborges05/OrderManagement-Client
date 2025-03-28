package br.com.fiap.client_management.domain;

import io.micrometer.common.util.StringUtils;

import java.time.LocalDate;
import java.util.Objects;

public class Client {

    private Long id;
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private Address address;

    public Client(Long id, String name, String cpf, LocalDate birthDate, Address address) {
        validateClientId(id);
        validateClientName(name);
        validateClientCpf(cpf);
        validateClientBirthDate(birthDate);
        validateClientAddress(address);

        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.address = address;
    }

    public Client(String name, String cpf, LocalDate birthDate, Address address) {
        validateClientName(name);
        validateClientCpf(cpf);
        validateClientBirthDate(birthDate);
        validateClientAddress(address);

        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.address = address;
    }

    private void validateClientId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id is required");
        }
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

    private void validateClientAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address is required");
        }
    }

    public Client updateFields(Client newFields) {
        if (!Objects.equals(this.id, newFields.id)) {
            throw new IllegalArgumentException("Id cannot be changed");
        }

        if (!this.cpf.equals(newFields.cpf)) {
            throw new IllegalArgumentException("Cpf cannot be changed");
        }

        if (!this.birthDate.equals(newFields.birthDate)) {
            throw new IllegalArgumentException("Birth date cannot be changed");
        }

        this.name = newFields.name;
        this.address = newFields.address;

        return this;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Client that = (Client) o;
        return Objects.equals(name, that.name)
                && Objects.equals(cpf, that.cpf)
                && Objects.equals(birthDate, that.birthDate)
                && Objects.equals(address, that.address);
    }
}
