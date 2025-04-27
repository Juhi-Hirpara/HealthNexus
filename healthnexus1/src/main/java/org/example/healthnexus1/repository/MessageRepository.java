package org.example.healthnexus1.repository;

import org.example.healthnexus1.entity.Appointment;
import org.example.healthnexus1.entity.Message;
import org.example.healthnexus1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findByAppointmentIdOrderBySentAtAsc(Appointment appointmentId);

    List<Message> findBySenderIdAndReceiverIdOrderBySentAtAsc(User senderId, User receiverId);
}