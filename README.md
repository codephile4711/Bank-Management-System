🏦 Bank Management System

A full-fledged, robust Bank Management System built using Core Java, Java Swing, and SQLite. This project implements the **Model-View-Controller (MVC)** architecture and uses the **Data Access Object (DAO)** pattern for seamless database interactions. 

✨ Features

👨‍💼 Admin Dashboard
* **Customer Management**: Create new customers and assign them Savings or Current accounts securely.
* **View All Customers**: A comprehensive grid displaying all registered customers and their contact details.
* **Audit Trail**: Every action performed by an Admin (like account creations) is permanently logged with timestamps for security.
* **Database Backup**: One-click functionality to safely back up the entire `bank.db` SQLite database locally.

## 👥 Customer Dashboard
* **Real-Time Overview**: View current account balances across multiple accounts (Savings/Current) in an intuitive dropdown.
* **Financial Operations**: Perform instant Deposits, Withdrawals, and inner-bank Transfers.
* **Transaction History**: View all past transactions (amount, type, and date/time) in a structured table.
* **Export to CSV**: Easily export transaction histories into an Excel-compatible `.csv` format.

## 🛠️ Technology Stack
* **Language**: Java (JDK 17+)
* **GUI Framework**: Java Swing
* **Database**: SQLite (via `sqlite-jdbc`)
* **Logging**: SLF4J
* **Testing**: JUnit 5
* **Build Tool**: Apache Maven

## 🚀 Getting Started

### Prerequisites
Make sure you have **Java 17** (or newer) and **Maven** installed on your machine.

### Installation & Run

**Option 1: Using the provided scripts (Windows)**
1. Clone the repository.
2. Double click the `build_and_run.ps1` script to automatically download Maven, build the project, and run it.
3. For subsequent runs, you can just double click `run.bat`.

**Option 2: Using Maven CLI**
1. Open a terminal in the project root directory.
2. Compile and package the application:
   ```bash
   mvn clean package
   ```
3. Run the executable JAR:
   ```bash
   java -jar target/BankManagementSystem-1.0-SNAPSHOT.jar
   ```

### 🔑 Default Login Credentials
The application will automatically initialize the database on the first run and create a default admin account.
* **Role**: Admin
* **Username**: `admin`
* **Password**: `admin123`

