package rmit;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestLogic {
    public StudentEnrolmentCommand enrolmentManagement1;

    {
        try {
            enrolmentManagement1 = new StudentEnrolmentCommand("default.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    String birthday = "10/29/1998";
    Date dob;

    {
        try {
            dob = df.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    Student student = new Student("S101163", "Joseph Fergile", dob);
    Course course = new Course("COSC4430", "Further Programming", 5);
    StudentEnrolment studentEnrolment = new StudentEnrolment(student, course, "2021A");


    @Test
    public void testDelete() throws FileNotFoundException, ParseException {
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        enrolmentManagement1.delete();
        assertEquals("Checking size of List after deleting", 14,
                enrolmentManagement1.numberOfEnrolments());

    }

    @Test
    public void testGetAll(){
        assertEquals("Checking size of List", 15, enrolmentManagement1.numberOfEnrolments());

    }


    @Test
    public void testAddNotDuplicateEnrol(){
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        String simulatedUserInput = "S101312" + System.getProperty("line.separator") + "BUS2232"+ System.getProperty("line.separator") + "2021A";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        enrolmentManagement1.add();
        assertEquals("Checking size of List after adding", 16, enrolmentManagement1.numberOfEnrolments());
        System.setIn(sysInBackup);
    }


    @Test
    public void testAddDuplicateEnrol(){
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        String simulatedUserInput = "S101312" + System.getProperty("line.separator") + "COSC4030"+ System.getProperty("line.separator") + "2020C";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        enrolmentManagement1.add();
        assertEquals("Checking size of List after not adding because of duplication", 15, enrolmentManagement1.numberOfEnrolments());
        System.setIn(sysInBackup);
    }

    @Test
    public void testUpdateChooseDelete(){
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        String simulatedUserInput = "S101312" + System.getProperty("line.separator") + "2"+ System.getProperty("line.separator") + "1";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        enrolmentManagement1.update();
        assertEquals("Checking size of List after deleting", 14, enrolmentManagement1.numberOfEnrolments());
        System.setIn(sysInBackup);
    }

    @Test
    public void testUpdateChooseAdd(){
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        String simulatedUserInput = "S101312" + System.getProperty("line.separator") + "1"+ System.getProperty("line.separator") + "BUS2232"+ System.getProperty("line.separator") + "2021A";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        enrolmentManagement1.update();
        assertEquals("Checking size of List after adding", 16, enrolmentManagement1.numberOfEnrolments());
        System.setIn(sysInBackup);
    }

    @Test
    public void testGetOne(){
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        String simulatedUserInput = "S101312" + System.getProperty("line.separator") + "2020C"+ System.getProperty("line.separator") + "COSC4030";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        enrolmentManagement1.getOne();
//        assertEquals("Checking size of List after adding", 16, enrolmentManagement1.numberOfEnrolments());
        System.setIn(sysInBackup);
    }

    @Test
    public void printAllCoursesOfferedFor1Sem(){
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        String simulatedUserInput = "y";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        enrolmentManagement1.printAllCoursesOfferedFor1Sem();
//        assertEquals("Checking size of List after adding", 16, enrolmentManagement1.numberOfEnrolments());
        System.setIn(sysInBackup);
    }

    @Test
    public void printAllStudentsFor1CourseFor1Sem(){
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        String simulatedUserInput = "y";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        enrolmentManagement1.printAllStudentsFor1CourseFor1Sem();
//        assertEquals("Checking size of List after adding", 16, enrolmentManagement1.numberOfEnrolments());
        System.setIn(sysInBackup);
    }

    @Test
    public void printAllCoursesFor1StudentFor1Sem(){
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        String simulatedUserInput = "y";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        enrolmentManagement1.printAllCoursesFor1StudentFor1Sem();
//        assertEquals("Checking size of List after adding", 16, enrolmentManagement1.numberOfEnrolments());
        System.setIn(sysInBackup);
    }


    @Test
    public void testGetNameStudent(){
        assertEquals("Joseph Fergile",student.getName());
    }

    @Test
    public void testSetNameStudent(){
        student.setName("Nguyen Van A");
        assertEquals("Nguyen Van A",student.getName());
    }

    @Test
    public void testGetIdStudent(){
        assertEquals("S101163",student.getId());
    }

    @Test
    public void testSetIdStudent(){
        student.setName("S111111");
        assertEquals("S111111",student.getName());
    }

    @Test
    public void testGetBirthdayStudent(){
        String birthday = "10/29/1998";
        Date dob = null;

        {
            try {
                dob = df.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        assertEquals(dob,student.getBirthday());
    }

    @Test
    public void testSetBirthdayStudent(){
        String birthday = "10/29/1998";
        Date dob = null;
        {
            try {
                dob = df.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        student.setBirthday(dob);
        String birthday1 = "10/29/1998";
        Date dob1 = null;
        {
            try {
                dob1 = df.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        assertEquals(dob1,student.getBirthday());
    }

//Test courses
    @Test
    public void testGetNameCourse(){
        assertEquals("Further Programming",course.getName());
    }

    @Test
    public void testSetNameCourse(){
        course.setName("Further Programming");
        assertEquals("Further Programming",course.getName());
    }

    @Test
    public void testGetIdCourse(){
        assertEquals("COSC4430",course.getId());
    }

    @Test
    public void testSetIdCourse(){
        course.setId("COSC4430");
        assertEquals("COSC4430",course.getId());
    }

    @Test
    public void testGetNumOfCreCourse(){
        assertEquals(5,course.getNumOfCre());
    }

    @Test
    public void testSetNumOfCreCourse(){
        course.setNumOfCre(6);
        assertEquals(6,course.getNumOfCre());
    }

    @Test
    public void	testGetStudent(){
        assertEquals(student.toString(),studentEnrolment.getStudent().toString());
    }
    @Test
    public void	testSetStudent(){
        String birthday = "10/29/1998";
        Date dob = null;

        {
            try {
                dob = df.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Student student2 = new Student("S102732", "Mark Duong", dob);
        StudentEnrolment se = new StudentEnrolment();
        se.setStudent(student2);
        assertEquals(student2.toString(),se.getStudent().toString());
    }

    @Test
    public void	testGetCourse(){
        assertEquals(course.toString(),studentEnrolment.getCourse().toString());
    }
    @Test
    public void	testSetCourse(){
        Course course1 = new Course("COSS4513", "Intro to Platform", 15);
        StudentEnrolment se = new StudentEnrolment();
        se.setCourse(course1);
        assertEquals(course1.toString(),se.getCourse().toString());
    }

    @Test
    public void	testGetSem(){
        assertEquals("2021A",studentEnrolment.getSem());
    }
    @Test
    public void	testSetSem(){
        StudentEnrolment se = new StudentEnrolment();
        se.setSem("2021B");
        assertEquals("2021B",se.getSem());
    }

}
