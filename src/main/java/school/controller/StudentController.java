package school.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import school.dao.StudentDAO;
import school.model.Student;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@PostMapping
	public ResponseEntity<String> createStudent(@RequestBody StudentPayload payload) {
		String url = System.getenv("DB_URL");
		String user = System.getenv("DB_USER");
		String password = System.getenv("DB_PASSWORD");

		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			StudentDAO dao = new StudentDAO(conn);
			dao.addStudent(payload.getName(), payload.getEmail());

			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Student " + payload.getName() + " added successfully!");

		} catch (SQLException e) {
			if ("23505".equals(e.getSQLState())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email already exists.");
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error: " + e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<List<Student>> getStudents() {
		return StudentService.fetchAllStudents();
	}
}

class StudentPayload {
	private String name;
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}