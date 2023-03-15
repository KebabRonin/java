import java.util.Date;
import java.util.Vector;

public class Programmer extends Person{
    private Vector<String> languages;
    public Programmer(String name, Date dateOfBirth) {super(name, dateOfBirth);}

    public Vector<String> getLanguages() {
        return languages;
    }

    public void setLanguages(Vector<String> languages) {
        this.languages = languages;
    }
}
