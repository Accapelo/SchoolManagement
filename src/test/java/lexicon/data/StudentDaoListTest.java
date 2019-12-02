package lexicon.data;

import lexicon.Student;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class StudentDaoListTest {

    @Before
    public void init(){
        Student.setIdGen(1000);
        StudentDaoList.clearStudents();
    }

    @Test
    public void saveStudentTest()
    {
        Student tobbe = new Student("Tobias","a@b.com","Sveagatan");
        StudentDaoList studenter = new StudentDaoList();

        studenter.saveStudent(tobbe);

        assertTrue(studenter.findAll().get(0).equals(tobbe));
    }

    @Test
    public void deleteStudentTest()
    {
        Student tobbe = new Student("Tobias","a@b.com","Sveagatan");
        Student adam = new Student("Adam","a@bc.com","Sveagatan 1");
        StudentDaoList studenter = new StudentDaoList();
        studenter.saveStudent(tobbe);
        studenter.saveStudent(adam);

        studenter.deleteStudent(tobbe);

        assertTrue(studenter.findAll().size()==1);
        assertTrue(studenter.findAll().get(0).equals(adam));
    }

    @Test
    public void findByEmailTest()
    {
        Student tobbe = new Student("Tobias","a@b.com","Sveagatan");
        Student adam = new Student("Adam","a@bc.com","Sveagatan 1");
        StudentDaoList studenter = new StudentDaoList();
        studenter.saveStudent(tobbe);
        studenter.saveStudent(adam);

        Student found = studenter.findByEmail("a@bc.com");

        assertTrue(found.equals(adam));
    }

    @Test
    public void findByNameTest()
    {
        Student tobbe = new Student("Tobias","a@b.com","Sveagatan");
        Student adam = new Student("Adam","a@bc.com","Sveagatan 1");
        Student tobbe2 = new Student("Tobias","a@be.com","Sveagatan");
        StudentDaoList studenter = new StudentDaoList();
        studenter.saveStudent(tobbe);
        studenter.saveStudent(adam);
        studenter.saveStudent(tobbe2);

        List<Student> foundName = studenter.findByName("Tobias");

        assertTrue(foundName.size()==2);
        assertTrue(foundName.get(0).equals(tobbe));
        assertTrue(foundName.get(1).equals(tobbe2));
    }

    @Test
    public void findByIdTest()
    {
        Student tobbe = new Student("Tobias","a@b.com","Sveagatan");
        Student adam = new Student("Adam","a@bc.com","Sveagatan 1");
        StudentDaoList studenter = new StudentDaoList();
        studenter.saveStudent(tobbe);
        studenter.saveStudent(adam);

        Student found = studenter.findById(1001);

        assertTrue(found.equals(adam));
    }

}
