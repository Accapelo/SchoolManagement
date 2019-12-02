package lexicon.data;

import lexicon.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoList implements CourseDAO{

    private static ArrayList<Course> courses = new ArrayList<>(0);

    @Override
    public Course saveCourse(Course course) {
        courses.add(course);
        return course;
    }

    @Override
    public Course findById(int id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId()==id){
                return courses.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Course> findByName(String name) {
        List<Course> coursesOfName = new ArrayList<>();

        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseName().equals(name)) {
                coursesOfName.add(courses.get(i));
            }
        }
        return coursesOfName;
    }

    @Override
    public List<Course> findByDate(LocalDate date) {
        List<Course> coursesOfName = new ArrayList<>();

        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getStartDate().equals(date)) {
                coursesOfName.add(courses.get(i));
            }
        }
        return coursesOfName;
    }

    @Override
    public List<Course> findAll() {
        return courses;
    }

    @Override
    public boolean removeCourse(Course course) {
        for (int i = 0; i < courses.size() ; i++) {
            if(courses.get(i).equals(course)){
                courses.remove(course);
                return true;
            }
        }
        return false;
    }

    public static void clearCourses(){
        courses.clear();
    }

}
