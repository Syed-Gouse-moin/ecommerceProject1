package dev.syed.productservice.contollers;

import com.stripe.exception.StripeException;
import dev.syed.productservice.dtos.PaymentRequestDTO;
import dev.syed.productservice.serivces.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private PaymentService paymentService;
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }
    @PostMapping("/payments")
    public ResponseEntity<String> createPaymentLink(@RequestBody PaymentRequestDTO paymentRequestDTO) throws StripeException {
        String paymentLink = paymentService.makePayment(paymentRequestDTO.getOrderId(), paymentRequestDTO.getPaymentAmount());
        return new ResponseEntity<>(paymentLink, HttpStatus.OK);
    }
    @PostMapping("/webhook")
    public void handleWebhook() {
        System.out.println("Webhook revecied here");
        return;
    }
}
