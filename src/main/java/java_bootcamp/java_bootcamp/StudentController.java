package java_bootcamp.java_bootcamp;

import org.springframework.web.bind.annotation.*;
import school.dao.StudentDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @PostMapping
    public String createStudent(@RequestBody StudentPayload payload) {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            StudentDAO studentDAO = new StudentDAO(conn);
            studentDAO.addStudent(payload.getName(), payload.getEmail());
            return "Student " + payload.getName() + " added successfully!";
        } catch (SQLException e) {
            return "Database error: " + e.getMessage();
        }
    }
}

// Helper class to map incoming JSON body from Postman
class StudentPayload {
    private String name;
    private String email;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}