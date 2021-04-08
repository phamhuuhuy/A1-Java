package rmit;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentEnrolmentCommand implements StudentEnrolmentManager{
    private ArrayList<StudentEnrolment> enrolments = new ArrayList<>();
    private HashSet<Student> students = new LinkedHashSet<>();
    private ArrayList<Course> courses = new ArrayList<>();

    private HashSet<String> sems = new LinkedHashSet<>();

    public StudentEnrolmentCommand() throws FileNotFoundException, ParseException {
        getEnrolment();
//        sems.add("2021A");
//        sems.add("2021B");
//        sems.add("2021C");
//
//        String date = "2000-11-30";
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Date dob = null;
//        try {
//            dob = df.parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Student stu1 = new Student("001", "Nguyen Quoc Huy", dob);
//        students.add(stu1);
//
//        String date1 = "2000-07-07";
//        try {
//            dob = df.parse(date1);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Student stu2 = new Student("002", "Pham Huu Huy", dob);
//        students.add(stu2);
//
//        Course cour1 = new Course("S123", "Engineering","1234354654");
//        courses.add(cour1);
//
//        Course cour2 = new Course("S124", "Software","12343546532");
//        courses.add(cour2);
    }

    public void getEnrolment() throws FileNotFoundException, ParseException {
        File fileCSV = new File("default.csv");
        Scanner input = new Scanner(fileCSV);
        HashSet<String> idStu = new HashSet<>();
        while (input.hasNext()){
            String line = input.nextLine();

            String[] token = line.split(",");
            token[0] = token[0].replaceAll("[^a-zA-Z0-9]", "");
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            Date dob = df.parse(token[2]);


            Student student = new Student(token[0], token[1], dob);
            if (!checkDupStudent(student)){
                students.add(student);
            }


            Course course = new Course(token[3], token[4], token[5]);
            if (!checkDupCourse(course)){
                courses.add(course);
            }

            sems.add(token[6]);

            StudentEnrolment enrolment = new StudentEnrolment(student, course, token[6]);
            enrolments.add(enrolment);
        }
    }

    public boolean checkDupStudent(Student newStudent){
        if (students != null) {
            for (Student stu : students) {
                if (stu.getId().trim().equals(newStudent.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkDupCourse(Course newCourse){
        if (courses != null) {
            for (Course cour : courses) {
                if (cour.getId().equals(newCourse.getId())) {
                    return true;
                }
            }
        }
        return false;
    }
    public void showStudent(){
        int countStu = 0;
        for (Student i: students){
            countStu++;
            System.out.print("Id student "+countStu +": "+ i.getId() + "  ");

        }
        System.out.println("");
    }

    public Student checkStudent(HashSet<Student> studentList){

         while (true){
             System.out.println("-----All students can choose-----");
             showStudent();
             System.out.print("Choose id student: ");
             Scanner input = new Scanner(System.in);
             String idStu = input.nextLine();
             for (Student i: students){
                 if (i.getId().equals(idStu)){
                     return i ;
                 }
             }
             System.out.println("This id student does not exist!!!");
         }
    }

    public void showCourse(){
        int countCour = 0;
        for (Course i: courses){
            countCour++;
            System.out.print("Id course "+countCour +": "+ i.getId() + "  ");
        }
        System.out.println("");
    }

    public Course checkCourse(){

        while (true){
            System.out.println("-----All course that student can enroll-----");
            showCourse();

            Scanner input = new Scanner(System.in);
            System.out.print("Choose id course: ");
            String idCour = input.nextLine();
            for (Course i: courses){
                if (i.getId().equals(idCour)){
                    return i;
                }
            }
            System.out.println("This id course does not exist!!!");
        }
    }

    public void showSem(){
        int countSem = 0;
        for (String i: sems){
            countSem++;
            System.out.println("Sem "+countSem +": "+i+ "  ");
        }
        System.out.println("");
    }

    public String checkSem(){
        while (true){
            System.out.println("-----All semester that student can enroll-----");
            showSem();
            Scanner input = new Scanner(System.in);
            System.out.print("Choose sem: ");
            String sem = input.nextLine();
            for (String i: sems){
                if (i.equals(sem)){
                    return i;
                }
            }
            System.out.println("This sem does not exist!!!");
        }
    }

    public void checkDupEnrol(){
        Student stu = checkStudent(students);


        Course cou = checkCourse();


        String sem = checkSem();


        StudentEnrolment enroll = new StudentEnrolment(stu, cou, sem);

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
    public void add() {


        checkDupEnrol();


//        if (enrolments.contains(enroll)){
//            System.out.println("This enrolment has already existed");
//            System.out.println("So cannot add the enrolment");
//        }else{
//            enrolments.add(enroll);

//        }






    }


    @Override
    public void delete() {
        System.out.println("------All enrolments-----");

        while (true){
            int idEnrol = 1;
            for (StudentEnrolment i: enrolments){
                System.out.println(idEnrol+": "+i.toString());
                idEnrol++;
            }
            Scanner input = new Scanner(System.in);
            System.out.print("Choose number of enrolment you want to delete: ");
            Integer number = input.nextInt();
            if (number > 0 && number<=enrolments.size()){
                enrolments.remove(number-1);
            }
            System.out.println("This enrolment does not exist!!!");
        }

    }

    @Override
    public void update() {
        Boolean checkStu = true;
        Student student = null;
        while (checkStu){
            Boolean wrong = true;
            System.out.println("-----All students can choose-----");
            showStudent();
            System.out.print("Choose id student: ");
            Scanner input = new Scanner(System.in);
            String idStu = input.nextLine();
            for (Student i: students){
                if (i.getId().equals(idStu)){
                    student = i;
                    checkStu = false;
                    wrong = false;
                    break;
                }
            }
            if (wrong == true)
                System.out.println("This id student does not exist!!!");
        }
        int countEnrols = 1;


        System.out.println("------Enrolments this student has-----");
        for (StudentEnrolment i: enrolments){
            if (i.getStudent().getId().equals(student.getId())){
                System.out.println("Enrolment "+countEnrols+": course= "+ i.getCourse().getId() + ", Sem= " + i.getSem());
                countEnrols++;
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
