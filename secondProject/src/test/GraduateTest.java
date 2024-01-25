package test;

import main.Graduate;
import main.Student;

public class GraduateTest {

    public static void main(String[] args) {
        boolean result1 = testCreateGraduateAndGetDegree();
        boolean result2 = testEligibilityForHonorSocietyG();
        boolean result3 = testToStringG();

        System.out.println("GraduateTest results:");
        System.out.println("testCreateGraduateAndGetDegree: " + (result1 ? "Passed" : "Failed"));
        System.out.println("testEligibilityForHonorSociety: " + (result2 ? "Passed" : "Failed"));
        System.out.println("testToString: " + (result3 ? "Passed" : "Failed"));
    }

    public static boolean testCreateGraduateAndGetDegree() {

        boolean passed = true; // Initialize to true, assuming the test will pass

        Graduate grad = new Graduate("Emilia", 40, 160, "Masters");
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

        return passed;
    }

    public static boolean testEligibilityForHonorSocietyG() {

        double testThreshold = 3.5;
        Student.setGpaThreshold(testThreshold);

        boolean passed = true; // Initialize to true, assuming the test will pass

        Graduate mastersStudent = new Graduate("George", 40, 140, "Masters"); // GPA: 3.5
        if (!mastersStudent.eligibleForHonorSociety()) {
            System.out.println("Eligibility test failed for Masters student");
            passed = false;
        }

        Graduate phdStudent = new Graduate("Doria", 40, 160, "PhD"); // GPA: 4.0
        if (phdStudent.eligibleForHonorSociety()) {
            System.out.println("Eligibility test failed for PhD student");
            passed = false;
        }

        return passed;
    }

    public static boolean testToStringG() {

        boolean passed = true; // Initialize to true, assuming the test will pass

        Graduate grad = new Graduate("Lillian", 40, 160, "Masters");
        if (!"Name: Lillian, GPA: 4.00, Degree Sought: Masters".equals(grad.toString())) {
            System.out.println("toString output not as expected");
            passed = false;
        }

        return passed;
    }
}
