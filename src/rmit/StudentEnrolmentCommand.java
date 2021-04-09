package rmit;

import java.io.*;
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

    public void printAllCoursesOfferedFor1Sem(){
        String report = "";
        int count = 1;
        for (String i: sems){
            report += "Semester "+i+ ": \n";
            System.out.println("Semester "+i+ ": ");
            for (Course course: courses){
                boolean check = false;
                for (StudentEnrolment h: enrolments){
                    if (h.getCourse().getId().equals(course.getId()) && h.getSem().equals(i)){
                        check = true;
                    }
                }
                if (check){
                    report += " Course "+count+": "+course.getName()+"\n";
                    System.out.println(" Course "+count+": "+course.getName());
                }
            }
        }
        writeToFile("report3.csv", report, false);
    }


    public void printAllCoursesFor1StudentFor1Sem(){
        String report = "";
        int count= 1;
        for (Student i: students){
            report += "Student "+count+ ": "+i.getId()+"\n";
            System.out.println("Student "+count+ ": "+i.getId());
            count++;
            HashSet<String> semesters = new HashSet<>();
            for (StudentEnrolment h: enrolments){
                if (h.getStudent().getId().equals(i.getId())){
                    semesters.add(h.getSem());
                }
            }
            for (String j: semesters){
                report += "  Semester "+j+ ": \n";
                System.out.println("  Semester "+j+ ": ");
                for (StudentEnrolment k: enrolments){
                    if (k.getStudent().getId().equals(i.getId()) && k.getSem().equals(j)){
                        report += "   " + k.getCourse().toString()+"\n";
                        System.out.println("   " + k.getCourse().toString());
                    }
                }
            }
        }
        writeToFile("report1.csv", report, false);
    }


    public static void writeToFile(String fileName, String line, boolean append) {
        PrintWriter output = null;
        try {
            output = new PrintWriter(new FileWriter(fileName, append));
            output.println(line);
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        finally {
            if (output != null)
                output.close();
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
                break;
            }
            System.out.println("This enrolment does not exist!!!");
        }

    }

    @Override
    public void update() {
        ArrayList<StudentEnrolment> enrolmentListOfStudent = new ArrayList<>();
        Boolean checkStu = true;
        Student student = null;
        Scanner input = new Scanner(System.in);
        while (checkStu){
            Boolean wrong = true;
            System.out.println("-----All students can choose-----");
            showStudent();
            System.out.print("Choose id student: ");

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
                enrolmentListOfStudent.add(i);
                System.out.println("Enrolment "+countEnrols+": course= "+ i.getCourse().getId() + ", Sem= " + i.getSem());
                countEnrols++;
            }
        }

        Boolean run = true;
        Boolean checkInput = false;
        while (run){
            System.out.println("What do you want to do?");
            System.out.println("1. Add enrolment");
            System.out.println("2. Delete");
            System.out.print("Your choice: ");
            Integer choice = input.nextInt();
            if (choice == 1){

                Course cour = checkCourse();


                String sem = checkSem();


                StudentEnrolment enroll = new StudentEnrolment(student, cour, sem);

                Boolean checkDup = false;

                for (StudentEnrolment stu: enrolments){
                    if (stu.getStudent().equals(student) && stu.getCourse().equals(cour) && stu.getSem().equals(sem)){
                        checkDup = true;
                        System.out.println("This enrolment has already existed");
                        checkInput = true;
                        break;
                    }
                }
                if (checkDup == false){
                    enrolments.add(enroll);
                    System.out.println("Enroll successfully!!");
                    System.out.println("---------------------");
                    break;
                }
                run = false;
            }else if (choice == 2){
                System.out.println("------All enrolments-----");

                while (true){
                    int idEnrol1 = 1;
                    for (StudentEnrolment i: enrolmentListOfStudent){
                        System.out.println(idEnrol1+": "+i.toString());
                        idEnrol1++;
                    }

                    System.out.print("Choose number of enrolment you want to delete: ");
                    Integer number = input.nextInt();
                    if (number > 0 && number<=enrolmentListOfStudent.size()){
                        enrolments.remove(enrolmentListOfStudent.get(number-1));
//                        enrolments.removeIf(enrol -> enrolmentListOfStudent.get(number - 1).getStudent().getId().equals(enrol.getStudent().getId()) && enrolmentListOfStudent.get(number - 1).getCourse().getId().equals(enrol.getCourse().getId()) && enrolmentListOfStudent.get(number - 1).getSem().equals(enrol.getSem()));

                        System.out.println("Delete Successfully");
                        checkInput = true;
                        break;
                    }
                    System.out.println("This enrolment does not exist!!!");
                }
                run = false;
            }
            if (checkInput == false){
                System.out.println("Wrong input");
            }

        }



    }

    @Override
    public void getOne() {

    }

    @Override
    public void getAll() {
        System.out.println("------All enrolments-----");
        int count = 1;
        for (StudentEnrolment i: enrolments){
            System.out.println("Enrolment " + count+ ": "+i.toString());
            count++;
        }
    }




}
