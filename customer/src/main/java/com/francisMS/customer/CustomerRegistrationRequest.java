package com.francisMS.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
