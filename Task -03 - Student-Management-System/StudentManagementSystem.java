/**CodSoft Java Internship
Task - 03 : Student Management System**/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Student {
  private String name;
  private int id;
  private String course;

  public Student(String name, int id, String course) {
    this.name = name;
    this.id = id;
    this.course = course;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  @Override
  public String toString() {
    return "Name: " + name + ", ID: " + id + ", Course: " + course;
  }
}

public class StudentManagementSystem {
  private static ArrayList<Student> students = new ArrayList<>();
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    loadData(); // Load data from a file

    while (true) {
      try {
        System.out.println("\n1. Add Student");
        System.out.println("2. Update Student Details");
        System.out.println("3. Remove Student");
        System.out.println("4. View Students");
        System.out.println("5. Search Student");
        System.out.println("6. Sort Students");
        System.out.println("7. Save Data to File");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();

        switch (choice) {
          case 1:
            addStudent();
            break;
          case 2:
            updateStudent();
            break;
          case 3:
            removeStudent();
            break;
          case 4:
            viewStudents();
            break;
          case 5:
            searchStudent();
            break;
          case 6:
            sortStudents();
            break;
          case 7:
            saveData();
            break;
          case 8:
            System.out.println("Exiting program. Goodbye!");
            saveData(); // Save data before exiting
            System.exit(0);
          default:
            System.out.println("Invalid choice. Please enter a valid option.");
        }
      } catch (java.util.InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid integer for your choice.");
        scanner.nextLine(); // Consume the invalid input to prevent an infinite loop
      }
    }
  }

  private static void addStudent() {
    try {
      System.out.print("Enter student name: ");
      String name = scanner.next();

      System.out.print("Enter student ID: ");
      int id = scanner.nextInt();

      System.out.print("Enter student course: ");
      String course = scanner.next();
      scanner.nextLine(); // Consume the newline character

      Student newStudent = new Student(name, id, course);
      students.add(newStudent);
      System.out.println("Student added successfully!");
    } catch (java.util.InputMismatchException e) {
      System.out.println("Invalid input. Please enter valid values for the student ID and course.");
      scanner.nextLine(); // Consume the invalid input to prevent an infinite loop
    }
  }

  private static void updateStudent() {
    // Implementation for updating student details
    System.out.print("Enter student ID to update: ");
    int id = scanner.nextInt();

    Student studentToUpdate = findStudentById(id);

    if (studentToUpdate != null) {
      System.out.print("Enter new course for student: ");
      String newCourse = scanner.next();
      studentToUpdate.setCourse(newCourse);
      System.out.println("Student details updated successfully!");
    } else {
      System.out.println("Student with ID " + id + " not found.");
    }
  }

  private static void removeStudent() {
    // Implementation for removing a student
    System.out.print("Enter student ID to remove: ");
    int id = scanner.nextInt();

    Student studentToRemove = findStudentById(id);

    if (studentToRemove != null) {
      students.remove(studentToRemove);
      System.out.println("Student removed successfully!");
    } else {
      System.out.println("Student with ID " + id + " not found.");
    }
  }

  private static void viewStudents() {
    // Implementation for viewing students
    if (students.isEmpty()) {
      System.out.println("No students found.");
    } else {
      System.out.println("Student List:");
      for (Student student : students) {
        System.out.println(student);
      }
    }
  }

  private static void searchStudent() {
    // Implementation for searching a student
    System.out.print("Enter student ID to search: ");
    int id = scanner.nextInt();

    Student foundStudent = findStudentById(id);

    if (foundStudent != null) {
      System.out.println("Student found: " + foundStudent);
    } else {
      System.out.println("Student with ID " + id + " not found.");
    }
  }

  private static void sortStudents() {
    // Implementation for sorting students
    System.out.println("1. Sort by Name");
    System.out.println("2. Sort by ID");
    System.out.print("Enter your choice: ");

    int sortChoice = scanner.nextInt();

    switch (sortChoice) {
      case 1:
        Collections.sort(students, Comparator.comparing(Student::getName));
        System.out.println("Students sorted by name.");
        break;
      case 2:
        Collections.sort(students, Comparator.comparingInt(Student::getId));
        System.out.println("Students sorted by ID.");
        break;
      default:
        System.out.println("Invalid choice. Students will not be sorted.");
    }
  }

  private static void saveData() {
    // Implementation for saving data to a file
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
      oos.writeObject(students);
      System.out.println("Data saved successfully.");
    } catch (IOException e) {
      System.out.println("Error saving data: " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private static void loadData() {
    // Implementation for loading data from a file
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"))) {
      students = (ArrayList<Student>) ois.readObject();
      System.out.println("Data loaded successfully.");
    } catch (IOException | ClassNotFoundException e) {
      System.out.println("No previous data found. Starting with an empty list.");
    }
  }

  private static Student findStudentById(int id) {
    // Implementation for finding a student by ID
    for (Student student : students) {
      if (student.getId() == id) {
        return student;
      }
    }
    return null;
  }
}
