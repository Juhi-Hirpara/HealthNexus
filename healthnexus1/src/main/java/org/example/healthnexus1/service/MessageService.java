//package org.example.healthnexus1.service;
//
//import org.example.healthnexus1.entity.Appointment;
//import org.example.healthnexus1.entity.Message;
//import org.example.healthnexus1.entity.User;
//import org.example.healthnexus1.repository.AppointmentRepository;
//import org.example.healthnexus1.repository.MessageRepository;
//import org.example.healthnexus1.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//public class MessageService {
//
//    @Autowired
//    private MessageRepository messageRepository;
//
//
//    public Message sendMessage(Integer senderId, Integer receiverId, Integer appointmentId, String messageText) {
//        // Fetch Appointment object by appointmentId
//        Appointment appointment = AppointmentRepository.findById(Long.valueOf(appointmentId))
//                .orElseThrow(() -> new RuntimeException("Appointment not found"));
//
//        // Fetch Sender user object by senderId
//        User sender = UserRepository.findById(senderId)
//                .orElseThrow(() -> new RuntimeException("Sender not found"));
//
//        // Fetch Receiver user object by receiverId
//        User receiver = UserRepository.findById(receiverId)
//                .orElseThrow(() -> new RuntimeException("Receiver not found"));
//
//        // Create and set up the Message object
//        Message message = new Message();
//        message.setMessage(messageText);
//        message.setAppointment(appointment); // Set the appointment
//        message.setSender(sender); // Set the sender user
//        message.setReceiver(receiver); // Set the receiver user
//        message.setSentAt(LocalDateTime.now()); // Set current time
//
//        // Save the message to the database
//        return messageRepository.save(message);
//    }
//
//    public List<Message> getMessagesByAppointment(Appointment appointmentId) {
//        return messageRepository.findByAppointmentIdOrderBySentAtAsc(appointmentId);
//    }
//
//    public List<Message> getMessagesBetweenUsers(User senderId, User receiverId) {
//        return messageRepository.findBySenderIdAndReceiverIdOrderBySentAtAsc(senderId, receiverId);
//    }
//
//
//}