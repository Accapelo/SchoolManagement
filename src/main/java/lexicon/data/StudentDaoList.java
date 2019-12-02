package lexicon.data;

import lexicon.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoList implements StudentDAO {
    private static List<Student> students = new ArrayList<>(0);

    @Override
    public Student saveStudent(Student student) {
        students.add(student);
        return student;
    }

    @Override
    public Student findByEmail(String email) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getEmail().equals(email)){
                return students.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Student> findByName(String name) {

        List<Student> studentsOfName = new ArrayList<>();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equals(name)) {
                studentsOfName.add(students.get(i));
            }
        }
        return studentsOfName;
    }

    @Override
    public Student findById(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId()==id){
                return students.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public boolean deleteStudent(Student student) {
        for (int i = 0; i < students.size() ; i++) {
            if(students.get(i)==student){
                students.remove(student);
                return true;
            }
        }
        return false;
    }

    public static void clearStudents(){
        students.clear();
    }

}
