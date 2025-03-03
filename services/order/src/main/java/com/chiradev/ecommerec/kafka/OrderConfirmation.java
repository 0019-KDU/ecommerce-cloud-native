package com.chiradev.ecommerec.kafka;

import com.chiradev.ecommerec.customer.CustomerResponse;
import com.chiradev.ecommerec.order.PaymentMethod;
import com.chiradev.ecommerec.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}