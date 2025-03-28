package br.com.fiap.client_management.controller.dto;

import br.com.fiap.client_management.domain.Address;

public record AddressDto(String street, String number, String complement, String zipCode, String city) {

    public AddressDto(Address address) {
        this(address.getStreet(), address.getNumber(), address.getComplement(), address.getZipCode(), address.getCity());
    }
}
