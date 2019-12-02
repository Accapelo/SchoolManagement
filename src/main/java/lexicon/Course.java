package lexicon;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private static int idGen = 10;
    private int id;
    private String courseName;
    private LocalDate startDate;
    private int weekDuration;
    private static List<Student> students = new ArrayList<>(0);

    public Course(String courseName, LocalDate startDate, int weekDuration) {
        this.id = idGen++;
        this.courseName = courseName;
        this.startDate = startDate;
        this.weekDuration = weekDuration;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void removeStudent(Student student){
        students.remove(student);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getWeekDuration() {
        return weekDuration;
    }

    public void setWeekDuration(int weekDuration) {
        this.weekDuration = weekDuration;
    }

    public List<Student> getStudents() {
        return students;
    }

    public static void setIdGen(int idGen) {
        Course.idGen = idGen;
    }
}
