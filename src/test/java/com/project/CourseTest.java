package com.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
Tests the core functionality of the Course class.
*/
public class CourseTest {

    private Course course;

    /**
    Creates a sample Course object before each test.
    */
    @BeforeEach
    void setUp() {
        course = new Course(
                "Data Structures",
                "CENG202",
                4,
                List.of("Dr. Smith", "Dr. Brown"),
                "10:00-12:00"
        );
    }

    /**
    Verifies that the course name is returned correctly.
    */
    @Test
    void testGetCourseName() {
        assertEquals("Data Structures", course.getCourseName());
    }

    /**
    Verifies that the course number is returned correctly.
    */
    @Test
    void testGetCourseNumber() {
        assertEquals("CENG202", course.getCourseNumber());
    }

    /**
    Verifies that the course credit value is returned correctly.
    */
    @Test
    void testGetCredit() {
        assertEquals(4, course.getCredit());
    }

    /**
    Verifies that the instructor list is initialized correctly.
    */
    @Test
    void testGetInstructors() {
        assertEquals(2, course.getInstructors().size());
        assertTrue(course.getInstructors().contains("Dr. Smith"));
    }

    /**
    Checks whether a given instructor teaches this course.
    */
    @Test
    void testHasInstructor() {
        assertTrue(course.hasInstructor("Dr. Brown"));
        assertFalse(course.hasInstructor("Dr. Green"));
    }

    /**
    Verifies that the time slot is returned correctly.
    */
    @Test
    void testGetTimeSlot() {
        assertEquals("10:00-12:00", course.getTimeSlot());
    }

    /**
    Verifies that the string representation of the course is not empty.
    */
    @Test
    void testToString() {
        String result = course.toString();
        assertNotNull(result);
        assertTrue(result.contains("Data Structures"));
    }
}
