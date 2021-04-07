package rmit;

public class StudentEnrolment {

    private Student student;
    private Course course;
    private String sem;

    public StudentEnrolment(Student student, Course course, String sem) {
        this.student = student;
        this.course = course;
        this.sem = sem;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    @Override
    public String toString() {
        return "StudentEnrolment{" +
                "student=" + student.getId() +
                ", course=" + course.getId() +
                ", sem='" + sem + '\'' +
                '}';
    }
}
