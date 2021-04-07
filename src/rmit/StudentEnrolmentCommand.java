package rmit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentEnrolmentCommand implements StudentEnrolmentManager{
    private ArrayList<StudentEnrolment> enrolments = new ArrayList<StudentEnrolment>();
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Course> courses = new ArrayList<Course>();

    private HashSet<String> sems = new LinkedHashSet<>();
    public StudentEnrolmentCommand() {

        sems.add("2021A");
        sems.add("2021B");
        sems.add("2021C");

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




    @Override
    public void add() {
        Boolean checkStu = true;
        Boolean checkCour = true;
        Boolean checkSem = true;

        Scanner input = new Scanner(System.in);


        int countStu = 0;
        int countCourse = 0;
        int countSem = 0;

        Student stu = null;
        while (checkStu){
            System.out.println("-----All students can enroll-----");
            for (Student i: students){
                countStu++;
                System.out.print("Id student "+countStu +": "+ i.getId() + "  ");
            }
            System.out.print("\nChoose id student: ");
            String idStu = input.nextLine();
            for (Student i: students){
                if (i.getId().equals(idStu)){
                    stu = i;
                    checkStu = false;
                    break;
                }
            }

            if (checkStu == true)
            System.out.println("This id student does not exist!!!");
        }

        Course cou = null;
        while (checkCour){
            System.out.println("-----All course that student can enroll-----");
            for (Course i: courses){
                countCourse++;
                System.out.print("Id course "+countCourse +": "+i.getId() + "  ");
            }
            System.out.print("\nChoose id course: ");
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
            System.out.println("-----All semester that student can enroll-----");
            for (String i: sems){
                countSem++;
                System.out.println("Sem "+countSem +": "+i+ "  ");
            }
            System.out.print("Choose id semester: ");
            sem = input.nextLine();
            for (String i: sems){
                if (i.equals(sem)){
                    checkSem = false;
                    break;
                }
            }
            if (checkSem == true)
                System.out.println("wrong sem");

        }

        StudentEnrolment enroll = new StudentEnrolment(stu, cou, sem);

//        if (enrolments.contains(enroll)){
//            System.out.println("This enrolment has already existed");
//            System.out.println("So cannot add the enrolment");
//        }else{
//            enrolments.add(enroll);

//        }
        Boolean checkDup = false;

        for (StudentEnrolment student: enrolments){
            if (student.getStudent().equals(stu) && student.getCourse().equals(cou) && student.getSem().equals(sem)){
                checkDup = true;
                System.out.println("This enrolment has already existed");
                break;
            }

        }
        if (checkDup == false){
            enrolments.add(enroll);
            System.out.println("Enroll successfully!!");
            System.out.println("---------------------");
        }





    }


    @Override
    public void delete() {

    }

    @Override
    public void update() {
        Scanner input = new Scanner(System.in);
        Set<String> idStuHasEnrol = new HashSet<>();
        for (StudentEnrolment student1: enrolments){
            idStuHasEnrol.add(student1.getStudent().getId());
        }

        System.out.println("The student has enrolments:");
        for (String id: idStuHasEnrol){
            System.out.println(id);
        }

        String idStu = input.nextLine();
        for (String id: idStuHasEnrol){
            if (idStu.equals(id)){

            }
        }
    }

    @Override
    public void getOne() {

    }

    @Override
    public void getAll() {
        System.out.println("------All enrolments-----");
        for (StudentEnrolment i: enrolments){
            System.out.println(i.toString());
        }
    }


}
