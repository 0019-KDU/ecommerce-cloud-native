package com.chiradev.ecommerec.order;

import com.chiradev.ecommerec.customer.CustomerClient;
import com.chiradev.ecommerec.exception.BusinessException;
import com.chiradev.ecommerec.orderline.OrderLineRequest;
import com.chiradev.ecommerec.orderline.OrderLineService;
import com.chiradev.ecommerec.product.ProductClient;
import com.chiradev.ecommerec.product.PurchaseRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    public Integer createdOrder(@Valid OrderRequest request) {

        var customer=this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Customer not found::No Customer Found With ID"));

        this.productClient.purchaseProducts(request.products());

        var order=this.repository.save(mapper.toOrder(request));

        for (PurchaseRequest purchaseRequest: request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.quantity()
                    )
                    );
            )
        }

    }
}
