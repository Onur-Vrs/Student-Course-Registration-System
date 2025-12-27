package com.project;

import java.util.Scanner;

public class Menu {
    static Scanner sc = new Scanner(System.in);

/**
* Displays the student menu, handles user input, and performs actions like add, edit, delete, or list students.
*/
    static void printMenu(){

        SaveCSV.loadFromCSV();
        SaveCSV.saveToCSV();
        
        while (true) {

        System.out.println("\n====STUDENT MENU====");
        System.out.println("1.Add Student");
        System.out.println("2.Edit Student");
        System.out.println("3.Delete Student");
        System.out.println("4.List Student");
        System.out.println("0. Exit");
        System.out.print("Choose: ");

        int c = readInt();

        switch(c){
            case 1 -> Student.addStudent();
            case 2 -> Student.editStudent();
            case 3 -> Student.deleteStudent();
            case 4 -> Student.listStudents();
            case 0 -> {
                System.out.println("Data has been saved. Exiting...");
                return;
            }
            default -> System.out.println("Wrong choose");
        }
    }
}

/**
* Reads an integer from user input, repeatedly prompting until a valid number is entered.
*/
    static int readInt(){
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Enter Number: ");
            } 
    }
}

}