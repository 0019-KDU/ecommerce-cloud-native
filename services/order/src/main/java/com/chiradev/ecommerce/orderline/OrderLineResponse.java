package com.chiradev.ecommerce.orderline;

public record OrderLineResponse(
        Integer id,
        double quantity
) { }