package org.example.healthnexus1.controller;

import org.example.healthnexus1.dto.RescheduleRequest;
import org.example.healthnexus1.entity.Appointment;
import org.example.healthnexus1.entity.Appointment.AppointmentStatus;
import org.example.healthnexus1.entity.Provider;
import org.example.healthnexus1.entity.User;
import org.example.healthnexus1.repository.AppointmentRepository;
import org.example.healthnexus1.repository.ProviderRepository;
import org.example.healthnexus1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProviderRepository providerRepository;

    // 📅 Book new appointment
    @PostMapping("/book")
    public Appointment bookAppointment(@RequestBody Appointment appointment) {
        Optional<User> userOpt = userRepository.findById(appointment.getUser().getId());
        Optional<Provider> providerOpt = providerRepository.findById(appointment.getProvider().getId());

        if (userOpt.isPresent()) {
            appointment.setUser(userOpt.get());
            appointment.setProvider(providerOpt.get());
            appointment.setStatus(AppointmentStatus.UPCOMING);
            return appointmentRepository.save(appointment);
        } else {
            throw new RuntimeException("User or Provider not found");
        }
    }

    // ✅ Get user's upcoming appointments
    @GetMapping("/user/{userId}/upcoming")
    public List<Appointment> getUpcomingAppointmentsForUser(@PathVariable Long userId) {
        return appointmentRepository.findByUserIdAndStatus(userId, AppointmentStatus.UPCOMING);
    }

    // ✅ Get provider's upcoming appointments
    @GetMapping("/provider/{providerId}/upcoming")
    public List<Appointment> getUpcomingAppointmentsForProvider(@PathVariable Long providerId) {
        return appointmentRepository.findByProviderIdAndStatus(providerId, AppointmentStatus.UPCOMING);
    }

    // 🏁 Mark as completed
    @PutMapping("/{id}/complete")
    public Appointment completeAppointment(@PathVariable Long id) {
        return updateStatus(id, AppointmentStatus.COMPLETED);
    }

    // ❌ Cancel appointment
    @PutMapping("/{id}/cancel")
    public Appointment cancelAppointment(@PathVariable Long id) {
        return updateStatus(id, AppointmentStatus.CANCELED);
    }

    // 🔁 Reschedule appointment
    @PutMapping("/{id}/reschedule")
    public Appointment rescheduleAppointment(@PathVariable Long id) {
        return updateStatus(id, AppointmentStatus.RESCHEDULED);
    }

    // 🔄 Status updater
    private Appointment updateStatus(Long id, AppointmentStatus status) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            appointment.setStatus(status);
            return appointmentRepository.save(appointment);
        } else {
            throw new RuntimeException("Appointment not found");
        }
    }



}
