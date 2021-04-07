package rmit;

import java.util.List;

public interface StudentEnrolmentManager {
    void add(List<Student> students, List<Course> courses);
    void delete();
    void update();
    void getOne();
    void getAll();

}
