# ✈️ Airline Ticket Reservation System

![Java](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.3-brightgreen?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/Database-MySQL-orange?logo=mysql&logoColor=white)
![React](https://img.shields.io/badge/Frontend-React-blueviolet?logo=react&logoColor=white)
![Status](https://img.shields.io/badge/Status-In%20Progress-yellow)
![License](https://img.shields.io/badge/License-Academic-lightgrey)

---

## 📖 Project Overview

The **Web-based Airline Ticket Reservation System** is a full-stack Java web application built to simplify **flight booking and management** for both passengers and airline staff.  
It enables users to **book flights, select seats, make payments (simulated), submit complaints, and participate in loyalty programs** — all from one secure platform.

Developed for the **SE2030 – Software Engineering** module, this system focuses on **efficiency, security, and user experience**, ensuring that all travel operations can be handled digitally with minimal manual effort.

---

## ✨ Core Features

### 👤 Passenger & Staff Profiles
- Secure registration and login with encrypted passwords  
- Role-based access: Admin, Customer, Staff, Finance, Marketing  
- Update or delete profiles anytime  
- Email confirmation and password reset  

### 🛫 Flight Management
- Add, edit, delete, and view flight details  
- Search flights by destination, time, or availability  
- Maintain accurate flight schedules and availability  

### 🎟️ Booking Management
- Book flights with seat selection  
- View booking history and booking status  
- Edit or cancel bookings before payment  

### 💳 Payment System (Simulated)
- Process mock payments after booking  
- Generate booking confirmation with transaction record  
- Allow finance staff to manage and track payment data  

### 📩 Complaint Management
- Passengers can post and update complaints  
- Admin and staff can view, edit, and resolve issues  
- Ensures transparency and timely customer service  

### 🎁 Loyalty & Promotions
- Reward points system for frequent flyers  
- Seasonal promotions and discounts  
- Redeem points for ticket discounts  

---

## ⚙️ Non-Functional Highlights

| Attribute | Description |
|------------|-------------|
| 🔒 **Security** | Encrypted passwords, secure login, and session handling |
| 🎨 **Usability** | Clean, responsive, and intuitive UI |
| ⚡ **Performance** | Optimized data flow and fast search |
| 📈 **Scalability** | Supports more users, flights, and roles |
| 🔧 **Maintainability** | Modular and readable code structure |
| 🧩 **Reliability** | Handles consistent uptime during usage |

---

## 🧩 Stakeholders & Roles

| Role | Responsibilities |
|------|-------------------|
| **Customer (Passenger)** | Register, book flights, make payments, submit complaints, and join loyalty programs |
| **Travel Agent** | Assist in bookings and customer management |
| **Flight Manager** | Manage flight schedules and availability |
| **Admin** | Full system access for user, flight, and complaint management |
| **Finance Executive** | Verify and track payment transactions |
| **Marketing Manager** | Create and monitor loyalty and promotion campaigns |
| **Customer Service Officer** | Handle and resolve passenger complaints |

---

## 🛠️ Tech Stack

**Frontend:** React.js, HTML, CSS, JavaScript  
**Backend:** Java 17, Spring Boot 3.3  
**Database:** MySQL / MS SQL  
**Server:** Apache Tomcat  
**Build Tool:** Maven  
**Version Control:** Git & GitHub  

---

## 🧱 System Modules

1. **Authentication Module** – Login, logout, register, reset password  
2. **Flight Module** – CRUD operations for flight management  
3. **Booking Module** – Flight reservation and booking status tracking  
4. **Payment Module** – Simulated payment confirmation and invoice display  
5. **Complaint Module** – Complaint submission and resolution tracking  
6. **Loyalty Module** – Reward points, promotions, and offers  
7. **Admin Dashboard** – System overview, analytics, and data control  

---

## 🚀 How to Run Locally

### 🔧 Prerequisites
- Java 17+
- Node.js (for frontend)
- Maven
- MySQL
- IntelliJ IDEA / VS Code

### 🧭 Backend Setup
```bash
# Clone the repository
git clone https://github.com/<your-username>/AirlineReservationApp.git

# Configure database (application.properties)
spring.datasource.url=jdbc:mysql://localhost:3306/airline_db
spring.datasource.username=root
spring.datasource.password=yourpassword

# Run backend
mvn spring-boot:run
```

### 🎨 Frontend Setup
```bash
# Go to frontend folder
cd airline-frontend

# Install dependencies
npm install

# Run the app
npm start
```

Access frontend at 👉 `http://localhost:3000`  
Backend API runs at 👉 `http://localhost:8080`

---

## 🧪 Development Timeline

| Week | Task |
|------|------|
| 3 | Assign roles and finalize project plan |
| 4 | Requirement gathering & proposal |
| 5 | Choose tools and setup environment |
| 6 | Design login and registration UI |
| 7 | Develop flight search & booking pages |
| 8 | Implement authentication & sessions |
| 9 | Complete booking module & admin panel |
| 10 | Add payment simulation & booking history |
| 11 | Finish data management & testing |
| 12 | Fix bugs and refine UI |
| 13 | Prepare documentation & demo |
| 14 | Final submission and presentation |

---

## 📈 Future Enhancements

- 📱 Mobile app (Android/iOS)
- 🌍 Real airline API integration
- 💳 Real payment gateway (Stripe/PayPal)
- ☁️ Cloud hosting & CI/CD deployment
- 🧠 AI-based flight recommendation system

---

## 📷 Screenshots (Optional)

_Add screenshots of login page, flight search, booking page, and admin dashboard here._

---

## 📝 License

This project was created for the **SE2030 – Software Engineering** coursework.  
You may fork, modify, and experiment with it for academic or learning purposes.  
Please credit the original authors when sharing publicly.

---
