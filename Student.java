import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Student implements Registrable {
    private String name;
    private String surname;
    private String id;
    private boolean registered;

    private Map<String,Course> dailyCourses =new LinkedHashMap<>();
    private Map<String, String> instructors = new LinkedHashMap<>();
    private Map<Course, Double> grades = new LinkedHashMap<>();
    static Scanner sc = new Scanner(System.in);
    static Map<String, Student> students = new HashMap<>();

    static {
        SaveCSV.loadFromCSV();
        students = SaveCSV.students;
    }

    public Student(String name,String surname,String id){
        if(!id.matches("\\d{9}"))
            throw new IllegalArgumentException("ID 9 haneli olmalı!");

        this.name=name;
        this.surname=surname;
        this.id=id;
    }

    public String getName(){ return name; }
    public String getSurname(){ return surname; }
    public String getId(){ return id; }

    public Map<String, Course> getDailyCourses(){ return dailyCourses; }

    public boolean setCourse(String day, Course c){
        Course existing = dailyCourses.get(day);
        if(existing != null && existing.getTimeSlot().equals(c.getTimeSlot()))
            return false;
        dailyCourses.put(day,c);
        return true;
    }

    public void removeCourse(String day){
        Course c = dailyCourses.remove(day);
        instructors.remove(day);
        if(c != null) grades.remove(c);
        }

    public boolean setInstructor(String day,String name){
       Course c = dailyCourses.get(day);
       if(c==null) return false;
       if(!c.hasInstructor(name)) return false;
       instructors.put(day, name);
       return true;
    }

    public String getInstructor(String day){
        return instructors.getOrDefault(day, "No");
    }
    
    public void setGrade(Course c, double grade){
        if(dailyCourses.containsValue(c))
            grades.put(c,grade);
    }

    static void addStudent(){
        System.out.print("Name:"); String n = sc.nextLine();
        System.out.print("Surname:"); String s = sc.nextLine();
        System.out.print("ID:"); String id = sc.nextLine();

        if(students.containsKey(id)){
            System.out.println("This ID already recorded!!");
            return;
        }

        System.out.print("Graduate student? (y/n): "); String g = sc.nextLine();
        

        try {
            Student st = g.equalsIgnoreCase("y")
                ? new GraduateStudent(n, s, id)
                : new Student(n, s, id);
            
            Course.courseSelection(st);
            Course.gradeEntry(st);

            st.setRegistered(true);
            students.put(id, st);
            System.out.println("Student Recorded!");
            SaveCSV.saveToCSV();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public double calculateGPA(){
        double total =0;
        int credits = 0;

        for(Map.Entry<Course,Double>e : grades.entrySet()){
            total+= e.getKey().getCredit()*e.getValue();
            credits += e.getKey().getCredit();
        }
        return credits == 0?0: total/ credits;
    }

    public int getTotalCredit(){
        int sum = 0;
        for(Course c : dailyCourses.values())
            sum +=c.getCredit();
        return sum;
    }

    public int calculateTuition(){ return getTotalCredit()*1000; }
    public String getStudentType() {return "No"; }
    @Override public boolean isRegistered(){ return registered; }
    @Override public void setRegistered(boolean r){ registered = r; }

}
