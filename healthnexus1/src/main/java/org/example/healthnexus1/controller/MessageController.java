//package org.example.healthnexus1.controller;
//
//import org.example.healthnexus1.entity.Appointment;
//import org.example.healthnexus1.entity.Message;
//import org.example.healthnexus1.entity.User;
//import org.example.healthnexus1.service.MessageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/messages")
//@CrossOrigin(origins = "*") // Allow frontend to call this
//public class MessageController {
//
//    @Autowired
//    private MessageService messageService;
//
//    @PostMapping("/send")
//    public Message sendMessage(@RequestBody Message message) {
//        return messageService.sendMessage(message);
//    }
//
//    @GetMapping("/appointment/{appointmentId}")
//    public List<Message> getMessagesByAppointment(@PathVariable Appointment appointmentId) {
//        return messageService.getMessagesByAppointment(appointmentId);
//    }
//
//    @GetMapping("/conversation")
//    public List<Message> getMessagesBetweenUsers(
//            @RequestParam User senderId,
//            @RequestParam User receiverId
//    ) {
//        return messageService.getMessagesBetweenUsers(senderId, receiverId);
//    }
//}