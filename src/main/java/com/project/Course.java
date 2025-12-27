package com.project;

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

/**
* Creates a Course object using the course name, code, credit value, instructors, and time slot.
*/
    public Course(String courseName, String courseNumber, int credit, List<String> instructors, String timeSlot) {
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.credit = credit;
        this.instructors = instructors;
        this.timeSlot = timeSlot;
    }

/**
* Returns the course's name, code, credit, instructors and time slot.
*/
    public String getCourseName() { return courseName; }
    public String getCourseNumber() { return courseNumber; }
    public int getCredit() { return credit; }
    public List<String> getInstructors() { return instructors; }
    public String getTimeSlot() { return timeSlot; }

/**
* Checks if the course has an instructor with the given name.
*/
    public boolean hasInstructor(String name){
        return instructors.contains(name);
    }
    
/**
* Reads an integer from the user input and retries until a valid number is entered.
*/
    private static int readInt(){
        while(true){
            try{
                return Integer.parseInt(sc.nextLine());
            } catch(Exception e){
                System.out.print("Enter a valid number");
            }
        }
    }

/**
* Allows a student to select courses and instructors for each day, handling conflicts and input validation.
*/
    static void courseSelection(Student s) {

    for (String day : days) {
        System.out.println("\n" + day);
        catalog.listCourses();

        int c;
        while (true) {
            System.out.print("Choose Lesson (1-" + catalog.getCourses().size() + ", 0 to skip): ");
            c = readInt();

            if (c == 0) break;
            if (c < 0 || c > catalog.getCourses().size()) {
                System.out.println("Invalid selection!");
                continue;
            }
            break;
        }

        if (c == 0) continue;

        Course base = catalog.getCourses().get(c - 1);
        Course chosen = new Course(
                base.getCourseName(),
                base.getCourseNumber(),
                base.getCredit(),
                base.getInstructors(),
                base.getTimeSlot()
        );

        if (!s.setCourse(day, chosen)) {
            System.out.println("Time conflict!");
            continue;
        }

        List<String> ins = chosen.getInstructors();
        for (int i = 0; i < ins.size(); i++)
            System.out.println((i + 1) + ") " + ins.get(i));

        while (true) {
            System.out.print("Choose Instructor (1-" + ins.size() + "): ");
            int idx = readInt();

            if (idx >= 1 && idx <= ins.size()) {
                s.setInstructor(day, ins.get(idx - 1));
                break;
            }
            System.out.println("Invalid instructor!");
        }
    }
}

/**
 * Prompts the user to enter grades for each of the student's courses, ensuring valid input between 0 and 100.
 */
    static void gradeEntry(Student s) {

    for (Course c : s.getDailyCourses().values()) {
        while (true) {
            System.out.print(c.getCourseName() + " grade (0-100): ");
            String g = sc.nextLine();

            try {
                double grade = Double.parseDouble(g);

                if (grade < 0 || grade > 100) {
                    System.out.println("Grade must be between 0 and 100!");
                    continue;
                }

                s.setGrade(c, grade);
                break;

            } catch (NumberFormatException e) {
                System.out.println("Invalid grade!");
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