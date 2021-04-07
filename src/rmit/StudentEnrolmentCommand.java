package rmit;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentEnrolmentCommand implements StudentEnrolmentManager{
    private ArrayList<StudentEnrolment> enrolments;

    private boolean checkStudent(List<Student> students, String idStudent){
        for (Student stu: students){
            if (stu.getId().equals(idStudent)){
                return true;
            }
        }
        return false;
    }
    private boolean checkCourse(List<Course> courses, String idCourse){
        for (Course cour: courses){
            if (cour.getId().equals(idCourse)){
                return true;
            }
        }
        return false;
    }
    @Override
    public void add(List<Student> students, List<Course> courses) {
        boolean run = true;
        while (run){
            Scanner input = new Scanner(System.in);
            String huy = input.nextLine();
            boolean exitID = false;
            if (checkStudent(students, huy)){
                run = false;
                exitID = true;
                System.out.println("hihi");
            }
            if (exitID == false){
                System.out.println("wrong");
            }
        }

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public void getOne() {

    }

    @Override
    public void getAll() {

    }

}
