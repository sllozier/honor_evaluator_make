package main;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 2
 * Date: February 6th, 2024
 * Description: The Undergraduate class extends the Student class to include
 * additional
 * information specific to undergraduate students, such as their academic year
 * (Freshman,
 * Sophomore, Junior, Senior). It overrides eligibility criteria for honor
 * societies by
 * requiring students to be either Juniors or Seniors in addition to meeting the
 * GPA threshold.
 * This class also provides a customized string representation to include the
 * student's year.
 */

// Defines the Undergraduate class which inherits from the Student class
public class Undergraduate extends Student {
    // Attribute to store the academic year of the undergraduate student (e.g.,
    // Freshman, Sophomore, Junior, Senior)
    private final String studentYear;

    // Constructor for the Undergraduate class
    public Undergraduate(String studentName, int creditHours, int qualityPoints, String studentYear) {
        // Calls the superclass (Student) constructor to initialize inherited attributes
        super(studentName, creditHours, qualityPoints);
        // Initializes the studentYear attribute specific to the Undergraduate class
        this.studentYear = studentYear;
    }

    // Getter method for the studentYear attribute
    public String getYear() {
        return this.studentYear;
    }

    // Overrides the eligibleForHonorSociety method from the Student class
    @Override
    public boolean eligibleForHonorSociety() {
        // Eligibility is based on being a Junior or Senior in addition to meeting the
        // GPA threshold
        if ("Junior".equals(this.studentYear) || "Senior".equals(this.studentYear)) {
            return this.gpa() >= getGpaThreshold();
        } else {
            // Returns false for Freshman and Sophomore, regardless of GPA
            return false;
        }
    }

    // Overrides the toString method to include the student's year in the output
    // string
    @Override
    public String toString() {
        // Formats the string to include the student's name, GPA, and academic year
        return "Name: " + this.getName() + ", " + "GPA: " + String.format("%.2f", this.gpa()) + ", Year: "
                + this.studentYear;
    }
}
