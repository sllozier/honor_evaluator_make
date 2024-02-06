package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 2
 * Date: February 6th, 2024
 * Description: Project2Test.java is dedicated to validating the functionality of the Project2
 * class, focusing on its ability to accurately read and process student data from a file,
 * compute average GPAs, set and display GPA thresholds for honor society eligibility, and
 * generate reports on students who meet these criteria. It ensures that the main application
 * logic is correctly implemented and that students are evaluated accurately according to
 * the specified requirements. This suite of tests aims to cover critical paths through the
 * Project2 processing logic, ensuring the system's reliability and correctness in handling
 * student records.
 */

import java.util.Arrays;
import java.util.List;
import main.Project2;
import main.Student;

public class Project2Test {

    public static void main(String[] args) {
        // Execute tests and report results
        boolean test1 = testReadStudentsFromFile();
        boolean test2 = testComputeAverageGpa();
        boolean test3 = testSetAndDisplayGpaThreshold();
        boolean test4 = testDisplayEligibleStudentsReport();

        // Output the results of each test
        System.out.println("Test 1: Read Students from File - " + (test1 ? "Passed" : "Failed"));
        System.out.println("Test 2: Compute Average GPA - " + (test2 ? "Passed" : "Failed"));
        System.out.println("Test 3: Set and Display GPA threshold - " + (test3 ? "Passed" : "Failed"));
        System.out.println("Test 4: Display Eligible Students Report - " + (test4 ? "Passed" : "Failed"));
    }

    // Test method for reading students from a file and verifying the data is loaded
    // correctly
    public static boolean testReadStudentsFromFile() {
        // File path for test data
        String filePath = "./docs/testStudents.txt";
        // Execute the method to read students from file
        Project2.readStudentsFromFile(filePath);

        // Verify that the test students list is not empty
        boolean passed = !Project2.getStudents().isEmpty();

        // Use Project2.getStudents() to get the list of test students
        List<Student> testStudents = Project2.getStudents();

        // Validate the data for each test student
        for (Student student : testStudents) {
            passed = passed && validateTestStudentData(student);
        }

        if (!passed) {
            System.out.println("Read test students from file test failed.");
        }

        // Return the result of the test
        return passed;
    }

    // Validates the correctness of each student's data loaded from file
    public static boolean validateTestStudentData(Student student) {
        // Initialize to true, assuming the test will pass
        boolean passed = true;
        // Extract and validate student attributes
        String name = student.getName();
        int creditHours = student.getCreditHours();
        int qualityPoints = student.getQualityPoints();
        String yearOrDegree = getYearOrDegree(student);

        // Split the name into lastName and firstName
        String[] nameParts = name.split(" ");
        if (nameParts.length < 2) {
            throw new AssertionError("Invalid name format in test student data: " + name);
        }
        String lastName = nameParts[0].trim();
        String firstName = nameParts[1].trim();

        // Validate the format of credit hours and quality points
        String creditHoursStr = String.valueOf(creditHours);
        String qualityPointsStr = String.valueOf(qualityPoints);
        if (!isValidNumber(creditHoursStr) || !isValidNumber(qualityPointsStr)) {
            throw new AssertionError("Invalid credit hours or quality points in test student data: " + name);
        }

        // Validate the format of the yearOrDegree
        if (!isValidYearOrDegree(yearOrDegree)) {
            throw new AssertionError("Invalid year or degree in test student data: " + name);
        }

        // Return the result of the test
        return passed;
    }

    // Extracts year or degree information from student's toString output
    public static String getYearOrDegree(Student student) {
        // Assuming last part of toString contains the required information
        String studentData = student.toString();
        String[] parts = studentData.split(" ");

        // Assuming the year or degree information is in the last part of the student
        // data
        String yearOrDegree = parts[parts.length - 1].trim();

        return yearOrDegree;
    }

    // Validates if the number string represents a valid number
    public static boolean isValidNumber(String str) {
        return str.matches("\\d+");
    }

    // Checks if the provided string matches any of the valid academic years or
    // degrees
    public static boolean isValidYearOrDegree(String str) {
        String[] validValues = { "Freshman", "Sophomore", "Junior", "Senior", "Masters" };
        return Arrays.asList(validValues).contains(str);
    }

    // Tests the computation of average GPA for loaded students
    public static boolean testComputeAverageGpa() {

        double averageGpa = Project2.computeAverageGpa(Project2.getStudents());

        // Assertions to check if the computed average GPA is correct
        boolean passed = averageGpa > 0 && averageGpa <= 4.0;

        if (!passed) {
            System.out.println("Compute average GPA test failed.");
        }

        // Return the result of the test
        return passed;
    }

    // Tests setting and displaying the GPA threshold for honor society eligibility
    public static boolean testSetAndDisplayGpaThreshold() {
        // Compute and set GPA threshold based on test data
        double averageGpa = Project2.computeAverageGpa(Project2.getStudents());
        double expectedThreshold = (averageGpa + 4.0) / 2;
        Student.setGpaThreshold(expectedThreshold);

        // Verify the threshold was set as expected
        boolean passed = Student.getGpaThreshold() == expectedThreshold;

        if (!passed) {
            System.out.println("Set and display GPA threshold test failed.");
            System.out.println("Expected GPA Threshold: " + String.format("%.2f", expectedThreshold));
            System.out.println("Actual GPA Threshold: " + String.format("%.2f", Student.getGpaThreshold()));
        }

        // Return the result of the test
        return passed;
    }

    // Tests generating and displaying a report of students eligible for the honor
    // society
    public static boolean testDisplayEligibleStudentsReport() {
        // Set a known GPA threshold to test eligibility
        double testThreshold = 3.0;
        Student.setGpaThreshold(testThreshold);

        // Call the method to get the list of eligible students
        List<Student> eligibleStudents = Project2.getEligibleStudents();

        // Check if all eligible students meet the GPA threshold
        boolean allEligibleStudentsMeetThreshold = eligibleStudents.stream()
                .allMatch(student -> student.gpa() >= testThreshold);

        // Check if there are no ineligible students below the GPA threshold
        boolean noIneligibleStudentsBelowThreshold = eligibleStudents.stream()
                .noneMatch(student -> student.gpa() < testThreshold);

        // Assert both conditions
        boolean passed = allEligibleStudentsMeetThreshold && noIneligibleStudentsBelowThreshold;

        if (!passed) {
            System.out.println("Display eligible students report test failed.");
        }

        // Return the result of the test
        return passed;
    }
}
