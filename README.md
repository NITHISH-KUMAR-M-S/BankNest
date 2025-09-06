🚀 BankNest – Mini Banking Web App 💻🏦

BankNest is a basic banking web application built using Java (JSP, Servlets, JDBC, MVC).
It was developed as part of my Java Full Stack training at Pentagon Space to practice real-world full-stack concepts.

🛠️ Features

🔑 User Authentication – Sign Up / Login

💰 Transactions – Deposit & Withdraw money

👀 View Balance – Check account balance

📝 Profile Management – Update user details

📜 Transaction History – Track deposits & withdrawals

🔧 Tech Stack

Frontend: JSP, HTML, CSS

Backend: Java Servlets (Java EE)

Architecture: MVC Pattern

Database: MySQL (via JDBC)

Server: Apache Tomcat

📂 Project Structure
BankNest/
 ├── src/main/java/com/bank/controller/   # Servlets (Login, Signup, Deposit, Withdraw, etc.)
 ├── src/main/java/com/bank/model/        # DBConnection & DBInteraction
 ├── src/main/java/com/bank/DTO/          # Bank DTO class
 ├── src/main/webapp/                     # JSP & HTML pages
 │   ├── login.jsp, signup.jsp, dashboard.jsp, profile.jsp, transactions.jsp
 │   ├── WEB-INF/web.xml                   # Deployment descriptor
 │   └── lib/                              # MySQL connector & servlet jars

🚀 Getting Started
1️⃣ Clone the repo
git clone https://github.com/NITHISH-KUMAR-M-S/BankNest.git

2️⃣ Import Project

Open Eclipse / IntelliJ

Import as a Dynamic Web Project (Maven not required, libs already included)

3️⃣ Setup Database
CREATE DATABASE banknest;
USE banknest;

-- Example table (adjust as per your schema)
CREATE TABLE users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  password VARCHAR(100),
  balance DECIMAL(10,2) DEFAULT 0
);

CREATE TABLE transactions (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT,
  type VARCHAR(20),
  amount DECIMAL(10,2),
  date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


Update your DB credentials in:
src/main/java/com/bank/model/DBConnection.java

4️⃣ Run on Tomcat

Deploy project to Apache Tomcat

Start server and access app at:

http://localhost:8080/BankNest

📚 Key Learnings

Building dynamic JSP pages

Secure session management

Performing CRUD operations with JDBC

Structuring projects with MVC architecture

🤝 Acknowledgments

Special thanks to Pentagon Space for guidance during my Java Full Stack training.

📌 Tags

Java JSP Servlets MVC MySQL Banking App Mini Project
