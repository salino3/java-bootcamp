package school.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import school.dao.StudentDAO;
import school.model.Student;

public class StudentService {

	public static ResponseEntity<List<Student>> fetchAllStudents() {
		String url = System.getenv("DB_URL");
		String user = System.getenv("DB_USER");
		String password = System.getenv("DB_PASSWORD");

		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			StudentDAO dao = new StudentDAO(conn);
			List<Student> students = dao.getAllStudents();
			return ResponseEntity.ok(students);
		} catch (SQLException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
