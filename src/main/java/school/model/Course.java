package school.model;

import java.util.List;

public class Course {
	private int id;
	private String title;
	private List<Teacher> teachers;
	private List<Student> students;

	// Constructor
	public Course(int id, String title, List<Teacher> teachers, List<Student> students) {
		this.id = id;
		this.title = title;
		this.teachers = teachers;
		this.students = students;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
}
