# ✈️ Airline Ticket Reservation System

![Java](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.3-brightgreen?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/Database-MySQL-orange?logo=mysql&logoColor=white)
![React](https://img.shields.io/badge/Frontend-React-blueviolet?logo=react&logoColor=white)
![Status](https://img.shields.io/badge/Status-In%20Progress-yellow)
![License](https://img.shields.io/badge/License-Academic-lightgrey)

---

## 📖 Project Overview

The **Web-based Airline Ticket Reservation System** is a full-stack Java web application designed to simplify **flight booking and management** for passengers and airline staff.  
It enables users to **book flights, select seats, make payments (simulated), submit complaints, and participate in loyalty programs** — all from a single secure platform.

Developed for the **SE2030 – Software Engineering** module, this system prioritizes **efficiency, security, and user experience**, ensuring all travel operations are handled digitally with minimal manual intervention.

---

## ✨ Core Features

### 👤 Passenger & Staff Profiles
- Secure registration and login with encrypted passwords  
- Role-based access (Admin, Customer, Staff, Finance, Marketing)  
- Update or delete profiles anytime  
- Email confirmation and password reset  

### 🛫 Flight Management
- Add, edit, delete, and view flight details  
- Search flights by destination, time, or availability  
- Maintain accurate flight schedules  

### 🎟️ Booking Management
- Book flights with seat selection  
- View booking history and status  
- Edit or cancel bookings before payment  

### 💳 Payment System (Simulated)
- Process simulated payments post-booking  
- Generate booking confirmations and transaction IDs  
- Allow finance staff to manage and track payment records  

### 📩 Complaint Management
- Passengers can post and update complaints  
- Admins and staff can review, edit, and close issues  
- Enables transparent and efficient issue tracking  

### 🎁 Loyalty & Promotions
- Reward points for frequent flyers  
- Seasonal promotions and discounts  
- Redeem points for ticket discounts  

---

## ⚙️ Non-Functional Highlights

| Attribute | Description |
|------------|-------------|
| 🔒 **Security** | Secure login, JWT authentication, and encrypted credentials |
| 🎨 **Usability** | Clean, responsive, and user-friendly interface |
| ⚡ **Performance** | Optimized data flow and efficient API design |
| 📈 **Scalability** | Supports multiple concurrent users and flights |
| 🔧 **Maintainability** | Modular structure for easy debugging and upgrades |
| 🧩 **Reliability** | Ensures consistent and error-free performance |

---

## 🧩 Stakeholders & Roles

| Role | Responsibilities |
|------|-------------------|
| **Customer (Passenger)** | Register, search, book flights, make payments, submit complaints, join loyalty programs |
| **Travel Agent** | Assist in flight bookings and customer management |
| **Flight Manager** | Create and maintain flight schedules |
| **Admin** | Manage users, flights, and complaints |
| **Finance Executive** | Verify and track payment transactions |
| **Marketing Manager** | Manage loyalty programs and promotions |
| **Customer Service Officer** | Review and resolve customer complaints |

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

1. **Authentication Module** – Registration, login, logout, password reset  
2. **Flight Module** – CRUD operations for flight management  
3. **Booking Module** – Reservation handling, booking history, ticketing  
4. **Payment Module** – Simulated payments and invoice generation  
5. **Complaint Module** – Complaint submission and tracking  
6. **Loyalty Module** – Reward points, promotions, and offers  
7. **Admin Dashboard** – System-wide monitoring and analytics  

---

## 🚀 How to Run Locally

### 🔧 Prerequisites
- Java 17 or higher  
- Node.js (for React frontend)  
- Maven  
- MySQL Database  
- IntelliJ IDEA / VS Code  


### 🧭 Backend Setup
# Clone the repository
git clone https://github.com/<your-username>/AirlineReservationApp.git

# Configure database (in application.properties)
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=Airline_Reservation_DB;encrypt=false;trustServerCertificate=true
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect

# Run backend
mvn spring-boot:run


### Frontend Setup
# Navigate to frontend folder
cd airline-frontend

# Install dependencies
npm install

# Run the app
npm start


Access the frontend at 👉 http://localhost:3000
Backend API runs at 👉 http://localhost:8080


---

## 🕓 Version History

| Version | Description | Link |
|----------|--------------|------|
| 1.8.1 | User registration setup | [View](./docs/versions/v1.8.1.md) |
| 1.9.1 | User profile & authentication UI | [View](./docs/versions/v1.9.1.md) |
| 1.9.2 | Backend refactor & frontend integration | [View](./docs/versions/v1.9.2.md) |
| 1.9.3 | Data privacy & booking filtering | [View](./docs/versions/v1.9.3.md) |
| 1.9.5 | E-Ticket UI & booking workflow | [View](./docs/versions/v1.9.5.md) |
| 1.9.6 | Complaint Management System | [View](./docs/versions/v1.9.6.md) |

---





