package rmit;

import java.text.DateFormat;
import java.util.Date;

public class Student {
    private String id;
    private String name;
    private Date birthday;


    public Student(String id, String name, Date birthday) {
        this.id = id;
        this.name = name;

        this.birthday = birthday;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
