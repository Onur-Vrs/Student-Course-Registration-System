import java.util.LinkedHashMap;
import java.util.Map;

public class Student implements Registrable {
    private String name;
    private String surname;
    private String id;
    private boolean registered = false;

    private Map<String,Course> dailyCourses =new LinkedHashMap<>();

    private Map<String, String> selectedInstructors = new LinkedHashMap<>();

    private Map<Course, Double> grades = new LinkedHashMap<>();

    public Student(String name,String surname,String id){
        this.name=name;
        this.surname=surname;
        this.id=id;
    }

    public String getName(){ return name; }
    public String getSurname(){ return surname; }
    public String getId(){ return id; }

    public Map<String, Course> getDailyCourses(){ return dailyCourses; }

    public void removeCourse(String day){
        Course removed = dailyCourses.remove(day);
        selectedInstructors.remove(day);
        if(removed != null){
            grades.remove(removed);
        }
    }

    public boolean hasSameDayConflict(String day, Course newCourse){
        Course existing = dailyCourses.get(day);
        if(existing == null) return false;
        return existing.getTimeSlot().equals(newCourse.getTimeSlot());
    }

    public boolean setCourse(String day, Course courses){
        if(hasSameDayConflict(day, courses)){
            return false;
        }
        dailyCourses.put(day, courses);
        return true;
    }
}
