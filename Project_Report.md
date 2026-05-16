# Bank Management System Project Report

## 1. Overview
This is a robust and modular Bank Management System built using Core Java, Java Swing for the GUI, and SQLite for persistent storage. It follows the MVC architecture, utilizes the DAO pattern for database interactions, and features separate dashboards for Admins and Customers.

## 2. UML Diagrams

### Use Case Diagram
```mermaid
flowchart LR
    A((Admin)) --> UC1(Login)
    A --> UC2(Create Customer Account)
    A --> UC3(View Customers)
    
    C((Customer)) --> UC1
    C --> UC4(View Balance)
    C --> UC5(Deposit Funds)
    C --> UC6(Withdraw Funds)
    C --> UC7(Transfer Funds)
    C --> UC8(View Transaction History)
```

### Class Diagram
```mermaid
classDiagram
    class User {
        -int id
        -String username
        -String password
        -String role
        +getters()
        +setters()
    }
    
    class Customer {
        -int id
        -int userId
        -String name
        -String dob
        -String address
        -String phone
        -String email
    }
    
    class Account {
        -int accountId
        -int customerId
        -String type
        -double balance
    }
    
    class Transaction {
        -int txnId
        -int accountId
        -String type
        -double amount
        -String dateTime
    }
    
    User "1" -- "1" Customer : mapped to
    Customer "1" -- "*" Account : owns
    Account "1" -- "*" Transaction : generates
```

### Sequence Diagram: Deposit Funds
```mermaid
sequenceDiagram
    actor C as Customer
    participant UI as CustomerDashboard
    participant BS as BankService
    participant AD as AccountDAO
    participant TD as TransactionDAO
    participant DB as SQLite Database
    
    C->>UI: Enter amount & click Deposit
    UI->>BS: deposit(accountId, amount)
    BS->>AD: getAccount(accountId)
    AD->>DB: SELECT query
    DB-->>AD: Account data
    AD-->>BS: Account object
    BS->>AD: updateBalance(accountId, newBalance)
    AD->>DB: UPDATE query
    DB-->>AD: success
    BS->>TD: addTransaction(tx)
    TD->>DB: INSERT query
    DB-->>TD: success
    BS-->>UI: true (success)
    UI-->>C: Show Success Message & Update Balance
```

## 3. Database Schema
The database uses SQLite and is initialized automatically.
- **users**: `id` (PK), `username` (UNIQUE), `password`, `role`
- **customers**: `id` (PK), `user_id` (FK), `name`, `dob`, `address`, `phone`, `email`
- **accounts**: `account_id` (PK), `customer_id` (FK), `type`, `balance`
- **transactions**: `txn_id` (PK), `account_id` (FK), `type`, `amount`, `date_time`

## 4. Deployment Instructions

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- Apache Maven (Optional, for building from command line)
- An IDE (IntelliJ IDEA, Eclipse, or VS Code)

### Building the Executable JAR
If you have Maven installed, open your terminal in the project directory (`Bank Management System`) and run:
```bash
mvn clean package
```
This downloads dependencies (SQLite JDBC, SLF4J, JUnit), compiles the code, runs tests, and packages an executable JAR file in the `target` folder.

### Running the Application
**Option A: Using the Executable JAR**
After packaging, run:
```bash
java -jar target/BankManagementSystem-1.0-SNAPSHOT.jar
```

**Option B: Using an IDE**
1. Import the `Bank Management System` folder as a Maven Project into your IDE.
2. Wait for dependencies to resolve.
3. Locate `src/main/java/com/bankmanagement/ui/MainApp.java` and run it.

### Testing
Unit tests for the service layer are included in `src/test/java/...`.
Run them using Maven:
```bash
mvn test
```

### Default Credentials
Upon the first run, the SQLite database (`bank.db`) is automatically created, and a default admin is inserted.
- **Username:** `admin`
- **Password:** `admin123`
