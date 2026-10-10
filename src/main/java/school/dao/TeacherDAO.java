package school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeacherDAO {
	private Connection connection;

	public TeacherDAO(Connection connection) {
		this.connection = connection;
	}

	public void addTeacher(String name, String email) {
		String sql = "INSERT INTO teachers (name, email) VALUES (?, ?)";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.executeUpdate();
			System.out.println("Teacher " + name + " with email " + email + " added successfully!");
		} catch (SQLException e) {
			System.err.println("Error inserting teacher: " + e.getMessage());
		}
	}
}
