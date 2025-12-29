package com.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaveCSVTest {

    /**
    * Prepares test data before each test.
    */
    @BeforeEach
    void setup() {
        SaveCSV.students.clear();

        Student s = new Student("Ali", "Yilmaz", "123456789");

        Course c = new Course(
                "Mathematics",
                "C101",
                5,
                List.of("Ahmet Teacher"),
                "10.30"
        );

        s.setCourse("Monday", c);
        s.setInstructor("Monday", "Ahmet Teacher");
        s.setGrade(c, 80);

        s.setRegistered(true);
        SaveCSV.students.put(s.getId(), s);
    }

    /**
    *  Cleans up CSV file and memory after each test.
    */
    @AfterEach
    void cleanup() {
        File f = new File("Students.csv");
        if (f.exists()) {
            f.delete();
        }
        SaveCSV.students.clear();
    }

    /**
    * Tests that saveToCSV creates the CSV file.
    */
    @Test
    void testSaveToCSV_createsFile() {
        SaveCSV.saveToCSV();

        File f = new File("Students.csv");
        assertTrue(f.exists());
    }

    /**
    * Tests saving and loading a student from CSV.
    */
    @Test
    void testSaveAndLoadFromCSV_studentLoaded() {
        SaveCSV.saveToCSV();

        SaveCSV.students.clear();
        SaveCSV.loadFromCSV();

        assertEquals(1, SaveCSV.students.size());
        assertTrue(SaveCSV.students.containsKey("123456789"));
    }

    /**
    * Tests that loaded student is a regular student.
    */
    @Test
    void testLoadedStudentType_isRegularStudent() {
        SaveCSV.saveToCSV();

        SaveCSV.students.clear();
        SaveCSV.loadFromCSV();

        Student s = SaveCSV.students.get("123456789");
        assertNotNull(s);
        assertEquals("No", s.getStudentType());
    }
}
