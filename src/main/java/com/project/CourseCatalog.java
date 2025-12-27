package com.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseCatalog {
   private List<Course> courses;

   public CourseCatalog() {
      courses = new ArrayList<>();
      this.courses.add(new Course("Mathematics", "C101", 5,Arrays.asList("Ahmet Teacher","Zeynep Teacher","Murat Teacher"),"10.30"));
      this.courses.add(new Course("Physics", "C102", 5,Arrays.asList("Emre Teacher","Doruk Teacher","Cagatay Teacher"),"12.45"));
      this.courses.add(new Course("Chemistry", "C103", 4,Arrays.asList("Onur Teacher","Kadir Teacher","Burak Teacher"),"9.00"));
      this.courses.add(new Course("Biology", "C104", 3,Arrays.asList("Tolga Teacher","Kubra Teacher","Ilkim Teacher"),"15.00"));
      this.courses.add(new Course("History", "C105", 2,Arrays.asList("Zehra Teacher","Zureyha Teacher","Hasan Teacher"),"11,00"));
   }

   public List<Course> getCourses() {
      return this.courses;
   }
   
   public void listCourses() {
      System.out.println("Available Courses:");

      for(int i = 0; i < this.courses.size(); ++i) {
         System.out.println(i + 1 + ". " + String.valueOf(this.courses.get(i)));
      }

   }
}