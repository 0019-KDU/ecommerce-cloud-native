package com.chiradev.ecommerec.orderline;

import com.chiradev.ecommerec.order.Order;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class OrderLine {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private double productId;
    private double quantity;


}
