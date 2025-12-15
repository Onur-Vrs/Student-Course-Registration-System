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
}
