
import java.io.Serializable;

public class GradeRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private String subject;
    private String assignmentName;
    private double pointsEarned;
    private double totalPoints;

    public GradeRecord(String subject, String assignmentName,
                       double pointsEarned, double totalPoints) {

        this.subject = subject;
        this.assignmentName = assignmentName;
        this.pointsEarned = pointsEarned;
        this.totalPoints = totalPoints;
    }

    public String getSubject() {
        return subject;
    }
    

    public String getAssignmentName() {
        return assignmentName;
    }

    public double getPointsEarned() {
        return pointsEarned;
    }

    public double getTotalPoints() {
        return totalPoints;
    }

    public double getPercentage() {
        return (pointsEarned / totalPoints) * 100;
    }
}

