package com.chiradev.ecommerce.payment;

import com.chiradev.ecommerce.customer.CustomerResponse;
import com.chiradev.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}