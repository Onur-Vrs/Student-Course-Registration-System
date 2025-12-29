package com.project;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraduateStudentTest {

    /**
    * Tests that a graduate student returns "Yes" as student type.
    */
    @Test
    void testStudentType_isYes() {
        GraduateStudent gs = new GraduateStudent("Ayse", "Demir", "111222333");
        assertEquals("Yes", gs.getStudentType());
    }


    /**
    * Tests that tuition is zero when no courses are selected.
    */
    @Test
    void testCalculateTuition_withNoCourses_isZero() {
        GraduateStudent gs = new GraduateStudent("Ayse", "Demir", "111222333");
        assertEquals(0, gs.calculateTuition());
    }

    /**
    * Tests tuition calculation with multiple courses.
    */
    @Test
    void testCalculateTuition_withCourses() {
        GraduateStudent gs = new GraduateStudent("Ayse", "Demir", "111222333");

        Course c1 = new Course(
                "Mathematics",
                "C101",
                5,
                List.of("Ahmet Teacher"),
                "10.30"
        );

        Course c2 = new Course(
                "Physics",
                "C102",
                4,
                List.of("Emre Teacher"),
                "12.45"
        );

        gs.setCourse("Monday", c1);
        gs.setCourse("Tuesday", c2);

        int expected = (5 + 4) * 2000;
        assertEquals(expected, gs.calculateTuition());
    }

    /**
    * Tests that GraduateStudent is a subclass of Student.
    */ 
    @Test
    void testGraduateStudent_isInstanceOfStudent() {
        GraduateStudent gs = new GraduateStudent("Ayse", "Demir", "111222333");
        assertTrue(gs instanceof Student);
    }
}
