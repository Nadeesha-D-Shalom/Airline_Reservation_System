✈️ Web-based Airline Ticket Reservation System
📌 Overview

The Airline Ticket Reservation System is a Java-based web application built to simplify airline bookings for both passengers and staff. It allows users to search flights, select seats, manage bookings, make (simulated) payments, submit complaints, and participate in loyalty programs.

The system reduces manual work for staff while ensuring a secure, efficient, and user-friendly experience for travelers.

👥 Team Information

Group ID: 2025-Y2-S1-MLB-B9G1-01
Module Code: SE2030 – Software Engineering

Name	Student ID
Chedima Imashi K.H.	IT24102218
Palliyage D.S.R.	IT24102226
Dias M.B.N.S.	IT24102244
Pataka Ralalage A.N.H.	IT24102321
Vidushan K.G.A.D.	IT24102189
Pehesara A.D.	IT24102305
⚙️ Features Implemented
🔹 Passenger & Staff Profiles

Register and manage accounts (CRUD).

Update personal details like contact, passport number.

Secure login/logout with password encryption.

🔹 Flight Management

Add, update, delete, and view flights.

Search available flights with filters (time, cost, destination).

🔹 Booking Management

Book flights with seat selection.

View booking history and details.

Update or cancel bookings before payment.

🔹 Payment (Simulation)

Record and confirm payments (simulated, not real gateway).

Track payment status for bookings.

Integrated with booking confirmation.

🔹 Complaint Management

Passengers submit, edit, and track complaints.

Staff resolves issues and updates complaint status.

🔹 Marketing & Loyalty Programs

Add promotions, discounts, and loyalty points.

Customers can redeem points for offers.

Seasonal promotions managed by Marketing team.

🔐 Non-Functional Features

Security: Encrypted login & session handling.

Performance: Fast booking and search flow.

Scalability: Designed to handle more users/flights.

Usability: Clean, user-friendly UI.

Maintainability: Modular code structure for easy updates.

🛠️ Tech Stack

Backend: Java, Spring Boot

Frontend: HTML, CSS, JavaScript (Vanilla / Bootstrap for UI)

Database: MS SQL / MySQL (depending on environment)

Server: Apache Tomcat (Local Deployment)

📌 Updates & Contributions

During development, we added and improved the following:

✅ Flight CRUD Operations – implemented full Create/Read/Update/Delete for flights.

✅ Booking Status Page – now shows confirmed/cancelled bookings.

✅ Edit/Cancel Booking Before Payment – users can update details or cancel before proceeding.

✅ Payment UI – added payment calculation panel (right side) with booking details in the middle.

✅ Complaint Module – complete CRUD for complaints.

✅ Admin Dashboard – manage flights, bookings, users, and complaints.

✅ Loyalty Program Module – integrated reward points & promotions.

🚀 How to Run

Clone the repository:

git clone https://github.com/<your-username>/AirlineReservationApp.git


Open in IntelliJ IDEA / Eclipse.

Configure database connection in application.properties.

Run the project with Spring Boot or deploy on Tomcat.

Access the app via:

http://localhost:8080

📌 Future Enhancements

Mobile app support (Android/iOS).

Real payment gateway integration.

Real-time airline API for live flight data.

Cloud hosting for production use.

📷 Screenshots

(Add screenshots of Login, Flight Search, Booking, and Admin Dashboard here.)

📝 License

This project was developed as part of SE2030 – Software Engineering coursework.
Feel free to fork and experiment, but please credit the original authors.
