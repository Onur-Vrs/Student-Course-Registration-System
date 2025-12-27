package com.project;

public class GraduateStudent extends Student {

/**
* Creates a GraduateStudent object with the given name, surname, and ID.
*/
    public GraduateStudent(String name, String surname, String id){
        super(name,surname,id);
    }

/**
* Calculates the tuition for a graduate student based on total credits.
*/
    @Override
    public int calculateTuition(){
        return getTotalCredit()*2000;
    }

/**
* Returns the type of the student.
*/
    @Override
    public String getStudentType(){
        return "Yes";
    }

}