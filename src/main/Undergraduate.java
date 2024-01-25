package project2;

public class Undergraduate extends Student {
    private final String studentYear;

    public Undergraduate(String studentName, int creditHours, int qualityPoints, String studentYear) {
        super(studentName, creditHours, qualityPoints);
        this.studentYear = studentYear;
    }

    public String getYear() {
        return this.studentYear;
    }

    @Override
    public boolean eligibleForHonorSociety() {
        if ("Junior".equals(this.studentYear) || "Senior".equals(this.studentYear)) {
            return this.gpa() >= getGpaThreshold();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + ", " + "GPA: " + String.format("%.2f", this.gpa()) + ", Year: "
                + this.studentYear;
    }
}
