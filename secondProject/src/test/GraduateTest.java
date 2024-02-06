package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 2
 * Date: February 6th, 2024
 * Description: GraduateTest.java is designed to rigorously test the functionality of the Graduate class,
 * ensuring it correctly implements and extends the base functionality provided by the Student class.
 * The tests cover the creation of Graduate objects with valid data, the accurate determination of
 * honor society eligibility based on specific criteria for graduate students, and the correct
 * formatting of Graduate object information into a string. This suite aims to validate that Graduate
 * students are properly instantiated and their unique attributes and methods (e.g., degree sought,
 * eligibility for honor society) behave as expected.
 */

import main.Graduate;
import main.Student;

public class GraduateTest {

    public static void main(String[] args) {
        // Execute tests and report results
        boolean result1 = testCreateGraduateAndGetDegree();
        boolean result2 = testEligibilityForHonorSocietyG();
        boolean result3 = testToStringG();

        // Output the results of each test
        System.out.println("GraduateTest results:");
        System.out.println("testCreateGraduateAndGetDegree: " + (result1 ? "Passed" : "Failed"));
        System.out.println("testEligibilityForHonorSociety: " + (result2 ? "Passed" : "Failed"));
        System.out.println("testToString: " + (result3 ? "Passed" : "Failed"));
    }

    // Tests the creation of Graduate objects and the correct setting of their
    // degree sought
    public static boolean testCreateGraduateAndGetDegree() {
        // Initialize to true, assuming the test will pass
        boolean passed = true;

        // Create a Graduate student and verify all attributes are set correctly
        Graduate grad = new Graduate("Emilia", 40, 160, "Masters");
        // Validate each attribute, including the degree sought
        if (!"Emilia".equals(grad.getName())) {
            System.out.println("Name not set correctly");
            passed = false;
        }
        if (grad.getCreditHours() != 40) {
            System.out.println("Credit hours not set correctly");
            passed = false;
        }
        if (grad.getQualityPoints() != 160) {
            System.out.println("Quality points not set correctly");
            passed = false;
        }
        if (!"Masters".equals(grad.getDegreeSought())) {
            System.out.println("Degree sought not set correctly");
            passed = false;
        }

        // Return the result of the test
        return passed;
    }

    // Tests the eligibility criteria for Graduate students for the honor society
    public static boolean testEligibilityForHonorSocietyG() {
        // Initialize to true, assuming the test will pass
        boolean passed = true;

        // Set a GPA threshold for the test
        double testThreshold = 3.5;
        Student.setGpaThreshold(testThreshold);

        // Test Graduate student eligibility based on degree and GPA
        // Masters student should be eligible, PhD student should not, based on criteria
        Graduate mastersStudent = new Graduate("George", 40, 140, "Masters");
        // GPA: 3.5
        if (!mastersStudent.eligibleForHonorSociety()) {
            System.out.println("Eligibility test failed for Masters student");
            passed = false;
        }

        Graduate phdStudent = new Graduate("Doria", 40, 160, "PhD");
        // GPA: 4.0
        if (phdStudent.eligibleForHonorSociety()) {
            System.out.println("Eligibility test failed for PhD student");
            passed = false;
        }

        // Return the result of the test
        return passed;
    }

    // Tests the toString method of Graduate objects for correct string format
    public static boolean testToStringG() {
        // Initialize to true, assuming the test will pass
        boolean passed = true;

        // Create a Graduate student and check toString output
        Graduate grad = new Graduate("Lillian", 40, 160, "Masters");
        // Verify the toString method returns the expected format
        if (!"Name: Lillian, GPA: 4.00, Degree Sought: Masters".equals(grad.toString())) {
            System.out.println("toString output not as expected");
            passed = false;
        }

        // Return the result of the test
        return passed;
    }
}
