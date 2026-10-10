package java_bootcamp.java_bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "java_bootcamp.java_bootcamp", "school" })
public class JavaBootcampApplication {
	public static void main(String[] args) {
		SpringApplication.run(JavaBootcampApplication.class, args);
	}
}