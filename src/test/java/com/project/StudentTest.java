package com.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentTest {

    Student student;

    /**
     This method runs before each test and creates a new Student object.
     */
    @BeforeEach
    void setUp() {
        student = new Student("Ali", "Veli", "123456789");
    }

    /**
     Tests whether the student object is created correctly
     and getter methods return correct values.
     */
    @Test
    void testStudentCreation() {
        assertEquals("Ali", student.getName());
        assertEquals("Veli", student.getSurname());
        assertEquals("123456789", student.getId());
    }

    /**
     Tests that creating a student with an invalid ID
     throws an IllegalArgumentException.
     */
    @Test
    void testInvalidIdThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Student("Ali", "Veli", "123");
        });
    }

    /**
     Tests student registration status.
     */
    @Test
    void testRegistration() {
        student.setRegistered(true);
        assertTrue(student.isRegistered());
    }

    /**
     Tests adding a course to a specific day.
     */
    @Test
    void testSetCourse() {
        Course c = new Course(
                "Math",
                "C101",
                5,
                java.util.Arrays.asList("Ahmet"),
                "10.30"
        );

        boolean added = student.setCourse("Monday", c);

        assertTrue(added);
        assertEquals(c, student.getDailyCourses().get("Monday"));
    }

    /**
     Tests that a course cannot be added if there is a time conflict.
     */
    @Test
    void testCourseTimeConflict() {
        Course c1 = new Course(
                "Math",
                "C101",
                5,
                java.util.Arrays.asList("Ahmet"),
                "10.30"
        );

        Course c2 = new Course(
                "Physics",
                "C102",
                4,
                java.util.Arrays.asList("Mehmet"),
                "10.30"
        );

        student.setCourse("Monday", c1);
        boolean result = student.setCourse("Monday", c2);

        assertFalse(result);
    }

    /**
     Tests assigning a valid instructor to a course.
     */
    @Test
    void testSetInstructor() {
        Course c = new Course(
                "Math",
                "C101",
                5,
                java.util.Arrays.asList("Ahmet"),
                "10.30"
        );

        student.setCourse("Monday", c);
        boolean assigned = student.setInstructor("Monday", "Ahmet");

        assertTrue(assigned);
        assertEquals("Ahmet", student.getInstructor("Monday"));
    }

    /**
     Tests that an invalid instructor cannot be assigned.
     */
    @Test
    void testInvalidInstructor() {
        Course c = new Course(
                "Math",
                "C101",
                5,
                java.util.Arrays.asList("Ahmet"),
                "10.30"
        );

        student.setCourse("Monday", c);
        boolean assigned = student.setInstructor("Monday", "Mehmet");

        assertFalse(assigned);
    }

    /**
     Tests GPA calculation based on course credits and grades.
     */
    @Test
    void testCalculateGPA() {
        Course c1 = new Course(
                "Math",
                "C101",
                5,
                java.util.Arrays.asList("Ahmet"),
                "10.30"
        );

        Course c2 = new Course(
                "Physics",
                "C102",
                3,
                java.util.Arrays.asList("Mehmet"),
                "12.00"
        );

        student.setCourse("Monday", c1);
        student.setCourse("Tuesday", c2);

        student.setGrade(c1, 3.0);
        student.setGrade(c2, 4.0);

        double gpa = student.calculateGPA();

        assertEquals(3.38, gpa, 0.01);
    }

    /**
     Tests total credit calculation.
     */
    @Test
    void testTotalCredits() {
        Course c1 = new Course(
                "Math",
                "C101",
                5,
                java.util.Arrays.asList("Ahmet"),
                "10.30"
        );

        Course c2 = new Course(
                "Physics",
                "C102",
                4,
                java.util.Arrays.asList("Mehmet"),
                "12.00"
        );

        student.setCourse("Monday", c1);
        student.setCourse("Tuesday", c2);

        assertEquals(9, student.getTotalCredit());
    }

    /**
     Tests tuition calculation for a regular student.
     */
    @Test
    void testTuitionCalculation() {
        Course c = new Course(
                "Math",
                "C101",
                5,
                java.util.Arrays.asList("Ahmet"),
                "10.30"
        );

        student.setCourse("Monday", c);

        assertEquals(5000, student.calculateTuition());
    }

    /**
     Tests the student type for a regular student.
     */
    @Test
    void testStudentType() {
        assertEquals("No", student.getStudentType());
    }
}
