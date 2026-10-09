package school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) {
		System.out.println("Starting School Application...");

		String url = System.getenv("DB_URL");
		String user = System.getenv("DB_USER");
		String password = System.getenv("DB_PASSWORD");

		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			if (conn != null) {
				System.out.println("Success! Connected to Neon PostgreSQL database.");
			}
		} catch (SQLException e) {
			System.err.println("Connection error: " + e.getMessage());
		}
	}

}
