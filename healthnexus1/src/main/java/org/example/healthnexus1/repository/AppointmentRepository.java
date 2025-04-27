package org.example.healthnexus1.repository;

import org.example.healthnexus1.entity.Appointment;
import org.example.healthnexus1.entity.Appointment.AppointmentStatus;
import org.example.healthnexus1.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByUserIdAndStatus(Long userId, AppointmentStatus status);

    List<Appointment> findByProviderIdAndStatus(Long providerId, AppointmentStatus status);

//    public default Message sendMessage(Integer senderId, Integer receiverId, Integer appointmentId, String messageText) {
//        // Fetch Appointment object by appointmentId
//        Appointment appointment = AppointmentRepository.findById(Long.valueOf(appointmentId))
//                .orElseThrow(() -> new RuntimeException("Appointment not found"));
//    }
}
