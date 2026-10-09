
package school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
	public static void main(String[] args) {
		String url = System.getenv("DB_URL");
		String user = System.getenv("DB_USER");
		String password = System.getenv("DB_PASSWORD");

		System.out.println("Connecting to Neon PostgreSQL...");

		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			if (conn != null) {
				System.out.println("Succes! Connected to the Database.");
			} else {
				System.out.println("Failed to make connection.");
			}
		} catch (SQLException e) {
			System.err.println("Connection error: " + e.getMessage());
		}

	}

}
