package lexicon;

public class Student {
    private static int idGen = 1000;
    private int id;
    private String name;
    private String email;
    private String address;

    public Student(String name, String email, String address) {
        this.id = idGen++;
        this.name = name;
        this.email = email;
        this.address=address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static void setIdGen(int idGen) {
        Student.idGen = idGen;
    }
}
