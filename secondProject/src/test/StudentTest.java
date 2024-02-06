package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 2
 * Date: February 6th, 2024
 * Description: StudentTest.java conducts a series of unit tests on the Student class to ensure
 * correct functionality across its constructor, GPA calculation, and honor society eligibility
 * methods. It validates both positive scenarios (correct inputs) and negative scenarios
 * (inputs that should trigger exceptions or fail eligibility criteria).
 */

import main.Student;

public class StudentTest {

    public static void main(String[] args) {
        // Execute tests and report results
        boolean test1 = testStudentCreation();
        boolean test2 = testGpaCalculation();
        boolean test3 = testEligibilityForHonorSociety();

        // Output the results of each test
        System.out.println("Test 1: Student Creation - " + (test1 ? "Passed" : "Failed"));
        System.out.println("Test 2: Student GPA Calculation - " + (test2 ? "Passed" : "Failed"));
        System.out.println("Test 3: Student Eligible for Honor Society - " + (test3 ? "Passed" : "Failed"));
    }

    // Tests the student's object creation with valid and invalid inputs
    public static boolean testStudentCreation() {

        // Initialize to true, assuming the test will pass
        boolean passed = true;

        // Test valid student creation
        Student student = new Student("Emilia", 30, 120);
        if (!"Emilia".equals(student.getName())) {
            System.out.println("Name not set correctly");
            passed = false;
        }
        if (student.getCreditHours() != 30) {
            System.out.println("Credit hours not set correctly");
            passed = false;
        }
        if (student.getQualityPoints() != 120) {
            System.out.println("Quality points not set correctly");
            passed = false;
        }

        // Test for invalid inputs (should throw IllegalArgumentException)
        try {
            new Student(null, 30, 120);
            System.out.println("Student Creation test failed: No exception for null name");
            passed = false;
        } catch (IllegalArgumentException e) {
            // Expected exception caught
        }
        try {
            // Negative credit hours
            new Student("Leo", -1, 120);
            System.out.println("Student Creation test failed: No exception for negative credit hours");
            passed = false;
        } catch (IllegalArgumentException e) {
            // Expected exception caught
        }

        try {
            // Negative quality points
            new Student("Robert", 30, -1);
            System.out.println("Student Creation test failed: No exception for negative quality points");
            passed = false;
        } catch (IllegalArgumentException e) {
            // Expected exception caught
        }
        // Return overall test result
        return passed;
    }

    // Tests the GPA calculation logic for various scenarios
    public static boolean testGpaCalculation() {
        // Initialize to true, assuming the test will pass
        boolean passed = true;

        // Test normal GPA calculation
        Student student = new Student("Victoria", 30, 120);
        // Expected GPA: 4.0
        if (Math.abs(student.gpa() - 4.0) >= 0.01) {
            System.out.println("GPA calculation error for Victoria");
            passed = false;
        }

        // Expected GPA: 3.0
        student = new Student("Lillian", 20, 60);
        if (Math.abs(student.gpa() - 3.0) >= 0.01) {
            System.out.println("GPA calculation error for Lillian");
            passed = false;
        }

        // Test GPA calculation with zero credit hours (should be 0.0)
        student = new Student("George", 0, 0);
        if (Math.abs(student.gpa() - 0.0) >= 0.01) {
            System.out.println("GPA calculation error for George with zero credit hours");
            passed = false;
        }

        // Test GPA calculation with non-zero quality points and zero credit hours
        // (should still be 0.0)
        student = new Student("Doria", 0, 50);
        if (Math.abs(student.gpa() - 0.0) >= 0.01) {
            System.out.println("GPA calculation error for Doria with zero credit hours");
            passed = false;
        }
        // Return overall test result
        return passed;
    }

    // Tests eligibility for the honor society based on the GPA threshold
    public static boolean testEligibilityForHonorSociety() {

        double defaultThreshold = 4.0;

        // Initialize to true, assuming the test will pass
        boolean passed = true;

        // Set a test GPA threshold
        double testThreshold = 3.5;
        Student.setGpaThreshold(testThreshold);

        // Test case where student's GPA is exactly the threshold
        Student student = new Student("Jeremy", 30, 105);
        // GPA: 3.5
        if (!student.eligibleForHonorSociety()) {
            System.out.println("Eligibility test failed for Jeremy (GPA exactly at threshold)");
            passed = false;
        }

        // Test case where student's GPA is above the threshold
        student = new Student("Emilia", 30, 120);
        // GPA: 4.0
        if (!student.eligibleForHonorSociety()) {
            System.out.println("Eligibility test failed for Emilia (GPA above threshold)");
            passed = false;
        }

        // Test case where student's GPA is below the threshold
        student = new Student("Leo", 30, 100);
        // GPA: 3.33
        if (student.eligibleForHonorSociety()) {
            System.out.println("Eligibility test failed for Leo (GPA below threshold)");
            passed = false;
        }

        // Reset the GPA threshold to its default or another desired value after testing
        Student.setGpaThreshold(defaultThreshold);

        // Return overall test result
        return passed;
    }

}
