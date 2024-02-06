package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 2
 * Date: February 6th, 2024
 * Description: TestRunner.java is a utility class designed to systematically
 * execute and report
 * on a series of unit tests covering the functionality of the Student,
 * Undergraduate,
 * Graduate, and Project2 classes. It assesses various aspects such as object
 * creation, GPA calculation,
 * honor society eligibility, and string representations, providing a structured
 * and
 * automated approach to validating the system's correctness and robustness.
 */

public class TestRunner {
    // ANSI escape codes for text colors
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        // Header for the test output
        System.out.println("-------------------------------------------------------");
        System.out.println(" T E S T S");
        System.out.println("-------------------------------------------------------");
        System.out.println("|");

        // Call test methods from different test classes
        runStudentTest();
        runUndergraduateTest();
        runGraduateTest();
        runProject2Test();

        // Footer to indicate completion
        System.out.println("\nAll tests completed.");
    }

    // Executes and reports on Student class tests
    private static void runStudentTest() {
        System.out.println("|+-- StudentTest");

        // Execute and display results for Student specific tests
        double executionTime1 = executeTest("testStudentCreation");
        double executionTime2 = executeTest("testGpaCalculation");
        double executionTime3 = executeTest("testEligibilityForHonorSociety");
        boolean result1 = StudentTest.testStudentCreation();
        boolean result2 = StudentTest.testGpaCalculation();
        boolean result3 = StudentTest.testEligibilityForHonorSociety();

        // Report on each test's execution time and result
        System.out.println("|");

        // Calculate and display execution times
        displayExecutionTime("testStudentCreation", executionTime1, result1);
        displayExecutionTime("testGpaCalculation", executionTime2, result2);
        displayExecutionTime("testEligibilityForHonorSociety", executionTime3, result3);
        System.out.println("|");
    }

    // Executes and reports on Undergraduate class tests
    private static void runUndergraduateTest() {
        System.out.println("|+-- UndergraduateTest");

        // Execute and display results for Undergraduate specific tests
        double executionTime1 = executeTest("testCreateUndergraduateAndGetYear");
        double executionTime2 = executeTest("testEligibilityForHonorSocietyU");
        double executionTime3 = executeTest("testToString");
        boolean result1 = UndergraduateTest.testCreateUndergraduateAndGetYear();
        boolean result2 = UndergraduateTest.testEligibilityForHonorSocietyU();
        boolean result3 = UndergraduateTest.testToString();

        // Report on each test's execution time and result
        System.out.println("|");

        // Calculate and display execution times
        displayExecutionTime("testCreateUndergraduateAndGetYear", executionTime1, result1);
        displayExecutionTime("testEligibilityForHonorSocietyU", executionTime2, result2);
        displayExecutionTime("testToString", executionTime3, result3);
        System.out.println("|");
    }

    // Executes and reports on Graduate class tests
    private static void runGraduateTest() {
        System.out.println("|+-- GraduateTest");

        // Execute and display results for Graduate specific tests
        double executionTime1 = executeTest("testCreateGraduateAndGetDegree");
        double executionTime2 = executeTest("testEligibilityForHonorSocietyG");
        double executionTime3 = executeTest("testToStringG");
        boolean result1 = GraduateTest.testCreateGraduateAndGetDegree();
        boolean result2 = GraduateTest.testEligibilityForHonorSocietyG();
        boolean result3 = GraduateTest.testToStringG();

        // Report on each test's execution time and result
        System.out.println("|");

        // Calculate and display execution times
        displayExecutionTime("testCreateGraduateAndGetDegree", executionTime1, result1);
        displayExecutionTime("testEligibilityForHonorSocietyG", executionTime2, result2);
        displayExecutionTime("testToStringG", executionTime3, result3);
        System.out.println("|");
    }

    // Executes and reports on Project2 class tests
    private static void runProject2Test() {
        System.out.println("|+-- Project2Test");

        // Execute and display results for Project2 functionality tests
        double executionTime1 = executeTest("testReadStudentsFromFile");
        double executionTime2 = executeTest("testComputeAverageGpa");
        double executionTime3 = executeTest("testSetAndDisplayGpaThreshold");
        double executionTime4 = executeTest("testDisplayEligibleStudentsReport");
        boolean result1 = Project2Test.testReadStudentsFromFile();
        boolean result2 = Project2Test.testComputeAverageGpa();
        boolean result3 = Project2Test.testSetAndDisplayGpaThreshold();
        boolean result4 = Project2Test.testDisplayEligibleStudentsReport();

        // Report on each test's execution time and result
        System.out.println("|");

        // Calculate and display execution times
        displayExecutionTime("testReadStudentsFromFile", executionTime1, result1);
        displayExecutionTime("testComputeAverageGpa", executionTime2, result2);
        displayExecutionTime("testSetAndDisplayGpaThreshold", executionTime3, result3);
        displayExecutionTime("testDisplayEligibleStudentsReport", executionTime4, result4);
        System.out.println("|");
    }

    private static double executeTest(String testName) {
        // Record start time, execute test, then calculate duration
        long startTime = System.currentTimeMillis();
        boolean result = false;

        // Execute the corresponding test method
        switch (testName) {
            case "testStudentCreation":
                result = testStudentCreation();
                break;
            case "testGpaCalculation":
                result = testGpaCalculation();
                break;
            case "testEligibilityForHonorSociety":
                result = testEligibilityForHonorSociety();
                break;
            case "testCreateUndergraduateAndGetYear":
                result = testCreateUndergraduateAndGetYear();
                break;
            case "testEligibilityForHonorSocietyU":
                result = testEligibilityForHonorSocietyU();
                break;
            case "testToString":
                result = testToString();
                break;
            case "testCreateGraduateAndGetDegree":
                result = testCreateGraduateAndGetDegree();
                break;
            case "testEligibilityForHonorSocietyG":
                result = testEligibilityForHonorSocietyG();
                break;
            case "testToStringG":
                result = testToStringG();
                break;
            case "testReadStudentsFromFile": // Add this case
                result = testReadStudentsFromFile();
                break;
            case "testComputeAverageGpa":
                result = testComputeAverageGpa();
                break;
            case "testSetAndDisplayGpaThreshold":
                result = testSetAndDisplayGpaThreshold();
                break;
            case "testDisplayEligibleStudentsReport":
                result = testDisplayEligibleStudentsReport();
                break;
        }

        // Get the current time after the test
        long endTime = System.currentTimeMillis();
        // Calculate execution time in seconds
        double executionTime = (endTime - startTime) / 1000.0;
        return executionTime;
    }

    // Displays the execution time and result of a test
    private static void displayExecutionTime(String testName, double executionTime, boolean result) {
        if (executionTime >= 0) {
            String resultString = (result == true) ? ANSI_GREEN + "[OK]" : ANSI_RED + "[FAILED]";
            System.out.printf("| +-- %s %s - %.3f ss%s\n", resultString, testName, Math.abs(executionTime), ANSI_RESET);
        }
    }

    private static boolean testStudentCreation() {
        // Return true if the test passes, false otherwise
        return StudentTest.testStudentCreation();
    }

    private static boolean testGpaCalculation() {
        // Return true if the test passes, false otherwise
        return StudentTest.testGpaCalculation();
    }

    private static boolean testEligibilityForHonorSociety() {
        // Return true if the test passes, false otherwise
        return StudentTest.testEligibilityForHonorSociety();
    }

    private static boolean testCreateUndergraduateAndGetYear() {
        // Return true if the test passes, false otherwise
        return UndergraduateTest.testCreateUndergraduateAndGetYear();
    }

    private static boolean testEligibilityForHonorSocietyU() {
        // Return true if the test passes, false otherwise
        return UndergraduateTest.testEligibilityForHonorSocietyU();
    }

    private static boolean testToString() {
        // Return true if the test passes, false otherwise
        return UndergraduateTest.testToString();
    }

    private static boolean testCreateGraduateAndGetDegree() {
        // Return true if the test passes, false otherwise
        return GraduateTest.testCreateGraduateAndGetDegree();
    }

    private static boolean testEligibilityForHonorSocietyG() {
        // Return true if the test passes, false otherwise
        return GraduateTest.testEligibilityForHonorSocietyG();
    }

    private static boolean testToStringG() {
        // Return true if the test passes, false otherwise
        return GraduateTest.testToStringG();
    }

    private static boolean testReadStudentsFromFile() {
        // Return true if the test passes, false otherwise
        return Project2Test.testReadStudentsFromFile();
    }

    private static boolean testComputeAverageGpa() {
        // Return true if the test passes, false otherwise
        return Project2Test.testComputeAverageGpa();
    }

    private static boolean testSetAndDisplayGpaThreshold() {
        // Return true if the test passes, false otherwise
        return Project2Test.testSetAndDisplayGpaThreshold();
    }

    private static boolean testDisplayEligibleStudentsReport() {
        // Return true if the test passes, false otherwise
        return Project2Test.testDisplayEligibleStudentsReport();
    }
}
