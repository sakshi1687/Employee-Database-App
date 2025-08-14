package com;
import java.sql.*;
import java.util.Scanner;
public class Emploee {
	 
	     // Database credentials
	     static final String DB_URL = "jdbc:mysql://localhost:3306/company";
	     static final String USER = "root";  // Change as per your MySQL setup
	     static final String PASS = "root";  // Change as per your MySQL setup

	     public static void main(String[] args) {
	    	
	         Scanner scanner = new Scanner(System.in);
	         try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
	             System.out.println("Connected to the database.");

	             while (true) {
	                 System.out.println("\nChoose an option:");
	                 System.out.println("1. Add Employee");
	                 System.out.println("2. View Employees");
	                 System.out.println("3. Update Employee");
	                 System.out.println("4. Delete Employee");
	                 System.out.println("5. Exit");
	                 System.out.print("Your choice: ");
	                 int choice = scanner.nextInt();
	                 scanner.nextLine(); // consume newline

	                 switch (choice) {
	                     case 1 -> addEmployee(conn, scanner);
	                     case 2 -> viewEmployees(conn);
	                     case 3 -> updateEmployee(conn, scanner);
	                     case 4 -> deleteEmployee(conn, scanner);
	                     case 5 -> {
	                         System.out.println("Exiting...");
	                         return;
	                     }
	                     default -> System.out.println("Invalid choice. Try again.");
	                 }
	             }

	         } catch (SQLException e) {
	             System.out.println("Database error: " + e.getMessage());
	         }
	         
	     }

	     // Add employee
	     private static void addEmployee(Connection conn, Scanner scanner) throws SQLException {
	         System.out.print("Enter name: ");
	         String name = scanner.nextLine();
	         System.out.print("Enter position: ");
	         String position = scanner.nextLine();
	         System.out.print("Enter salary: ");
	         double salary = scanner.nextDouble();
	         scanner.nextLine(); // consume newline

	         String sql = "INSERT INTO employees (name, position, salary) VALUES (?, ?, ?)";
	         try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	             stmt.setString(1, name);
	             stmt.setString(2, position);
	             stmt.setDouble(3, salary);
	             int rows = stmt.executeUpdate();
	             System.out.println(rows + " employee(s) added.");
	         }
	     }

	     // View employees
	     private static void viewEmployees(Connection conn) throws SQLException {
	         String sql = "SELECT * FROM employees";
	         try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	             ResultSet rs = stmt.executeQuery();
	             System.out.println("\n--- Employee List ---");
	             while (rs.next()) {
	                 System.out.printf("ID: %d, Name: %s, Position: %s, Salary: %.2f\n",
	                         rs.getInt("id"),
	                         rs.getString("name"),
	                         rs.getString("position"),
	                         rs.getDouble("salary"));
	             }
	         }
	     }

	     // Update employee
	     private static void updateEmployee(Connection conn, Scanner scanner) throws SQLException {
	         System.out.print("Enter employee ID to update: ");
	         int id = scanner.nextInt();
	         scanner.nextLine();
	         System.out.print("Enter new name: ");
	         String name = scanner.nextLine();
	         System.out.print("Enter new position: ");
	         String position = scanner.nextLine();
	         System.out.print("Enter new salary: ");
	         double salary = scanner.nextDouble();
	         scanner.nextLine();

	         String sql = "UPDATE employees SET name = ?, position = ?, salary = ? WHERE id = ?";
	         try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	             stmt.setString(1, name);
	             stmt.setString(2, position);
	             stmt.setDouble(3, salary);
	             stmt.setInt(4, id);
	             int rows = stmt.executeUpdate();
	             System.out.println(rows + " employee(s) updated.");
	         }
	     }

	     // Delete employee
	     private static void deleteEmployee(Connection conn, Scanner scanner) throws SQLException {
	         System.out.print("Enter employee ID to delete: ");
	         int id = scanner.nextInt();
	         scanner.nextLine();

	         String sql = "DELETE FROM employees WHERE id = ?";
	         try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	             stmt.setInt(1, id);
	             int rows = stmt.executeUpdate();
	             System.out.println(rows + " employee(s) deleted.");
	         }
	     }
	 }


