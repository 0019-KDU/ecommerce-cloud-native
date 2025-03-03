package com.chiradev.ecommerec.payment;

import com.chiradev.ecommerec.notification.NotificationProducer;
import com.chiradev.ecommerec.notification.PaymentNotificationRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;

    private final NotificationProducer notificationProducer;

    public Integer createPayment(@Valid PaymentRequest request) {

        var payment = paymentRepository.save(mapper.toPayment(request));

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );

        return payment.getId();
    }
}
