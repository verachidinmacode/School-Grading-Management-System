
import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String studentName;
    private String studentID;

    private ArrayList<GradeRecord> grades;

    public Student(String studentName, String studentID) {

        this.studentName = studentName;
        this.studentID = studentID;

        grades = new ArrayList<>();
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void addGrade(GradeRecord grade) {
        grades.add(grade);
    }

    public ArrayList<GradeRecord> getGrades() {
        return grades;
    }

    public double calculateAverage() {

        double totalEarned = 0;
        double totalPossible = 0;

        for (GradeRecord grade : grades) {

            totalEarned += grade.getPointsEarned();
            totalPossible += grade.getTotalPoints();
        }

        if (totalPossible == 0) {
            return 0;
        }

        return (totalEarned / totalPossible) * 100;
    }
}

