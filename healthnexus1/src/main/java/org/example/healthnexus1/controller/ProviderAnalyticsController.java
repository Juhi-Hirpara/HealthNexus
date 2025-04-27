package org.example.healthnexus1.controller;

import org.example.healthnexus1.dto.AnalyticsResponse;
import org.example.healthnexus1.entity.Appointment;
import org.example.healthnexus1.repository.AppointmentRepository;
import org.example.healthnexus1.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analytics")
@CrossOrigin(origins = "http://localhost:3000")
public class ProviderAnalyticsController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ProviderRepository providerRepository;

    // 📊 Get provider analytics
    @GetMapping("/provider/{providerId}")
    public AnalyticsResponse getProviderAnalytics(@PathVariable Long providerId) {
        // Fetch the provider's upcoming, completed, and canceled appointments
        List<Appointment> upcomingAppointments = appointmentRepository.findByProviderIdAndStatus(providerId, Appointment.AppointmentStatus.UPCOMING);
        List<Appointment> completedAppointments = appointmentRepository.findByProviderIdAndStatus(providerId, Appointment.AppointmentStatus.COMPLETED);
        List<Appointment> canceledAppointments = appointmentRepository.findByProviderIdAndStatus(providerId, Appointment.AppointmentStatus.CANCELED);

        // Calculate the metrics for analytics
        int totalAppointments = upcomingAppointments.size() + completedAppointments.size() + canceledAppointments.size();
        double completionRate = (totalAppointments > 0) ? (completedAppointments.size() * 100.0) / totalAppointments : 0;
        double cancellationRate = (totalAppointments > 0) ? (canceledAppointments.size() * 100.0) / totalAppointments : 0;

        // Return the analytics response
        AnalyticsResponse response = new AnalyticsResponse();
        response.setTotalAppointments(totalAppointments);
        response.setUpcomingAppointments(upcomingAppointments.size());
        response.setCompletedAppointments(completedAppointments.size());
        response.setCanceledAppointments(canceledAppointments.size());
        response.setCompletionRate(completionRate);
        response.setCancellationRate(cancellationRate);

        return response;
    }
}
