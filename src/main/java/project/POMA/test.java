package project.POMA;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class test {
	Set<Student> students = new HashSet<Student>();

	public static void main(String[] args) {
		test app = new test();
		app.students.add(new Student("Vlad", "umkc", new HashSet<String>(Arrays.asList("A", "F", "C"))));
		app.students.add(new Student("Bob", "umkc", new HashSet<String>(Arrays.asList("A", "C"))));
		app.students.add(new Student("Nick", "umkc", new HashSet<String>(Arrays.asList("A", "F"))));

		System.out.println(app.existsInSet());
		System.out.println(app.filterSet());

	}

	// 1st goal
	private String existsInSet() {
		for (Student s : students) {
			if (s.name.equals("Vlad") && s.school.equals("umkc") && s.grades.contains("F")) {
				return "sat";
			}
		}
		return "unsat";
	}

	// 2nd goal
	private Set<Student> filterSet() {
		Set<Student> output = new HashSet<Student>();
		for (Student s : students) {
			if (s.school.equals("umkc") && s.grades.contains("F")) {
				output.add(s);
			}
		}
		return output;
	}

	private static class Student {
		String name;
		String school;
		Set<String> grades;

		public Student(String name, String school, Set<String> grades) {
			this.name = name;
			this.school = school;
			this.grades = grades;
		}

		@Override
		public String toString() {
			return name + " " + school + " " + grades;
		}
	}
}
