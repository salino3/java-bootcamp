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
	private static String url = System.getenv("DB_URL");
	private static String user = System.getenv("DB_USER");
	private static String password = System.getenv("DB_PASSWORD");

	public static ResponseEntity<List<Student>> fetchAllStudents() {

		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			StudentDAO dao = new StudentDAO(conn);
			List<Student> students = dao.getAllStudents();
			return ResponseEntity.ok(students);
		} catch (SQLException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	public static ResponseEntity<Student> fetchStudentByID(String id) {
		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			StudentDAO dao = new StudentDAO(conn);
			Student student = dao.getStudentByID(id);

			if (student != null) {
				return ResponseEntity.ok(student);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
		} catch (SQLException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
