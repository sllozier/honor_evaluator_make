package test;

import java.util.Arrays;
import java.util.List;
import main.Project2;
import main.Student;

public class Project2Test {

    public static void main(String[] args) {
        boolean test1 = testReadStudentsFromFile();
        boolean test2 = testComputeAverageGpa();
        boolean test3 = testSetAndDisplayGpaThreshold();
        boolean test4 = testDisplayEligibleStudentsReport();

        System.out.println("Test 1: Read Students from File - " + (test1 ? "Passed" : "Failed"));
        System.out.println("Test 2: Compute Average GPA - " + (test2 ? "Passed" : "Failed"));
        System.out.println("Test 3: Set and Display GPA threshold - " + (test3 ? "Passed" : "Failed"));
        System.out.println("Test 4: Display Eligible Students Report - " + (test4 ? "Passed" : "Failed"));
    }

    public static boolean testReadStudentsFromFile() {

        String filePath = "./docs/testStudents.txt";

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

        return passed;
    }

    public static boolean validateTestStudentData(Student student) {
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
        // Return true if all validations pass
        return true;
    }

    public static String getYearOrDegree(Student student) {
        // You can access the year or degree information based on your student data
        // format
        String studentData = student.toString(); // Assuming toString provides the necessary information
        String[] parts = studentData.split(" ");

        // Assuming the year or degree information is in the last part of the student
        // data
        String yearOrDegree = parts[parts.length - 1].trim();

        return yearOrDegree;
    }

    public static boolean isValidNumber(String str) {
        return str.matches("\\d+");
    }

    public static boolean isValidYearOrDegree(String str) {
        String[] validValues = { "Freshman", "Sophomore", "Junior", "Senior", "Masters" };
        return Arrays.asList(validValues).contains(str);
    }

    public static boolean testComputeAverageGpa() {

        double averageGpa = Project2.computeAverageGpa(Project2.getStudents());

        // Assertions to check if the computed average GPA is correct
        boolean passed = averageGpa > 0 && averageGpa <= 4.0;

        if (!passed) {
            System.out.println("Compute average GPA test failed.");
        }

        return passed;
    }

    public static boolean testSetAndDisplayGpaThreshold() {

        double averageGpa = Project2.computeAverageGpa(Project2.getStudents());
        double expectedThreshold = (averageGpa + 4.0) / 2;
        Student.setGpaThreshold(expectedThreshold);

        // Assertions to verify that the GPA threshold is set correctly
        boolean passed = Student.getGpaThreshold() == expectedThreshold;

        if (!passed) {
            System.out.println("Set and display GPA threshold test failed.");
            System.out.println("Expected GPA Threshold: " + String.format("%.2f", expectedThreshold));
            System.out.println("Actual GPA Threshold: " + String.format("%.2f", Student.getGpaThreshold()));
        }

        return passed;
    }

    public static boolean testDisplayEligibleStudentsReport() {

        // Set a known GPA threshold, for example, 3.0
        double testThreshold = 3.0;
        Student.setGpaThreshold(testThreshold);

        // Call the method to get the list of eligible students
        List<Student> eligibleStudents = Project2.getEligibleStudents();

        // Now, you can write assertions to automatically verify the list

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

        return passed;
    }
}
