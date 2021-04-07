package rmit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentEnrolmentCommand implements StudentEnrolmentManager{
    private ArrayList<StudentEnrolment> enrolments = new ArrayList<StudentEnrolment>();
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Course> courses = new ArrayList<Course>();

    private HashSet<String> sems = new HashSet<>();
    public StudentEnrolmentCommand() {
        sems.add("2020A");
        String date = "2000-11-30";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = null;
        try {
            dob = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Student stu1 = new Student("001", "Nguyen Quoc Huy", dob);
        students.add(stu1);

        String date1 = "2000-07-07";
        try {
            dob = df.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Student stu2 = new Student("002", "Pham Huu Huy", dob);
        students.add(stu2);

        Course cour1 = new Course("S123", "Engineering","1234354654");
        courses.add(cour1);

        Course cour2 = new Course("S124", "Software","12343546532");
        courses.add(cour2);
    }

    private boolean checkStudent(String idStudent){
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
    public void add() {
        Boolean checkStu = true;
        Boolean checkCour = true;
        Boolean checkSem = true;

        Scanner input = new Scanner(System.in);

        Student stu = null;
        while (checkStu){
            for (Student i: students){
                System.out.print(i.getId() + "  ");
            }
            System.out.print("Choose id student: ");
            String idStu = input.nextLine();
            for (Student i: students){
                if (i.getId().equals(idStu)){
                    stu = i;
                    checkStu = false;
                    break;
                }
            }
            if (checkStu == true)
            System.out.println("wrong id");
        }

        Course cou = null;
        while (checkCour){
            for (Course i: courses){
                System.out.println(i.getId() + "  ");
            }
            System.out.print("Choose id course: ");
            String idCour = input.nextLine();
            for (Course i: courses){
                if (i.getId().equals(idCour)){
                    cou = i;
                    checkCour = false;
                    break;
                }
            }
            if (checkCour == true)
                System.out.println("wrong id");
        }

        String sem = null;
        while (checkSem){
            for (String i: sems){
                System.out.println(i+ "  ");
            }
            System.out.println("Choose id semester: ");
            sem = input.nextLine();
            for (String i: sems){
                if (i.equals(sem)){
                    checkSem = false;
                    break;
                }
            }
            if (checkSem == true)
                System.out.println("wrong id");

        }
        StudentEnrolment enroll = new StudentEnrolment(stu, cou, sem);
        enrolments.add(enroll);

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
        for (StudentEnrolment i: this.enrolments){
            System.out.println(i.toString());
        }
    }


}
