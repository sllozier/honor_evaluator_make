package test;

import main.Student;
import main.Undergraduate;

public class UndergraduateTest {

    public static void main(String[] args) {
        boolean result1 = testCreateUndergraduateAndGetYear();
        boolean result2 = testEligibilityForHonorSocietyU();
        boolean result3 = testToString();

        // Print results
        System.out.println("\nCreate Undergraduate and getYear tests " + (result1 ? "passed." : "failed."));
        System.out.println("Eligibility for Honor Society tests " + (result2 ? "passed." : "failed."));
        System.out.println("toString tests " + (result3 ? "passed." : "failed."));
    }

    public static boolean testCreateUndergraduateAndGetYear() {
        boolean passed = true; // Initialize to true, assuming the test will pass

        Undergraduate undergrad = new Undergraduate("Robert", 30, 120, "Junior");
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
        return passed; // Return the result of the test
    }

    public static boolean testEligibilityForHonorSocietyU() {

        boolean passed = true; // Initialize to true, assuming the test will pass

        double testThreshold = 3.5;
        Student.setGpaThreshold(testThreshold);

        Undergraduate junior = new Undergraduate("Jeremy", 30, 105, "Junior"); // GPA: 3.5
        if (!junior.eligibleForHonorSociety()) {
            System.out.println("Eligibility test failed for Junior");
            passed = false;
        }

        Undergraduate senior = new Undergraduate("Victoria", 30, 120, "Senior"); // GPA: 4.0
        if (!senior.eligibleForHonorSociety()) {
            System.out.println("Eligibility test failed for Senior");
            passed = false;
        }

        Undergraduate sophomore = new Undergraduate("Lillian", 30, 120, "Sophomore"); // GPA: 4.0
        if (sophomore.eligibleForHonorSociety()) {
            System.out.println("Eligibility test failed for Sophomore");
            passed = false;
        }
        if (!passed) {
            System.out.println("Create Undergraduate and getYear test failed.");
        }
        return passed; // Return the result of the test
    }

    public static boolean testToString() {

        boolean passed = true; // Initialize to true, assuming the test will pass

        Undergraduate undergrad = new Undergraduate("Leo", 30, 120, "Senior");
        if (!"Name: Leo, GPA: 4.00, Year: Senior".equals(undergrad.toString())) {
            System.out.println("toString output not as expected");
            passed = false;
        }

        return passed; // Return the result of the test
    }
}
