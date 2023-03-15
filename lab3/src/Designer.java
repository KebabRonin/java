import java.util.Date;
import java.util.Vector;

public class Designer extends Person{
    private Vector<String> projects;
    public Designer(String name, Date dateOfBirth) {super(name, dateOfBirth);}

    public Vector<String> getProjects() {
        return projects;
    }

    public void setProjects(Vector<String> projects) {
        this.projects = projects;
    }
}
