package rmit;

public class Course {
    private String id;
    private String name;
    private String numOfCre;

    public Course(String id, String name, String numOfCre) {
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

    public String getNumOfCre() {
        return numOfCre;
    }

    public void setNumOfCre(String numOfCre) {
        this.numOfCre = numOfCre;
    }
}
