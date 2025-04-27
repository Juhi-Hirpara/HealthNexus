//package org.example.healthnexus1.dto;
//
//import java.math.BigDecimal;
//
//public class PaymentRequest {
//
//    private int userId;
//    private int providerId;
//    private int appointmentId;
//    private BigDecimal amount;
//    private String paymentMethod;
//
//    // Constructors
//    public PaymentRequest() {}
//    private String paymentMethodId; // Changed from paymentMethod to paymentMethodId
//
//    public PaymentRequest(int userId, int providerId, int appointmentId, BigDecimal amount, String paymentMethod) {
//        this.userId = userId;
//        this.providerId = providerId;
//        this.appointmentId = appointmentId;
//        this.amount = amount;
//        this.paymentMethodId = paymentMethodId;}
//
//    // Getters and Setters
//    public long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public long getProviderId() {
//        return providerId;
//    }
//
//    public void setProviderId(int providerId) {
//        this.providerId = providerId;
//    }
//
//    public long getAppointmentId() {
//        return appointmentId;
//    }
//
//    public void setAppointmentId(int appointmentId) {
//        this.appointmentId = appointmentId;
//    }
//
//    public BigDecimal getAmount() {
//        return amount;
//    }
//
//    public void setAmount(BigDecimal amount) {
//        this.amount = amount;
//    }
//
//
//    public String getPaymentMethodId() { // Changed getter name
//        return paymentMethodId;
//    }
//
//    public void setPaymentMethodId(String paymentMethodId) { // Changed setter name
//        this.paymentMethodId = paymentMethodId;
//    }
//}
package org.example.healthnexus1.dto;

import java.math.BigDecimal;

public class PaymentRequest {

    private int userId;
    private int providerId;
    private int appointmentId;
    private BigDecimal amount;
    private String paymentMethodId; // Changed from paymentMethod to paymentMethodId

    // Constructors
    public PaymentRequest() {}

    public PaymentRequest(int userId, int providerId, int appointmentId, BigDecimal amount, String paymentMethodId) {
        this.userId = userId;
        this.providerId = providerId;
        this.appointmentId = appointmentId;
        this.amount = amount;
        this.paymentMethodId = paymentMethodId; // Use paymentMethodId here
    }

    // Getters and Setters
    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethodId() { // Correct getter for paymentMethodId
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) { // Correct setter for paymentMethodId
        this.paymentMethodId = paymentMethodId;
    }
}
