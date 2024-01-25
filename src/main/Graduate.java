package project2;

class Graduate extends Student {
    private final String degreeSought;

    public Graduate(String studentName, int creditHours, int qualityPoints, String degreeSought) {
        super(studentName, creditHours, qualityPoints);
        this.degreeSought = degreeSought;
    }

    public String getDegreeSought() {
        return this.degreeSought;
    }

    @Override
    public boolean eligibleForHonorSociety() {
        if ("Masters".equals(this.degreeSought)) {
            return this.gpa() >= getGpaThreshold();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + ", " + "GPA: " + String.format("%.2f", this.gpa()) + ", Degree Sought: "
                + this.degreeSought;
    }
}
