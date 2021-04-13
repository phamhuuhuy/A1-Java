package rmit;

public class Course {
    private String id;
    private String name;
    private Integer numOfCre;


    public Course(String id, String name, Integer numOfCre) {
        this.id = id;
        this.name = name;
        this.numOfCre = numOfCre;
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

    public int getNumOfCre() {
        return numOfCre;
    }

    public void setNumOfCre(int numOfCre) {
        this.numOfCre = numOfCre;
    }

    @Override
    public String toString() {
        return "Course: " +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", numOfCre='" + numOfCre + '\'';
    }

}
