//package org.example.healthnexus1.controller;
//
//import org.example.healthnexus1.dto.PaymentRequest;
//import org.example.healthnexus1.entity.PaymentHistory;
//import org.example.healthnexus1.service.PaymentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//// This exposes REST APIs for payment operations
//@RestController
//@RequestMapping("/api/payments")
//public class PaymentController {
//
//    @Autowired
//    private PaymentService paymentService;
//
//    // Create a new Payment
//    @PostMapping("/create")
//    public ResponseEntity<PaymentHistory> createPayment(@RequestBody PaymentRequest paymentRequest) {
//        PaymentHistory payment = paymentService.createPayment(paymentRequest);
//        return ResponseEntity.ok(payment);
//    }
//
//    // Update Payment Status
//    @PutMapping("/{paymentId}/status")
//    public ResponseEntity<PaymentHistory> updatePaymentStatus(
//            @PathVariable Integer paymentId,
//            @RequestParam String status) {
//        PaymentHistory updatedPayment = paymentService.updatePaymentStatus(paymentId, status);
//        return ResponseEntity.ok(updatedPayment);
//    }
//
//    // Optional: You can add more APIs later to get all payments, get payment by ID, etc.
//}
package org.example.healthnexus1.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.example.healthnexus1.dto.PaymentRequest;
import org.example.healthnexus1.entity.PaymentHistory;
import org.example.healthnexus1.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*") // allow frontend access
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public Map<String, Object> createPayment(@RequestBody PaymentRequest paymentRequest) throws StripeException {
        // 1. Convert BigDecimal amount to long (amount in cents for Stripe)
        BigDecimal amountInCents = paymentRequest.getAmount().multiply(BigDecimal.valueOf(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
        long amount = amountInCents.longValue(); // Get the amount in cents

        // 2. Create a PaymentIntent with Stripe
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount)  // amount in cents
                .setCurrency("usd") // Currency code, could be "usd" or others
                .setPaymentMethod(List.of("card").toString())  // Add other payment methods if needed
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        // 2. Save Payment in your Database
        PaymentHistory payment = paymentService.createPayment(paymentRequest);

        // 3. Send back client_secret (frontend uses it to confirm payment)
        Map<String, Object> response = new HashMap<>();
        response.put("paymentIntentClientSecret", paymentIntent.getClientSecret());
        response.put("paymentId", payment.getId());
        return response;
    }

    @PostMapping("/update-status/{paymentId}")
    public PaymentHistory updatePaymentStatus(@PathVariable Integer paymentId, @RequestParam String status) {
        return paymentService.updatePaymentStatus(paymentId, status);
    }
}
