
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class SchoolGradingSystem {

    static Scanner input = new Scanner(System.in);

    static ArrayList<Student> students = new ArrayList<>();

    static final String FILE_NAME = "students.dat";

    public static void main(String[] args) {

        // Load previously saved student records
        loadStudents();

        int choice;

        do {
            System.out.println("\n====================================");
            System.out.println("       SCHOOL GRADING SYSTEM");
            System.out.println("====================================");
            System.out.println("1. Teacher Dashboard");
            System.out.println("2. Student Portal");
            System.out.println("3. Exit");
            System.out.println("====================================");

            System.out.print("Select an option: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    teacherDashboard();
                    break;

                case 2:
                    studentPortal();
                    break;

                case 3:
                    saveStudents();
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }

        } while (choice != 3);

        input.close();
    }

    // TEACHER DASHBOARD
    public static void teacherDashboard() {

        int choice;

        do {
            System.out.println("\n========== TEACHER DASHBOARD ==========");
            System.out.println("1. Register Student");
            System.out.println("2. Enter Assignment Grade");
            System.out.println("3. View All Students");
            System.out.println("4. Return to Main Menu");

            System.out.print("Select an option: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    registerStudent();
                    break;

                case 2:
                    enterGrade();
                    break;

                case 3:
                    viewAllStudents();
                    break;

                case 4:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 4);
    }

    // REGISTER STUDENT
    public static void registerStudent() {

        System.out.println("\n--- STUDENT REGISTRATION ---");

        System.out.print("Student Full Name: ");
        String name = input.nextLine();

        System.out.print("Student ID: ");
        String id = input.nextLine();

        if (findStudent(id) != null) {
            System.out.println("Student ID already exists.");
            return;
        }

        Student newStudent = new Student(name, id);

        students.add(newStudent);

        saveStudents();

        System.out.println("Student registered successfully!");
    }

    // ENTER ASSIGNMENT GRADE
    public static void enterGrade() {

        System.out.println("\n--- ENTER ASSIGNMENT GRADE ---");

        System.out.print("Enter Student ID: ");
        String id = input.nextLine();

        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Class Subject: ");
        String subject = input.nextLine();

        System.out.print("Assignment Name: ");
        String assignment = input.nextLine();

        System.out.print("Points Earned: ");
        double earned = input.nextDouble();

        System.out.print("Total Possible Points: ");
        double possible = input.nextDouble();
        input.nextLine();

        if (possible <= 0 || earned < 0 || earned > possible) {
            System.out.println("Invalid grade information.");
            return;
        }

        GradeRecord grade = new GradeRecord(
            subject, assignment, earned, possible
        );

        student.addGrade(grade);

        saveStudents();

        System.out.println("Grade recorded successfully!");
    }

    // VIEW ALL STUDENTS
    public static void viewAllStudents() {

        System.out.println("\n--- REGISTERED STUDENTS ---");

        if (students.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }

        for (Student student : students) {

            System.out.println("--------------------------------");
            System.out.println("Name: " + student.getStudentName());
            System.out.println("Student ID: " + student.getStudentID());
            System.out.println("Assignments: " + student.getGrades().size());
        }
    }

    // STUDENT PORTAL
    public static void studentPortal() {

        System.out.println("\n========== STUDENT PORTAL ==========");

        System.out.print("Enter your Student ID: ");
        String id = input.nextLine();

        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student record not found.");
            return;
        }

        System.out.println("\n========== STUDENT REPORT ==========");
        System.out.println("Student Name: " + student.getStudentName());
        System.out.println("Student ID: " + student.getStudentID());

        System.out.println("------------------------------------");

        if (student.getGrades().isEmpty()) {
            System.out.println("No grades available yet.");
            return;
        }

        for (GradeRecord grade : student.getGrades()) {

            System.out.println("Subject: " + grade.getSubject());
            System.out.println("Assignment: " + grade.getAssignmentName());

            System.out.println(
                "Points: " + grade.getPointsEarned()
                + " / " + grade.getTotalPoints()
            );

            System.out.printf(
                "Percentage: %.2f%%\n", grade.getPercentage()
            );

            System.out.println(
                "Letter Grade: " + getLetterGrade(grade.getPercentage())
            );

            System.out.println("------------------------------------");
        }

        double average = student.calculateAverage();

        System.out.printf("Overall Average: %.2f%%\n", average);

        System.out.println(
            "Overall Letter Grade: " + getLetterGrade(average)
        );

        System.out.println(
            "Course Status: " + (average >= 60 ? "PASS" : "FAIL")
        );

        System.out.println("====================================");
    }

    // FIND STUDENT BY ID
    public static Student findStudent(String id) {

        for (Student student : students) {

            if (student.getStudentID().equalsIgnoreCase(id)) {
                return student;
            }
        }

        return null;
    }

    // DETERMINE LETTER GRADE
    public static char getLetterGrade(double percentage) {

        if (percentage >= 90) {
            return 'A';
        } else if (percentage >= 80) {
            return 'B';
        } else if (percentage >= 70) {
            return 'C';
        } else if (percentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    // SAVE STUDENT RECORDS
    public static void saveStudents() {

        try (ObjectOutputStream output =
             new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            output.writeObject(students);

            System.out.println("Student records saved successfully!");

        } catch (IOException e) {

            System.out.println("Error saving student records: "
                               + e.getMessage());
        }
    }

    // LOAD STUDENT RECORDS
    @SuppressWarnings("unchecked")
    public static void loadStudents() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No previous records found. Starting fresh.");
            return;
        }

        try (ObjectInputStream inputStream =
             new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            students = (ArrayList<Student>) inputStream.readObject();

            System.out.println("Student records loaded successfully!");
            System.out.println("Total students: " + students.size());

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error loading student records: "
                               + e.getMessage());
        }
    }
}
