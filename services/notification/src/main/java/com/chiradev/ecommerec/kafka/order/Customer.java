package com.chiradev.ecommerec.kafka.order;

public record Customer(
        String id,

        String firstname,

        String lastname,

        String email
) {
}
