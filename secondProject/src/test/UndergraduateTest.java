package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 2
 * Date: February 6th, 2024
 * Description: UndergraduateTest.java conducts unit tests specifically for the Undergraduate
 * class, extending the Student class. It verifies the functionality of creating Undergraduate
 * instances, accurately retrieving their academic year, determining their eligibility for the
 * honor society based on GPA and year, and ensuring the toString method returns the correct
 * string format. This test suite aims to ensure that the Undergraduate class behaves as
 * expected under various conditions, including both valid and edge case scenarios.
 */

import main.Student;
import main.Undergraduate;

public class UndergraduateTest {

    public static void main(String[] args) {
        // Execute tests and report results
        boolean result1 = testCreateUndergraduateAndGetYear();
        boolean result2 = testEligibilityForHonorSocietyU();
        boolean result3 = testToString();

        // Output the results of each test
        System.out.println("\nCreate Undergraduate and getYear tests " + (result1 ? "passed." : "failed."));
        System.out.println("Eligibility for Honor Society tests " + (result2 ? "passed." : "failed."));
        System.out.println("toString tests " + (result3 ? "passed." : "failed."));
    }

    // Test the creation of Undergraduate objects and retrieval of their academic
    // year
    public static boolean testCreateUndergraduateAndGetYear() {
        // Initialize to true, assuming the test will pass
        boolean passed = true;

        // Create an undergraduate student and verify attributes
        Undergraduate undergrad = new Undergraduate("Robert", 30, 120, "Junior");

        // Validate each attribute
        if (!"Robert".equals(undergrad.getName())) {

            passed = false;
        }
        if (undergrad.getCreditHours() != 30) {

            passed = false;
        }
        if (undergrad.getQualityPoints() != 120) {

            passed = false;
        }
        if (!"Junior".equals(undergrad.getYear())) {

            passed = false;
        }
        if (!passed) {
            System.out.println("Create Undergraduate and getYear test failed.");
        }

        // Return the result of the test
        return passed;
    }

    // Test the eligibility criteria for undergraduates joining the honor society
    public static boolean testEligibilityForHonorSocietyU() {
        // Initialize to true, assuming the test will pass
        boolean passed = true;

        // Set a GPA threshold for testing
        double testThreshold = 3.5;
        Student.setGpaThreshold(testThreshold);

        // Test different undergraduates' eligibility
        Undergraduate junior = new Undergraduate("Jeremy", 30, 105, "Junior");
        // GPA: 3.5
        if (!junior.eligibleForHonorSociety()) {
            System.out.println("Eligibility test failed for Junior");
            passed = false;
        }

        Undergraduate senior = new Undergraduate("Victoria", 30, 120, "Senior");
        // GPA: 4.0
        if (!senior.eligibleForHonorSociety()) {
            System.out.println("Eligibility test failed for Senior");
            passed = false;
        }

        Undergraduate sophomore = new Undergraduate("Lillian", 30, 120, "Sophomore");
        // GPA: 4.0
        if (sophomore.eligibleForHonorSociety()) {
            System.out.println("Eligibility test failed for Sophomore");
            passed = false;
        }
        if (!passed) {
            System.out.println("Create Undergraduate and getYear test failed.");
        }

        // Return the result of the test
        return passed;
    }

    // Test the toString method for Undergraduate objects
    public static boolean testToString() {
        // Initialize to true, assuming the test will pass
        boolean passed = true;

        // Create an undergraduate and check toString output
        Undergraduate undergrad = new Undergraduate("Leo", 30, 120, "Senior");
        if (!"Name: Leo, GPA: 4.00, Year: Senior".equals(undergrad.toString())) {
            System.out.println("toString output not as expected");
            passed = false;
        }

        // Return the result of the test
        return passed;
    }
}
