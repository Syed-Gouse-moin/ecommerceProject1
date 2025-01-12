package dev.syed.productservice.serivces;

import com.stripe.exception.StripeException;

public interface PaymentService {
    String makePayment(String orderid,Long amount) throws StripeException;
}
