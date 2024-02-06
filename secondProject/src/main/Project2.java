package main;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 2
 * Date: February 6th, 2024
 * Description: The Project2.java class serves as the main driver for a student management system
 * designed to read, process, and evaluate student records for honor society eligibility. It
 * utilizes a static list to store student objects, which can be either Undergraduate or Graduate
 * instances, populated from a text file containing student details. The program calculates the
 * average GPA of all students, sets a GPA threshold for honor society eligibility, and
 * identifies students who meet this criterion. It showcases object-oriented programming
 * principles, such as inheritance and polymorphism, by extending a base Student class to
 * handle specific student types differently. The program emphasizes error handling and data
 * validation during file reading and student object creation, ensuring robustness and
 * reliability in processing student records.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Main class for managing student data and processing honor society eligibility
public class Project2 {
    // List to store all students read from file
    private static List<Student> students = new ArrayList<>();
    // Accumulator for total GPA to compute average
    private static double totalGpa = 0.0;

    // Main method to drive the program
    public static void main(String[] args) {
        // Path to the student data file
        String filePath = "./docs/students.txt";

        // Read students from the file
        readStudentsFromFile(filePath);

        // Calculate the average GPA using the new method
        double averageGpa = computeAverageGpa(students);

        // Compute the GPA threshold and use the method to set and display it
        double gpaThreshold = (averageGpa + 4.0) / 2;
        setAndDisplayGpaThreshold(gpaThreshold);

        // Display the report of students eligible for the honor society
        displayEligibleStudentsReport();

    }

    // Reads student data from a file and populates the students list
    public static void readStudentsFromFile(String filePath) {
        File file = new File(filePath);

        // Checks if the file exists before proceeding
        if (!file.exists()) {
            System.err.println("Error: File " + filePath + " does not exist.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Split the line into parts
                String[] parts = line.split(" ");
                if (parts.length < 4) {
                    // Skip lines that don't have enough parts
                    continue;
                }

                // Split the name into lastName and firstName
                String[] nameParts = parts[0].split(",");
                if (nameParts.length < 2) {
                    // Skip if name format is not as expected
                    continue;
                }
                // firstName lastName
                String name = nameParts[1].trim() + " " + nameParts[0].trim();
                // Parses credit hours and quality points
                int creditHours = Integer.parseInt(parts[1]);
                int qualityPoints = Integer.parseInt(parts[2]);
                // Determines student year or degree
                String yearOrDegree = parts[3];

                // Creates appropriate student object based on year or degree
                Student student;
                if (yearOrDegree.equals("Junior") || yearOrDegree.equals("Senior")) {
                    student = new Undergraduate(name, creditHours, qualityPoints, yearOrDegree);
                } else {
                    student = new Graduate(name, creditHours, qualityPoints, yearOrDegree);
                }
                // Adds to list
                students.add(student);
                // Add the student's GPA to the total
                totalGpa += student.gpa();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading the file: " + filePath);
            e.printStackTrace();
        }
    }

    // Returns the list of students
    public static List<Student> getStudents() {
        return students;
    }

    // Computes the average GPA of all students
    public static double computeAverageGpa(List<Student> students) {
        if (students.isEmpty()) {
            return 0.0;
        }

        double totalGpa = 0.0;
        for (Student student : students) {
            totalGpa += student.gpa();
        }
        return totalGpa / students.size();
    }

    // Sets and displays the GPA threshold for honor society eligibility
    public static void setAndDisplayGpaThreshold(double threshold) {
        Student.setGpaThreshold(threshold);
        System.out.println("\nGPA Threshold: " + String.format("%.2f", threshold) + "\n");
    }

    // Adds a single student to the list
    public static void addStudent(Student student) {
        students.add(student);
    }

    // Method to get the list of eligible students
    // Added to help with testing
    public static List<Student> getEligibleStudents() {
        List<Student> eligibleStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.eligibleForHonorSociety()) {
                eligibleStudents.add(student);
            }
        }
        return eligibleStudents;
    }

    // Method to display the report of eligible students
    public static void displayEligibleStudentsReport() {
        System.out.println("Eligible Students:\n");

        List<Student> eligibleStudents = getEligibleStudents();

        for (Student student : eligibleStudents) {
            System.out.println(student.getName());
        }
    }

}
