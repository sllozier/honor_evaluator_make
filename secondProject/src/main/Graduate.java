package main;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 2
 * Date: February 6th, 2024
 * Description: The Graduate class is a specialization of the Student class for
 * graduate
 * students, incorporating an additional attribute to indicate whether the
 * student is pursuing
 * a Master's degree or a Doctorate. The eligibility for honor societies is
 * tailored to
 * graduate students, requiring them to be pursuing a Master's degree in
 * addition to meeting
 * the GPA threshold. The toString method is overridden to include the degree
 * sought in the
 * student's formatted information string.
 */

// Defines the Graduate class which inherits from the Student class
public class Graduate extends Student {
    // Stores the type of graduate degree (e.g., Masters, PhD)
    private final String degreeSought;

    // Constructor initializes graduate student with name, credit hours, quality
    // points, and degree sought
    public Graduate(String studentName, int creditHours, int qualityPoints, String degreeSought) {
        super(studentName, creditHours, qualityPoints);
        this.degreeSought = degreeSought;
    }

    // Returns the graduate degree the student is pursuing
    public String getDegreeSought() {
        return this.degreeSought;
    }

    // Checks if the student is eligible for the honor society based on degree and
    // GPA
    @Override
    public boolean eligibleForHonorSociety() {
        // Only Masters students with sufficient GPA are eligible
        if ("Masters".equals(this.degreeSought)) {
            return this.gpa() >= getGpaThreshold();
        } else {
            // Non-Masters students are not eligible
            return false;
        }
    }

    // Returns a string representation including name, GPA, and degree sought
    @Override
    public String toString() {
        // Formats the string to include the student's name, GPA, and degree sought
        return "Name: " + this.getName() + ", " + "GPA: " + String.format("%.2f", this.gpa()) + ", Degree Sought: "
                + this.degreeSought;
    }
}
