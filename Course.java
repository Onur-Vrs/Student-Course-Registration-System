public class Course {
    private String courseName;
    private String courseNumber;
    private int credit;

    public Course(String courseName, String courseNumber, int credit) {
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.credit = credit;
    }

    public String getCourseName() { return courseName; }
    public String getCourseNumber() { return courseNumber; }
    public int getCredit() { return credit; }

    @Override
    public String toString() {
        return courseName + " (" + courseNumber + ") - Credit: " + credit;
    }
}
