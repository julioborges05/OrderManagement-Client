package br.com.fiap.client_management.gateway.database.jpa.entity;

import br.com.fiap.client_management.domain.Address;
import br.com.fiap.client_management.domain.Client;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String street;
    private String number;
    private String complement;
    private String zipCode;
    private String city;
    private boolean isActive = true;

    public ClientEntity() {
    }

    public ClientEntity(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.birthDate = client.getBirthDate();
        this.street = client.getAddress().getStreet();
        this.number = client.getAddress().getNumber();
        this.complement = client.getAddress().getComplement();
        this.zipCode = client.getAddress().getZipCode();
        this.city = client.getAddress().getCity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public Client toDomain() {
        Address address = new Address(street, number, complement, zipCode, city);

        return new Client(id, name, cpf, birthDate, address);
    }
}
