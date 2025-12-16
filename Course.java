import java.util.List;

public class Course {
    private String courseName;
    private String courseNumber;
    private int credit;
    private List<String> instructors;
    private String timeSlot; 

    public Course(String courseName, String courseNumber, int credit,
                  List<String> instructors, String timeSlot) {
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.credit = credit;
        this.instructors = instructors;
        this.timeSlot = timeSlot;
    }

    public String getCourseName() { return courseName; }
    public String getCourseNumber() { return courseNumber; }
    public int getCredit() { return credit; }
    public List<String> getInstructors() { return instructors; }
    public String getTimeSlot() { return timeSlot; }

    public boolean hasInstructor(String name){
        return instructors.contains(name);
    }

    @Override
    public String toString() {
        return courseName + " (" + courseNumber + ") - Credit: " + credit
                + " | Time: " + timeSlot + " | Instructors: " + instructors;
    }
}