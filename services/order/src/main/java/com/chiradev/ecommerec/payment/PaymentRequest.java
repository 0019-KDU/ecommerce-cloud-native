package com.chiradev.ecommerec.payment;

import com.chiradev.ecommerec.customer.CustomerResponse;
import com.chiradev.ecommerec.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}