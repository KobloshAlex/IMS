package com.cogent.insurance.service.impl;

import com.cogent.insurance.model.payment.PaymentDto;
import com.cogent.insurance.service.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StripeServiceImpl implements StripeService {

  private static final String AMOUNT = "amount";
  private static final String CURRENCY = "currency";
  private static final String DESCRIPTION = "description";
  private static final String PAYMENT_METHOD_TYPES = "payment_method_types";
  private static final String PAYMENT_METHOD = "payment_method";
  private static final String PM_CARD_VISA = "pm_card_visa";

  @Value("${stripe.key.secret}")
  private String secretKey;

  @Override
  public PaymentIntent paymentIntent(PaymentDto paymentIntentDto) throws StripeException {
    Stripe.apiKey = secretKey;
    List<String> paymentMethodTypes = new ArrayList<>();
    paymentMethodTypes.add("card");
    Map<String, Object> params = new HashMap<>();
    params.put(AMOUNT, paymentIntentDto.getAmount());
    params.put(CURRENCY, paymentIntentDto.getCurrency());
    params.put(DESCRIPTION, paymentIntentDto.getDescription());
    params.put(PAYMENT_METHOD_TYPES, paymentMethodTypes);
    return PaymentIntent.create(params);
  }

  @Override
  public PaymentIntent confirm(String id) throws StripeException {
    Stripe.apiKey = secretKey;
    PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
    Map<String, Object> params = new HashMap<>();
    params.put(PAYMENT_METHOD, PM_CARD_VISA);
    paymentIntent.confirm(params);
    return paymentIntent;
  }

  @Override
  public PaymentIntent cancel(String id) throws StripeException {
    Stripe.apiKey = secretKey;
    PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
    paymentIntent.cancel();
    return paymentIntent;
  }
}
