# ✈️ Airline Ticket Reservation System

![Java](https://img.shields.io/badge/Java-17-blue?logo=java\&logoColor=white)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.0-brightgreen?logo=springboot\&logoColor=white)
![MySQL](https://img.shields.io/badge/Database-MySQL-orange?logo=mysql\&logoColor=white)
![Status](https://img.shields.io/badge/Status-In%20Progress-yellow)
![License](https://img.shields.io/badge/License-Academic-lightgrey)

---

## 📖 Project Overview

The **Airline Ticket Reservation System** is a full-stack Java web application developed as part of the **SE2030 – Software Engineering** module.

It simplifies flight booking for passengers while helping staff manage flights, payments, complaints, and loyalty programs.
The system is **secure, efficient, and user-friendly**, designed to reduce manual work for airline staff and improve the overall passenger experience.

---

## ✨ Key Features

### 👤 Passenger & Staff Profiles

* Secure login/logout with encrypted passwords
* Register, update, and manage user details
* Role-based access (Admin, Customer, Staff, Finance, Marketing)

### 🛫 Flight Management

* Add, edit, delete, and view flights
* Search flights by destination, time, or availability

### 🎟️ Booking Management

* Book flights with seat selection
* View booking history
* Edit or cancel bookings before payment

### 💳 Payment System (Simulation)

* Record and confirm payments (simulated, no real gateway)
* Track payment status linked to bookings

### 📩 Complaint Management

* Passengers can submit complaints
* Staff can update and resolve issues

### 🎁 Marketing & Loyalty

* Loyalty program with reward points
* Seasonal promotions and discounts
* Redeem points for offers

---

## ⚙️ Non-Functional Highlights

* 🔒 **Security**: Encrypted authentication & session handling
* ⚡ **Performance**: Fast search & booking process
* 📈 **Scalability**: Supports growing users and flights
* 🎨 **Usability**: Clean, intuitive user interface
* 🛠️ **Maintainability**: Well-structured modular code

---

## 🛠️ Tech Stack

* **Backend:** Java, Spring Boot
* **Frontend:** HTML, CSS, JavaScript
* **Database:** MySQL / MS SQL
* **Server:** Apache Tomcat

---

## 📌 Updates & Improvements

✅ Flight CRUD operations implemented
✅ Booking status page with confirmation/cancellation
✅ Edit/Cancel bookings before payment
✅ Payment UI with cost breakdown & booking details
✅ Complaint module with full CRUD
✅ Admin dashboard for flights, users, and complaints
✅ Loyalty & promotions module

---

## 🚀 Getting Started

### 🔧 Prerequisites

* Java 17+
* Maven / Gradle
* MySQL (or MS SQL)
* IntelliJ IDEA / Eclipse

### ▶️ Run Locally

```bash
# Clone repository
git clone https://github.com/<your-username>/AirlineReservationApp.git

# Open in IDE and configure DB in application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/airline_db
spring.datasource.username=root
spring.datasource.password=yourpassword

# Run with Spring Boot
mvn spring-boot:run
```

Access at 👉 `http://localhost:8080`

---

## 📈 Future Enhancements

* 📱 Mobile application (Android/iOS)
* 🌍 Real-time airline API integration
* 💳 Real payment gateway support
* ☁️ Cloud hosting & CI/CD

---

## 📷 Screenshots (Optional)

*Add login page, flight search, booking page, and admin dashboard screenshots here.*

---

## 📝 License

This project was developed as part of **SE2030 – Software Engineering** coursework.
Feel free to fork and experiment, but please credit the original authors.

---

👉 Nadeesha, do you also want me to **design a `project banner image`** (with your project name, logo, and theme colors) to place at the very top of the README? That makes repos look even more professional.
