# Java JDBC – Employee Database App

A simple console-based Java app to perform CRUD operations on an Employee database using JDBC and MySQL.

## Features

- Connects to MySQL
- Add/View/Update/Delete employees
- Uses JDBC, PreparedStatement, and ResultSet

## How to Run

1. Import `mysql-connector-java.jar`
2. Compile: `javac -cp .:mysql-connector-java.jar EmployeeApp.java`
3. Run: `java -cp .:mysql-connector-java.jar EmployeeApp`

## DB Setup

```sql
CREATE DATABASE company;
USE company;
CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    position VARCHAR(100),
);
    salary DOUBLE
);
