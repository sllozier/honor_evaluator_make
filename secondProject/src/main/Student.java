package main;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 2
 * Date: February 6th, 2024
 * Description: The Student class represents a general student entity with basic
 * attributes
 * such as student name, credit hours, and quality points. It provides methods
 * to calculate
 * the GPA, determine eligibility for an honor society based on a GPA threshold,
 * and
 * format the student's information into a string. This class serves as the
 * superclass for
 * more specific student types, including Undergraduate and Graduate students.
 */

public class Student {
    // Attributes to store the student's name, credit hours, and quality points
    private final String studentName;
    private final int creditHours;
    private final int qualityPoints;

    // Static variable to hold the GPA threshold for honor society eligibility
    private static double gpaThreshold = 4.0;

    // Constructor to initialize a Student object with name, credit hours, and
    // quality points
    public Student(String studentName, int creditHours, int qualityPoints) {
        // Validation to ensure inputs are not null or negative
        if (studentName == null) {
            throw new IllegalArgumentException("Student name cannot be null");
        }
        if (creditHours < 0) {
            throw new IllegalArgumentException("Student credit hours cannot be negative");
        }
        if (qualityPoints < 0) {
            throw new IllegalArgumentException("Student quality points cannot be negative");
        }

        // Assigning values to instance variables
        this.studentName = studentName;
        this.creditHours = creditHours;
        this.qualityPoints = qualityPoints;
    }

    // Getter for student's name
    public String getName() {
        return this.studentName;
    }

    // Getter for credit hours
    public int getCreditHours() {
        return this.creditHours;
    }

    // Getter for quality points
    public int getQualityPoints() {
        return this.qualityPoints;
    }

    // Method to calculate and return the GPA, rounded to two decimal places
    public double gpa() {
        // Avoid division by zero if credit hours are zero
        if (creditHours == 0) {
            return 0.0;
        }
        double calculatedGpa = (double) this.qualityPoints / this.creditHours;
        // Rounds to two decimal places
        return Math.round(calculatedGpa * 100.0) / 100.0;
    }

    // Method to check eligibility for the honor society based on GPA
    public boolean eligibleForHonorSociety() {
        return this.gpa() >= getGpaThreshold();
    }

    // Overridden toString method to return a string representation of the student
    @Override
    public String toString() {
        return "Name: " + this.studentName + ", " + "GPA: " + String.format("%.2f", this.gpa());
    }

    // Static method to set the GPA threshold for honor society eligibility
    public static void setGpaThreshold(double threshold) {
        gpaThreshold = threshold;
    }

    // Static method to get the current GPA threshold
    public static double getGpaThreshold() {
        return gpaThreshold;
    }

}
