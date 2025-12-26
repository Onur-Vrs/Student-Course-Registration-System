

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
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
            throw new IllegalArgumentException("ID must be 9 digits!");

        this.name=name;
        this.surname=surname;
        this.id=id;
    }

    public String getName(){ return name; }
    public String getSurname(){ return surname; }
    public String getId(){ return id; }
    public Map<Course, Double> getGrades() {
    return grades;
}

    public void setName(String name) {
    this.name = name;
}

public void setSurname(String surname) {
    this.surname = surname;
}



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

    static void addStudent() {

    String n;
    while (true) {
        System.out.print("Name: ");
        n = sc.nextLine();

        if (n.isEmpty()) {
            System.out.println("Name must be entered!");
            continue;
        }

        if (!n.matches("\\p{L}+")) {
            System.out.println("Name can only contain letters!");
            continue;
        }
        break;
    }

    String s;
    while (true) {
        System.out.print("Surname: ");
        s = sc.nextLine().trim();

        if (s.isEmpty()) {
            System.out.println("Surname must be entered!");
            continue;
        }

        if (!s.matches("\\p{L}+")) {
            System.out.println("Surname can only contain letters!");
            continue;
        }
        break;
    }

    String id;
    while(true){
    System.out.print("ID: ");
    id = sc.nextLine();

    if (!id.matches("\\d{9}")) {
        System.out.println("ID must be exactly 9 digits!");
        continue;
    }

    if (students.containsKey(id)) {
        System.out.println("This ID already recorded!");
        continue;
    }
    break;
    }

    System.out.print("Graduate student? (y/n): ");
    String g = sc.nextLine();

    Student st = g.equalsIgnoreCase("y")
            ? new GraduateStudent(n, s, id)
            : new Student(n, s, id);

    Course.courseSelection(st);
    Course.gradeEntry(st);

    st.setRegistered(true);
    students.put(id, st);
    SaveCSV.saveToCSV();

    System.out.println("Student Recorded!");
}


    static void editStudent() {

    System.out.print("Student ID: ");
    String id = sc.nextLine();

    Student s = students.get(id);
    if (s == null) {
        System.out.println("Student not found!");
        return;
    }

    String n;
    while (true) {
        System.out.print("New Name (empty = keep): ");
        n = sc.nextLine();

        if (n.isEmpty()) {
            n = s.getName();
            break;
        }
        if (!n.matches("\\p{L}+")) {
            System.out.println("Only letters allowed!");
            continue;
        }
        break;
    }

    String sur;
    while (true) {
        System.out.print("New Surname (empty = keep): ");
        sur = sc.nextLine();

        if (sur.isEmpty()) {
            sur = s.getSurname();
            break;
        }
        if (!sur.matches("\\p{L}+")) {
            System.out.println("Only letters allowed!");
            continue;
        }
        break;
    }

    s.setName(n);
    s.setSurname(sur);

    s.getDailyCourses().clear();
    s.getGrades().clear();

    Course.courseSelection(s);
    Course.gradeEntry(s);

    SaveCSV.saveToCSV();
    System.out.println("Student updated!");
}


    static void deleteStudent(){
        System.out.print("ID to be deleted");
        Student removed = students.remove(sc.nextLine());
        if(removed==null){
            System.out.println("ID not found!");
        } else {
            System.out.println("ID deleted!");
        }
    }

    static void listStudents(){
        if(students.isEmpty()){
            System.out.println("No records found!");
            return;
        }

        for(Student s : students.values()){
            System.out.println(
                s.getName() + " " + s.getSurname()+
                " | ID: "+ s.getId() +
                " | Graduate: " + s.getStudentType() +
                " | GPA: " + String.format(Locale.US, "%.2f", s.calculateGPA())
            );
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