
import java.util.ArrayList;
import java.util.List;

public class CourseCatalog {
   private List<Course> courses = new ArrayList();

   public CourseCatalog() {
      this.courses.add(new Course("Mathematics", "C101", 3));
      this.courses.add(new Course("Physics", "C102", 4));
      this.courses.add(new Course("Chemistry", "C103", 2));
      this.courses.add(new Course("Biology", "C104", 3));
      this.courses.add(new Course("History", "C105", 2));
   }

   public List<Course> getCourses() {
      return this.courses;
   }
   
   public void listCourses() {
      System.out.println("Available Courses:");

      for(int var1 = 0; var1 < this.courses.size(); ++var1) {
         System.out.println(var1 + 1 + ". " + String.valueOf(this.courses.get(var1)));
      }

   }
}
