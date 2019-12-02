package lexicon;

import lexicon.data.CourseDaoList;
import lexicon.data.StudentDaoList;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static Scanner scan = new Scanner(System.in);
    public static void main( String[] args )
    {
        CourseDaoList courses = new CourseDaoList();
        StudentDaoList students = new StudentDaoList();
        boolean loop=true;


        String input;
        while(loop){

            System.out.println("What would you like to do?");
            System.out.println("(1) Add course/student.");
            System.out.println("(2) Register/remove student from course.");
            System.out.println("(3) Find student/course.");
            System.out.println("(4) Edit student/course.");
            System.out.println("(5) Quit.");

            input = scan.nextLine();

            switch(input){
                case "1":
                    addSC(courses, students);
                    break;
                case "2":
                    regRem(courses, students);
                    break;
                case "3":
                    find(courses,students);
                    break;
                case "4":
                    edit(courses, students);
                    break;
                case "5":
                    loop=false;
            }

        }
    }

    private static void addSC(CourseDaoList courses, StudentDaoList students){
        System.out.println("Would you like to add (1)student or (2)course?");
        String input = scan.nextLine();
        if(input.equals("1")){
            addStudent(students);
        }else if(input.equals("2")){
            addCourse(courses);
        }else{
            System.out.println("Invalid input, back to main menu");
        }
    }

    private static void addStudent(StudentDaoList students){
        System.out.println("Input student name.");
        String input1 = scan.nextLine();
        System.out.println("Input student email.");
        String input2 = scan.nextLine();
        System.out.println("Input student adress.");
        String input3 = scan.nextLine();
        Student newStudent = new Student(input1,input2,input3);
        students.saveStudent(newStudent);
    }

    private static void addCourse(CourseDaoList courses){
        System.out.println("Input course name.");
        String input1 = scan.nextLine();
        System.out.println("Input date of course start, example: YYYY-MM-DD.");
        LocalDate input2 = LocalDate.parse(scan.nextLine());
        System.out.println("Input course duration in week.");
        int input3 = Integer.parseInt(scan.nextLine());
        Course newCourse = new Course(input1,input2,input3);
        courses.saveCourse(newCourse);
    }

    private static void regRem(CourseDaoList courses, StudentDaoList students){
        System.out.println("Would you like to (1)register or (2)remove student.");
        String input = scan.nextLine();
        if(input.equals("1")){
            register(courses, students);
        }else if (input.equals("2")){
            remove(courses, students);
        }else{
            System.out.println("Invalid input, back to main menu");
        }

    }

    private static void register(CourseDaoList courses, StudentDaoList students){
        System.out.println("Choose student by studentId or email.");
        Student found;
        Course course;
        String input = scan.nextLine();
        found = students.findByEmail(input);
        if(found == null){
            int inputInt = Integer.parseInt(input);
            found = students.findById(inputInt);
        }

        if(found == null){
            System.out.println("System could not find matching person.");
        }else{
            System.out.println("Chose course to add student to by courseId.");
            int inputI = Integer.parseInt(scan.nextLine());
            course = courses.findById(inputI);
            if(course==null){
                System.out.println("System could not find matching course.");
            }else{
                course.addStudent(found);
                System.out.println("Student added to course.");
            }
        }
    }

    private static void remove(CourseDaoList courses, StudentDaoList students){
        System.out.println("Choose student by studentId or email.");
        Student found;
        Course course;
        String input = scan.nextLine();
        found = students.findByEmail(input);
        if(found == null){
            int inputInt = Integer.parseInt(input);
            found = students.findById(inputInt);
        }

        if(found == null){
            System.out.println("System could not find matching person.");
        }else{
            System.out.println("Chose course to remove student from by courseId.");
            int inputI = Integer.parseInt(scan.nextLine());
            course = courses.findById(inputI);
            if(course==null){
                System.out.println("System could not find matching course.");
            }else{
                course.removeStudent(found);
                System.out.println("Student added to course.");
            }
        }
    }

    private static void find (CourseDaoList courses, StudentDaoList students){
        System.out.println("Would you like to find a (1)student or a (2)course?");
        String input = scan.nextLine();
        if(input.equals("1")){
            findS(students);
        }else if (input.equals("2")){
            findC(courses);
        }else{
            System.out.println("Invalid input, back to main menu");
        }
    }

    public static Student findS( StudentDaoList students){
        System.out.println("Search for student by entering name, id or email.");
        String input = scan.nextLine();
        Student found2;
        List<Student> nameList;

        found2 = students.findByEmail(input);

        if(found2!=null){
            System.out.println(found2.getName()+": "+found2.getAddress()+", "+found2.getEmail()+", #"+found2.getId());
            return null;
        }

        nameList=students.findByName(input);

        if(nameList.size()>0){
            for (int i = 0; i < nameList.size(); i++) {
                System.out.println(nameList.get(i).getName()+": "+nameList.get(i).getAddress()+", "+nameList.get(i).getEmail()+", #"+nameList.get(i).getId());
            }
            return null;
        }

        int id = Integer.parseInt(input);
        found2 = students.findById(id);

        if (found2 != null){
            System.out.println(found2.getName()+": "+found2.getAddress()+", "+found2.getEmail()+", #"+found2.getId());
            return null;
        }

        System.out.println("Could not find person of reference.");
        return null;

    }

    public static Course findC(CourseDaoList courses){
        System.out.println("Search for course by entering course name, id or start date (date written like YYYY-MM-DD).");
        String input = scan.nextLine();
        Course found2;
        List<Course> nameList;

        nameList = courses.findByName(input);

        if(nameList.size()>0){
            for (int i = 0; i < nameList.size(); i++) {
                System.out.println(nameList.get(i).getCourseName()+": "+nameList.get(i).getStartDate()+", "+nameList.get(i).getWeekDuration()+" weeks, #"+nameList.get(i).getId());
                printStudents(nameList.get(i));
            }
            return null;
        }

        LocalDate date = LocalDate.parse(input);
        nameList = courses.findByDate(date);

        if(nameList.size()>0){
            for (int i = 0; i < nameList.size(); i++) {
                System.out.println(nameList.get(i).getCourseName()+": "+nameList.get(i).getStartDate()+", "+nameList.get(i).getWeekDuration()+" weeks, #"+nameList.get(i).getId());
                printStudents(nameList.get(i));
            }
            return null;
        }

        int id = Integer.parseInt(input);
        found2 = courses.findById(id);

        if (found2 != null){
            System.out.println(found2.getCourseName()+": "+found2.getStartDate()+", "+found2.getWeekDuration()+" weeks, #"+found2.getId());
            printStudents(found2);
            return null;
        }

        System.out.println("Could not find course of reference.");
        return null;
    }

    public static void printStudents(Course course){
        List<Student> nameList = course.getStudents();
        System.out.println("Attending students:");
        for (int i = 0; i < nameList.size(); i++) {
            System.out.println(nameList.get(i).getName()+": "+nameList.get(i).getAddress()+", "+nameList.get(i).getEmail()+", #"+nameList.get(i).getId());
        }
    }

    public static void edit(CourseDaoList courses, StudentDaoList students){
        System.out.println("Would you like to edit a (1)student or a (2)course?");
        String input = scan.nextLine();
        if(input.equals("1")){
            editS(students);
        }else if (input.equals("2")){
            editC(courses);
        }else{
            System.out.println("Invalid input, back to main menu");
        }
    }

    public static Student editS(StudentDaoList students){
        System.out.println("Pick student by entering id or email.");
        String input = scan.nextLine();
        Student found2;

        found2 = students.findByEmail(input);

        if(found2!=null){
            editElement(found2);
            return null;
        }

        int id = Integer.parseInt(input);
        found2 = students.findById(id);

        if (found2 != null){
            editElement(found2);
            return null;
        }

        System.out.println("Could not find person of reference.");
        return null;
    }

    public static void editElement(Student student){
        System.out.println("Change (1)name, (2)email or (3)address?");
        String input = scan.nextLine();

        switch (input){
            case "1":
                System.out.println("Set new name.");
                input=scan.nextLine();
                student.setName(input);
                break;
            case "2":
                System.out.println("Set new email.");
                input=scan.nextLine();
                student.setEmail(input);
                break;
            case "3":
                System.out.println("Set new address.");
                input=scan.nextLine();
                student.setAddress(input);
                break;
            default:
                System.out.println("Invalid input.");
        }
    }

    public static Course editC(CourseDaoList courses){
        System.out.println("Pick course by entering id.");
        String input = scan.nextLine();
        Course found2;

        int id = Integer.parseInt(input);
        found2 = courses.findById(id);

        if (found2 != null){
            editElement(found2);
            return null;
        }

        System.out.println("Could not find person of reference.");
        return null;
    }

    public static void editElement(Course course){
        System.out.println("Change (1)name, (2)week duration or (3)start date?");
        String input = scan.nextLine();

        switch (input){
            case "1":
                System.out.println("Set new name.");
                input=scan.nextLine();
                course.setCourseName(input);
                break;
            case "2":
                System.out.println("Set new week duration.");
                int input2=Integer.parseInt(scan.nextLine());
                course.setWeekDuration(input2);
                break;
            case "3":
                System.out.println("Set new start date (YYYY-XX-DD).");
                LocalDate date=LocalDate.parse(scan.nextLine());
                course.setStartDate(date);
                break;
            default:
                System.out.println("Invalid input.");
        }
    }

}
