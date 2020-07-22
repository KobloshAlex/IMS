package com.cogent.insurance.service;

import com.cogent.insurance.model.payment.PaymentDto;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface StripeService {
    PaymentIntent paymentIntent(PaymentDto paymentIntentDto) throws StripeException;

    PaymentIntent confirm(String id) throws StripeException;

    PaymentIntent cancel(String id) throws StripeException;
}
