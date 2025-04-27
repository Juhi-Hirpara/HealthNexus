# HealthNexus
A full-stack healthcare platform where users can browse doctors by category, book appointments, make payments, and connect via voice call. Providers (doctors) can manage appointments and view analytics through their dashboard.

✨ Features

User Side (Patient):

🔐 JWT Authentication (Signup/Login)
🩺 Browse doctors by selecting specialty/category
📅 Book appointments and manage:
     -Upcoming Appointments
     -Cancelled Appointments
     -Rescheduled Appointments
💳 Make payment for appointments via Stripe Payment Gateway
📞 Voice call feature with doctors (integrated using WebSocket)

Provider Side (Doctor):

  -🔐 JWT Authentication (Signup/Login)
  -📥 Manage appointment requests:
     -Accept appointments
     -Decline appointments
  -📊 Dashboard with Analytics:
  -View number of Upcoming, Completed, and Cancelled appointments

🛠️ Tech Stack

  -Frontend: React.js
  -Backend: Spring Boot (Java)
  -Authentication: JWT (JSON Web Token)
  -Real-time Communication: WebSocket (for voice calls)
  -Payment Integration: Stripe
  -Database: MySQL 
