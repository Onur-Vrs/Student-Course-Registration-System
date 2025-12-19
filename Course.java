import java.util.List;
import java.util.Scanner;

public class Course {
    private String courseName;
    private String courseNumber;
    private int credit;
    private List<String> instructors;
    private String timeSlot; 

    static CourseCatalog catalog = new CourseCatalog();
    static Scanner sc = new Scanner(System.in);
    static String [] days={"Monday","Tuesday","Wednesday","Thursday","Friday"};

    public Course(String courseName, String courseNumber, int credit, List<String> instructors, String timeSlot) {
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
    
    private static int readInt(){
        while(true){
            try{
                return Integer.parseInt(sc.nextLine());
            } catch(Exception e){
                System.out.print("Enter a valid number");
            }
        }
    }

    static void courseSelection(Student s){
        for(String day : days){
            System.out.println("\n"+day);
            catalog.listCourses();
            System.out.print("Choose Lesson(1-"+catalog.getCourses().size()+", 0 to skip)");
            int c = readInt();
            if(c<=0 || c>catalog.getCourses().size()) continue;

            Course base = catalog.getCourses().get(c-1);
            Course chosen = new Course(
                base.getCourseName(),
                base.getCourseNumber(),
                base.getCredit(),
                base.getInstructors(),
                base.getTimeSlot()
            );

            if(!s.setCourse(day, chosen)){
                System.out.println("Time Conflict!");
                continue;
            }

            List<String> ins = chosen.getInstructors();
            for(int i=0; i<ins.size(); i++)
                System.out.println((i+1)+") "+ins.get(i));
            
            int insIdx = -1;
            while (true) {
                System.out.print("Choose Instructor (1-" + ins.size() + "): ");
                insIdx = readInt();

    
                if (insIdx >= 1 && insIdx <= ins.size()) {
                    s.setInstructor(day, ins.get(insIdx - 1));
                     break; 
                 } else {
                    System.out.println("Incorrect selection! Please enter one of the numbers from the list.");
                }
            }

        }
    }

    static void gradeEntry(Student s){
        for(Course c : s.getDailyCourses().values()){
            System.out.print(c.getCourseName()+" grade(0-100)");
            String g = sc.nextLine();
            if(!g.isEmpty()){
                try {
                    s.setGrade(c, Double.parseDouble(g));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid grade");
                }
            }
        }
    }

    

    @Override
    public String toString() {
        return courseName + " (" + courseNumber + ") - Credit: " + credit
                + " | Time: " + timeSlot + " | Instructors: " + instructors;
    }
}