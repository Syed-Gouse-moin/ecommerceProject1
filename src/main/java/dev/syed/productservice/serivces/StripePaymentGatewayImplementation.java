package dev.syed.productservice.serivces;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentGatewayImplementation implements PaymentService{

    @Override
    public String makePayment(String orderid, Long amount) throws StripeException {
        Stripe.apiKey = "sk_test_51QfIepAwQlITtQmVxWBPyoOTUFL6bJdiQD9305CCOLoUvu8JOZqc0y1lFSxfrq2e4n4FHZN1j95u7q6dUI99ja2p0074LUMYR3";
        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("USD")
                        .setUnitAmount(amount)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName(orderid).build()
                        )
                        .build();
        Price price = Price.create(params);
        PaymentLinkCreateParams paramsLink =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl("https://www.google.com/")
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();
        PaymentLink paymentLink = PaymentLink.create(paramsLink);
        return paymentLink.getUrl();
    }
}
