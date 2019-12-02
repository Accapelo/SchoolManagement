package lexicon.data;

import lexicon.Course;
import lexicon.data.CourseDAO;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CourseDAOListTest {

    @Before
    public void init(){
        Course.setIdGen(10);
        CourseDaoList.clearCourses();
    }

    @Test
    public void saveCourseTest()
    {
        LocalDate date = LocalDate.of(2019,10,23);
        Course computer = new Course("Programing", date, 26);
        CourseDaoList courses = new CourseDaoList();

        courses.saveCourse(computer);

        assertTrue(courses.findByDate(date).get(0).equals(computer));
    }

    @Test
    public void removeCourseTest(){

        LocalDate date = LocalDate.of(2019,11,22);
        Course computer = new Course("Programing", date, 26);
        Course math = new Course("Math", date, 26);
        CourseDaoList courses = new CourseDaoList();
        courses.saveCourse(computer);
        courses.saveCourse(math);

        courses.removeCourse(computer);

        assertTrue(courses.findAll().get(0).equals(math));

    }

    @Test
    public void findByIdTest(){

        LocalDate date = LocalDate.of(2019,11,22);
        Course computer = new Course("Programing", date, 26);
        Course math = new Course("Math", date, 26);
        Course bio = new Course("Biology", date, 26);
        CourseDaoList courses = new CourseDaoList();
        courses.saveCourse(computer);
        courses.saveCourse(math);
        courses.saveCourse(bio);

        Course foundCourse = courses.findById(12);

        assertTrue(foundCourse.equals(bio));

    }

    @Test
    public void findByNameTest(){

        LocalDate date = LocalDate.of(2019,11,22);
        Course computer = new Course("Programing", date, 26);
        Course math = new Course("Math", date, 26);
        Course bio = new Course("Biology", date, 26);
        Course math2 = new Course("Math", date, 26);
        CourseDaoList courses = new CourseDaoList();
        courses.saveCourse(computer);
        courses.saveCourse(math);
        courses.saveCourse(bio);
        courses.saveCourse(math2);

        List<Course> foundCourses = courses.findByName("Math");

        assertTrue(foundCourses.size()==2);
        assertTrue(foundCourses.get(0).equals(math));
        assertTrue(foundCourses.get(1).equals(math2));


    }


}
