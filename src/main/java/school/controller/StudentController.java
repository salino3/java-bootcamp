package school.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import school.dao.StudentDAO;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@PostMapping
	public ResponseEntity<String> createStudent(@RequestBody StudentPayload payload) {
		String url = System.getenv("DB_URL");
		String user = System.getenv("DB_USER");
		String password = System.getenv("DB_PASSWORD");

		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			StudentDAO studentDAO = new StudentDAO(conn);
			studentDAO.addStudent(payload.getName(), payload.getEmail());

			// This line ONLY executes if addStudent succeeds without throwing an exception
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Student " + payload.getName() + " added successfully!");

		} catch (SQLException e) {
			// If a database error occurs, execution jumps straight here,
			// bypassing the success return above completely.
			if ("23505".equals(e.getSQLState())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email already exists.");
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error: " + e.getMessage());
		}
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