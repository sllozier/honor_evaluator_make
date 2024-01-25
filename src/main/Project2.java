package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Project2 {
    private static List<Student> students = new ArrayList<>();
    private static double totalGpa = 0.0;

    public static void main(String[] args) {
        String filePath = "secondProject/target/generated-sources/students.txt";
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

    public static void readStudentsFromFile(String filePath) {
        File file = new File(filePath);

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
                String name = nameParts[1].trim() + " " + nameParts[0].trim(); // firstName lastName
                int creditHours = Integer.parseInt(parts[1]);
                int qualityPoints = Integer.parseInt(parts[2]);
                String yearOrDegree = parts[3];

                Student student;
                if (yearOrDegree.equals("Junior") || yearOrDegree.equals("Senior")) {
                    student = new Undergraduate(name, creditHours, qualityPoints, yearOrDegree);
                } else {
                    student = new Graduate(name, creditHours, qualityPoints, yearOrDegree);
                }
                students.add(student);
                totalGpa += student.gpa(); // Add the student's GPA to the total
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading the file: " + filePath);
            e.printStackTrace();
        }
    }

    public static List<Student> getStudents() {
        return students;
    }

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

    public static void setAndDisplayGpaThreshold(double threshold) {
        Student.setGpaThreshold(threshold);
        System.out.println("\nGPA Threshold: " + String.format("%.2f", threshold) + "\n");
    }

    public static void addStudent(Student student) {
        students.add(student);
    }

    // Method to display the report of eligible students
    public static void displayEligibleStudentsReport() {
        System.out.println("Eligible Students:\n");
        for (Student student : students) {
            if (student.eligibleForHonorSociety()) {
                System.out.println(student.getName());
            }
        }
    }
}
