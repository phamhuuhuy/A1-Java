package rmit;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static  StudentEnrolmentCommand enrolmentCommand;

    static {
        try {
            enrolmentCommand = new StudentEnrolmentCommand();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws FileNotFoundException, ParseException {

        mainMenu();
    }
    public static void mainMenu() throws FileNotFoundException, ParseException {
        Scanner sc = new Scanner(System.in);


            while (true) {
                System.out.println("------------------------");
                System.out.println("What do you want to do: ");
                System.out.println("1. CRUD student enrolment");
                System.out.println("2. Report");
                System.out.println("3. Exit");
                System.out.println("------------------------");
                System.out.print("Your choice: ");
                String choice = sc.next();
                if (choice.equals("1")) {
                    crudMenu();
                    return;
                } else if (choice.equals("2")) {
                    reportMenu();
                    return;
                } else if (choice.equals("3")){
                    return;
                } else {
                    System.out.println("Wrong input");
                }
            }

    }
    public static void crudMenu() throws FileNotFoundException, ParseException {
        Scanner sc = new Scanner(System.in);




            while (true) {
                System.out.println("------------------------");
                System.out.println("What do you want to do: ");
                System.out.println("1. View all enrolment \n2. View one enrolment \n3. Adding new enrolment \n4. Delete enrolment \n5. Update enrolment\n6. Return to Main Menu");
                System.out.println("7. Exit");
                System.out.println("------------------------");
                System.out.print("Your choice: ");
                String choice = sc.next();
                if (choice.equals("1")) {
                    enrolmentCommand.getAll();
                    break;
                } else if (choice.equals("2")){
                    enrolmentCommand.getOne();
                    break;
                }
                else if (choice.equals("3")) {
                    enrolmentCommand.add();
                    break;
                } else if (choice.equals("4")) {
                    enrolmentCommand.delete();
                    break;
                } else if (choice.equals("5")) {
                    enrolmentCommand.update();
                    break;
                } else if (choice.equals("6")) {
                    mainMenu();
                    return;
                } else if (choice.equals("7")) {
                    return;
                } else {
                    System.out.println("Wrong input");
                }
            }
            while (true) {
                System.out.print("Do you want to continue managing? (y/n): ");
                String cont = sc.next();
                if (cont.equals("y") || cont.equals("Y")) {
                    crudMenu();
                    break;
                } else if (cont.equals("n") || cont.equals("N")) {

                    break;
                } else {
                    System.out.println("Wrong input");
                }
            }

    }
    public static void reportMenu() throws FileNotFoundException, ParseException {
        Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("------------------------");
                System.out.println("What do you want to do: ");
                System.out.println("1. Print all courses for one student and one semester \n2. Print all students for one course and one semester \n3. Print all courses offered in 1 semester \n4. Return to Main Menu");
                System.out.println("5. Exit");
                System.out.println("------------------------");
                System.out.print("Your choice: ");
                String choice = sc.next();
                if (choice.equals("1")) {
                    enrolmentCommand.printAllCoursesFor1StudentFor1Sem();
                    break;
                } else if (choice.equals("2")) {
                    enrolmentCommand.printAllStudentsFor1CourseFor1Sem();
                    break;
                } else if (choice.equals("3")) {
                    enrolmentCommand.printAllCoursesOfferedFor1Sem();
                    break;
                } else if (choice.equals("4")) {
                    mainMenu();
                    return;
                } else if (choice.equals("5")) {
                    return;
                } else {
                    System.out.println("Wrong input");
                }
            }
            while (true) {
                System.out.print("Do you want to continue managing? (y/n): ");
                String cont = sc.next();
                if (cont.equals("y") || cont.equals("Y")) {
                    reportMenu();
                    break;
                } else if (cont.equals("n") || cont.equals("N")) {
                    break;
                } else {
                    System.out.println("Wrong input");
                }
            }
        }

}
