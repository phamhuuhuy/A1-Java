package rmit;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentEnrolmentCommand implements StudentEnrolmentManager{

    //List of enrolment
    private ArrayList<StudentEnrolment> enrolments = new ArrayList<>();

    //List of students
    private HashSet<Student> students = new LinkedHashSet<>();

    //List of courses
    private ArrayList<Course> courses = new ArrayList<>();

    //List of semester
    private HashSet<String> sems = new LinkedHashSet<>();

    public StudentEnrolmentCommand() throws FileNotFoundException, ParseException {
        getEnrolment();

    }

    public String fileNameInput(){
        while (true){
            System.out.print("Do you want to add your file? (y/n): ");
            Scanner input = new Scanner(System.in);
            String yesOrNo = input.nextLine();
            if (yesOrNo.equals("y")){
                System.out.print("Input file name: ");
                String fileName = input.nextLine();
                return fileName;
            }else if (yesOrNo.equals("n")){
                String fileName = "default.csv";
                return fileName;
            }
            System.out.println("Wrong input");
        }

    }

    //Get information from CSV file
    private void getEnrolment() throws FileNotFoundException, ParseException {
        //Read the file
        File fileCSV = new File(fileNameInput());
        Scanner input = new Scanner(fileCSV);
        HashSet<String> idStu = new HashSet<>();
        while (input.hasNext()){
            //Read by line
            String line = input.nextLine();
            //split by ","
            String[] token = line.split(",");
            //change String to date format
            token[0] = token[0].replaceAll("[^a-zA-Z0-9]", "");
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            Date dob = df.parse(token[2]);

            //create the student object
            Student student = new Student(token[0], token[1], dob);
            //add student to list and check to not have duplicate
            if (!checkDupStudent(student)){
                students.add(student);
            }

            //create the course object
            Course course = new Course(token[3], token[4], token[5]);
            //add course to list and check to not have duplicate
            if (!checkDupCourse(course)){
                courses.add(course);
            }

            //add the semester to list
            sems.add(token[6]);

            //add student, course and semester to enrolment object
            StudentEnrolment enrolment = new StudentEnrolment(student, course, token[6]);

            //add enrolment to list enrolments
            enrolments.add(enrolment);
        }
        input.close();
    }

    //print all courses offered for one semester
    public void printAllCoursesOfferedFor1Sem(){
        //String to save all information of report
        String report = "";
        //number of course in sem
        int count = 1;
        //each semester
        for (String sem: sems){
            report += "Semester "+sem+ ": \n";
            System.out.println("Semester "+sem+ ": ");
            for (Course course: courses){
                boolean check = false;
                for (StudentEnrolment enrolment: enrolments){
                    if (enrolment.getCourse().getId().equals(course.getId()) && enrolment.getSem().equals(sem)){
                        //check course in semester
                        check = true;
                    }
                }
                if (check){
                    //if right add Course to String report
                    report += " Course "+count+": "+course.getName()+"\n";
                    System.out.println(" Course "+count+": "+course.getName());
                }
            }
        }

        //calling add CSV
        wantAddToCSV("report3.csv", report);
    }

    //print all students for one course for one semester
    public void printAllStudentsFor1CourseFor1Sem(){
        //String to save all information of report
        String report = "";
        //number of course in sem
        int count= 1;
        //each course
        for (Course course: courses){
            report += "Course "+count+ ": "+course.getId() +"\n";
            System.out.println("Course "+count+ ": "+course.getId());
            count++;
            HashSet<String> semesters = new HashSet<>();
            for (StudentEnrolment enrolment: enrolments){
                if (enrolment.getCourse().getId().equals(course.getId())){
                    //add sem to hashset to remove duplicate
                    semesters.add(enrolment.getSem());
                }
            }
            // each sem in course
            for (String sem: semesters){
                report += "  Semester "+sem+ ": \n";
                System.out.println("  Semester "+sem+ ": ");
                for (StudentEnrolment enrolment: enrolments){
                    if (enrolment.getCourse().getId().equals(course.getId()) && enrolment.getSem().equals(sem)){
                        // all students in this course and this semester
                        report += "   " + enrolment.getStudent().toString()+ "\n";
                        System.out.println("   " + enrolment.getStudent().toString());
                    }
                }
            }
        }
        //calling add CSV
        wantAddToCSV("report2.csv", report);

    }

    //print all courses for one student for one sem
    public void printAllCoursesFor1StudentFor1Sem(){
        //String to save all information of report
        String report = "";
        //number of student in sem
        int count= 1;
        //each student
        for (Student student: students){
            report += "Student "+count+ ": "+student.getId()+"\n";
            System.out.println("Student "+count+ ": "+student.getId());
            count++;
            HashSet<String> semesters = new HashSet<>();
            for (StudentEnrolment enrolment: enrolments){
                if (enrolment.getStudent().getId().equals(student.getId())){
                    //check sem the student study and add to semester hashset
                    semesters.add(enrolment.getSem());
                }
            }
            for (String sem: semesters){
                report += "  Semester "+sem+ ": \n";
                System.out.println("  Semester "+sem+ ": ");
                for (StudentEnrolment enrolment: enrolments){
                    if (enrolment.getStudent().getId().equals(student.getId()) && enrolment.getSem().equals(sem)){
                        // all courses in this student and this semester
                        report += "   " + enrolment.getCourse().toString()+"\n";
                        System.out.println("   " + enrolment.getCourse().toString());
                    }
                }
            }
        }
        //add report to CSV file
        wantAddToCSV("report1.csv", report);
    }

    //function to add report into CSV file
    private static void writeToFile(String fileName, String line, boolean append) {
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

    //function to add user
    private void wantAddToCSV(String fileName, String line){
        while(true){
            System.out.print("Do you want to add report to CSV file? (y/n): ");
            Scanner input = new Scanner(System.in);
            String answer = input.nextLine();
            if (answer.equals("y")){
                writeToFile(fileName, line, false);
                break;
            }else if (answer.equals("n")){
                break;
            }
            System.out.println("Wrong input");
        }
    }

    //check duplicate students
    private boolean checkDupStudent(Student newStudent){
        if (students != null) {
            for (Student stu : students) {
                if (stu.getId().trim().equals(newStudent.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    //check duplicate courses
    private boolean checkDupCourse(Course newCourse){
        if (courses != null) {
            for (Course cour : courses) {
                if (cour.getId().equals(newCourse.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    //show all students
    private void showStudent(){
        int countStu = 0;
        for (Student student: students){
            countStu++;
            System.out.print("Id student "+countStu +": "+ student.getId() + "  ");

        }
        System.out.println("");
    }

    //check id student input
    private Student checkStudent(HashSet<Student> studentList){
         while (true){
             System.out.println("-----All students can choose-----");
             showStudent();
             System.out.print("Choose id student: ");
             Scanner input = new Scanner(System.in);
             String idStu = input.nextLine();
             for (Student student: students){
                 //check id student and return student object
                 if (student.getId().equals(idStu)){
                     return student ;
                 }
             }
             //wrong let user input again
             System.out.println("This id student does not exist!!!");
         }
    }

    //show all courses
    private void showCourse(){
        int countCour = 0;
        for (Course course: courses){
            countCour++;
            System.out.print("Id course "+countCour +": "+ course.getId() + "  ");
        }
        System.out.println("");
    }

    //check id course input
    private Course checkCourse(){
        while (true){
            System.out.println("-----All course that student can enroll-----");
            showCourse();
            Scanner input = new Scanner(System.in);
            System.out.print("Choose id course: ");
            String idCour = input.nextLine();
            for (Course course: courses){
                //check id course and return course object
                if (course.getId().equals(idCour)){
                    return course;
                }
            }
            //wrong let user input again
            System.out.println("This id course does not exist!!!");
        }
    }

    //show all semesters
    private void showSem(){
        int countSem = 0;
        for (String sem: sems){
            countSem++;
            System.out.println("Sem "+countSem +": "+sem+ "  ");
        }
        System.out.println("");
    }

    //check semester input
    private String checkSem(){
        while (true){
            System.out.println("-----All semester that student can enroll-----");
            showSem();
            Scanner input = new Scanner(System.in);
            System.out.print("Choose sem: ");
            String sem = input.nextLine();
            for (String semester: sems){
                //check the semester and return String semester
                if (sem.equals(semester)){
                    return semester;
                }
            }
            //wrong let user input again
            System.out.println("This sem does not exist!!!");
        }
    }

    //function check enrolment duplicate when adding
    private void checkDupEnrol(){
        //calling check student function
        Student stu = checkStudent(students);
        //calling check course function
        Course cou = checkCourse();
        //calling check semester function
        String sem = checkSem();
        //create the enrolment object
        StudentEnrolment enroll = new StudentEnrolment(stu, cou, sem);

        Boolean checkDup = false;
        //go all enrolment
        for (StudentEnrolment enrolment: enrolments){
            //check duplicate enrolment
            if (enrolment.getStudent().equals(stu) && enrolment.getCourse().equals(cou) && enrolment.getSem().equals(sem)){
                checkDup = true;
                System.out.println("This enrolment has already existed");
                break;
            }
        }
        if (checkDup == false){
            //if no duplicate, add in to enrolment list
            enrolments.add(enroll);
            System.out.println("Enroll successfully!!");
            System.out.println("---------------------");
        }
    }

    //function add
    @Override
    public void add() {
        //calling function
        checkDupEnrol();

    }


    @Override
    public void delete() {


        while (true){
            if (enrolments.isEmpty()){
                System.out.println("Dont have any enrolment of student for delete");
                break;
            }
            System.out.println("------All enrolments-----");
            int idEnrol = 1;
            //list all enrolment
            for (StudentEnrolment enrolment: enrolments){
                System.out.println(idEnrol+": "+enrolment.toString());
                idEnrol++;
            }
            Scanner input = new Scanner(System.in);
            System.out.print("Choose number of enrolment you want to delete: ");
            String userInput = input.nextLine();
            if (isNumeric(userInput)){
                int number = Integer.parseInt(userInput);
                if (number> 0 && number<=enrolments.size()){
                    //remove with index
                    enrolments.remove(number-1);
                    break;
                }
            }else{
                //wrong input, let user input again
                System.out.println("This enrolment does not exist!!!");
            }

        }

    }

    //check input String is numeric or not
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @Override
    public void update() {
        //create the list of enrolment of this student
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
            for (Student stu: students){
                //check student input
                if (stu.getId().equals(idStu)){
                    student = stu;
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
        //list all enrolment of this student
        for (StudentEnrolment enrolment: enrolments){
            if (enrolment.getStudent().getId().equals(student.getId())){
                enrolmentListOfStudent.add(enrolment);
                System.out.println("Enrolment "+countEnrols+": course= "+ enrolment.getCourse().getId() + ", Sem= " + enrolment.getSem());
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
            String choice = input.nextLine();
            //choose 1 for adding
            if (choice.equals("1")){

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
            }else if (choice.equals("2")){


                //choose 2 for delete


                while (true){
                    if (enrolmentListOfStudent.isEmpty()){
                        System.out.println("Dont have any enrolment of student for delete");
                        checkInput = true;
                        break;
                    }
                    System.out.println("------All enrolments-----");
                    int idEnrol1 = 1;
                    for (StudentEnrolment studentEnrolment: enrolmentListOfStudent){
                        System.out.println(idEnrol1+": "+studentEnrolment.toString());
                        idEnrol1++;
                    }

                    System.out.print("Choose number of enrolment you want to delete: ");
                    String userInput = input.nextLine();
                    if (isNumeric(userInput)){
                        int number = Integer.parseInt(userInput);
                        if (number > 0 && number<=enrolmentListOfStudent.size()){
                            enrolments.remove(enrolmentListOfStudent.get(number-1));
//                        enrolments.removeIf(enrol -> enrolmentListOfStudent.get(number - 1).getStudent().getId().equals(enrol.getStudent().getId()) && enrolmentListOfStudent.get(number - 1).getCourse().getId().equals(enrol.getCourse().getId()) && enrolmentListOfStudent.get(number - 1).getSem().equals(enrol.getSem()));

                            System.out.println("Delete Successfully");
                            checkInput = true;
                            break;
                        }else{
                            System.out.println("This enrolment does not exist!!!");
                        }
                    }else {
                        System.out.println("This enrolment does not exist!!!");
                    }


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
        ArrayList<StudentEnrolment> enrolmentListOfStudent = new ArrayList<>();
        ArrayList<String> semesterEnrolment = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        Boolean checkStu = true;
        Student student = null;
        while (checkStu){
            Boolean wrong = true;
            System.out.println("-----All students can choose-----");
            showStudent();
            System.out.print("Choose id student: ");

            String idStu = input.nextLine();
            for (Student stu: students){
                //check student input
                if (stu.getId().equals(idStu)){
                    student = stu;
                    checkStu = false;
                    wrong = false;

                    break;
                }
            }
            if (wrong == true)
                System.out.println("This id student does not exist!!!");
        }
        for (StudentEnrolment enrolment: enrolments){
            if (enrolment.getStudent().getId().equals(student.getId())){
                enrolmentListOfStudent.add(enrolment);
            }
        }

        Boolean checkCourse = true;
        Course course =null;
        while(checkCourse){
            Boolean wrong = true;
            if (enrolmentListOfStudent.isEmpty()){
                System.out.println("Dont have any course of student for observe");
                break;
            }
            System.out.println("------All course-----");
            int idEnrol1 = 1;
            for (StudentEnrolment studentCourse: enrolmentListOfStudent){
                System.out.println(idEnrol1+": "+ studentCourse.getCourse());
                idEnrol1++;

            }

            System.out.print("Choose id course:");
            String idCour = input.nextLine();
            for (StudentEnrolment studentCourse: enrolmentListOfStudent){
                //check id course and return course object
                if (studentCourse.getCourse().getId().equals(idCour)){
                    course = studentCourse.getCourse();
                    checkCourse = false;
                    wrong = false;
                }
            }
            //wrong let user input again
            if (wrong == true){
                System.out.println("This id course does not exist!!!");
            }
        }

        for (StudentEnrolment enrolment: enrolments){
            if (enrolment.getStudent().getId().equals(student.getId()) && enrolment.getCourse().getId().equals(course.getId())){
                semesterEnrolment.add(enrolment.getSem());
            }
        }
        Boolean checkSem = true;
        String semester =null;
        while (checkSem){
            Boolean wrong = true;
            if (semesterEnrolment.isEmpty()){
                System.out.println("Dont have any semester of student and course for observe");
                break;
            }
            System.out.println("------All semester-----");
            int idEnrol1 = 1;
            for (String sem: semesterEnrolment){
                System.out.println(idEnrol1+": "+ sem);
                idEnrol1++;
            }
            System.out.print("Choose sem: ");
            String sem = input.nextLine();
            for (String semesterStudent: sems){
                //check the semester and return String semester
                if (semesterStudent.equals(sem)){
                    semester = semesterStudent;
                    checkSem = false;
                    wrong = false;
                }
            }
            //wrong let user input again
            if (wrong == true){
                System.out.println("This sem does not exist!!!");
            }
        }
        System.out.println("This enrolment: ");
        System.out.println(student.toString() +" " + course.toString() + " Semester: " + semester);
    }

    @Override
    public void getAll() {
        //list all enrolments
        System.out.println("------All enrolments-----");
        int count = 1;
        for (StudentEnrolment enrolment: enrolments){
            System.out.println("Enrolment " + count+ ": "+enrolment.toString());
            count++;
        }
    }




}
