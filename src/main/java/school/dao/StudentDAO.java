package school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import school.model.Student;

// Data Access Objects 
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

	//
	public List<Student> getAllStudents() {

		List<Student> students = new ArrayList<>();

		String sql = "SELECT id, name, email FROM students;";

		try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				students.add(new Student(id, name, email));
			}
		} catch (SQLException e) {
			System.err.println("Error fetching students: " + e.getMessage());
		}
		return students;
	}
}
