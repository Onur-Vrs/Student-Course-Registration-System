package com.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
Tests the functionality of the CourseCatalog class.
*/
public class CourseCatalogTest {

    private CourseCatalog catalog;

    /**
    Initializes a new CourseCatalog before each test.
    */
    @BeforeEach
    void setUp() {
        catalog = new CourseCatalog();
    }

    /**
    Verifies that the course catalog is initialized with courses.
    */
    @Test
    void testCatalogIsNotEmpty() {
        List<Course> courses = catalog.getCourses();
        assertNotNull(courses);
        assertFalse(courses.isEmpty());
    }

    /**
    Verifies that the catalog contains the expected number of courses.
    */
    @Test
    void testCourseCount() {
        assertEquals(5, catalog.getCourses().size());
    }

    /**
    Verifies that course details are correctly initialized.
    */
    @Test
    void testFirstCourseDetails() {
        Course c = catalog.getCourses().get(0);

        assertEquals("Mathematics", c.getCourseName());
        assertEquals("C101", c.getCourseNumber());
        assertEquals(5, c.getCredit());
        assertTrue(c.getInstructors().contains("Ahmet Teacher"));
    }

    /**
    Verifies that listCourses() runs without throwing an exception.
    */
    @Test
    void testListCoursesDoesNotThrow() {
        assertDoesNotThrow(() -> catalog.listCourses());
    }
}
