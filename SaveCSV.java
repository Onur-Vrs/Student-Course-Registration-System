import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SaveCSV {
    static String[] days ={"Monday","Tuesday","Wednesday","Thursday","Friday"};
    static Map<String, Student> students =new HashMap<>();
    static File file = new File("Students.csv");

    static void saveToCSV(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            bw.write("Name,Surname,ID,Graduate,Tuition,"+"Monday,Tuesday,Wednesday,Thursday,Friday,GPA");
            bw.newLine();

            for(Student s : students.values()){
                StringBuilder sb = new StringBuilder();

                sb.append(s.getName()).append(",")
                  .append(s.getSurname()).append(",")
                  .append(s.getId()).append(",")
                  .append(s.getStudentType()).append(",")
                  .append(s.calculateTuition());

                for(String day : days){
                    Course c = s.getDailyCourses().get(day);
                    String inst = s.getInstructor(day);

                    if(c != null){
                        sb.append(",")
                          .append(c.getCourseName())
                          .append(" (")
                          .append(inst)
                          .append(")");
                    } else {
                        sb.append(",");
                    }
                }

                sb.append(",")
                  .append(String.format(Locale.US,"%.2f", s.calculateGPA()));

                bw.write(sb.toString());
                bw.newLine();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
        static void loadFromCSV(){
            if(!file.exists()) return;

            try(BufferedReader br = new BufferedReader(new FileReader(file))){
                br.readLine();
                String line;

                while((line = br.readLine())!= null){
                    String [] p = line.split(",");
                    if(p.length<3) continue;

                    Student s = p[3].equals("Yes")
                        ? new GraduateStudent(p[0], p[1], p[2])
                        : new Student(p[0], p[1], p[2]);

                s.setRegistered(true);
                students.put(p[2], s);
                }
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    
}


