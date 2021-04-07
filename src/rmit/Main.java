package rmit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        // write your code here
//
//
//        List<Student> students= new ArrayList<Student>();
//        String date = "2000-11-30";
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Date dob = null;
//        try {
//            dob = df.parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Student stu1 = new Student("001", "NguyenQuocHuy", dob);
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
//        List<Course> courses= new ArrayList<Course>();
//        Course cour1 = new Course("S123", "Engineering","1234354654");
//        courses.add(cour1);

        StudentEnrolmentCommand h = new StudentEnrolmentCommand();
        h.add();
        h.add();
        h.getAll();
    }
}
