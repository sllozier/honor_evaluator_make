package project2;

public class Student {
    private final String studentName;
    private final int creditHours;
    private final int qualityPoints;
    private static double gpaThreshold = 4.0;

    public Student(String studentName, int creditHours, int qualityPoints) {
        if (studentName == null) {
            throw new IllegalArgumentException("Student name cannot be null");
        }
        if (creditHours < 0) {
            throw new IllegalArgumentException("Student credit hours cannot be negative");
        }
        if (qualityPoints < 0) {
            throw new IllegalArgumentException("Student quality points cannot be negative");
        }

        this.studentName = studentName;
        this.creditHours = creditHours;
        this.qualityPoints = qualityPoints;
    }

    public String getName() {
        return this.studentName;
    }

    public int getCreditHours() {
        return this.creditHours;
    }

    public int getQualityPoints() {
        return this.qualityPoints;
    }

    public double gpa() {
        if (creditHours == 0) {
            return 0.0;
        }
        double calculatedGpa = (double) this.qualityPoints / this.creditHours;
        return Math.round(calculatedGpa * 100.0) / 100.0; // Rounds to two decimal places
    }

    public boolean eligibleForHonorSociety() {
        return this.gpa() >= getGpaThreshold();
    }

    @Override
    public String toString() {
        return "Name: " + this.studentName + ", " + "GPA: " + String.format("%.2f", this.gpa());
    }

    public static void setGpaThreshold(double threshold) {
        gpaThreshold = threshold;
    }

    public static double getGpaThreshold() {
        return gpaThreshold;
    }

}
