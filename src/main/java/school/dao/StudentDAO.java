package school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO {
	private Connection connection;

	public StudentDAO(Connection connection) {
		this.connection = connection;
	}

	public void addStudent(String name, String email) {
		String sql = "INSERT INTO students (name, email) VALUES (?, ?)";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.executeUpdate();
			System.out.println("Student " + name + " with email " + email + " added successfully!");
		} catch (SQLException e) {
			System.err.println("Error inserting student: " + e.getMessage());
		}
	}
}
