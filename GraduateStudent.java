public class GraduateStudent extends Student {

    public GraduateStudent(String name, String surname, String id){
        super(name,surname,id);
    }

    @Override
    public int calculateTuition(){
        return getTotalCredit()*2000;
    }

    @Override
    public String getStudentType(){
        return "Yes";
    }

    
}
