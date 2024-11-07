package ir.maktabsharif;

import ir.maktabsharif.Repository.CourseRepository;
import ir.maktabsharif.Repository.ExamRepository;
import ir.maktabsharif.Repository.StudentRepository;
import ir.maktabsharif.Repository.TeacherRepository;
import ir.maktabsharif.Repository.impls.CourseRepositoryImpl;
import ir.maktabsharif.Repository.impls.ExamRepositoryImpl;
import ir.maktabsharif.Repository.impls.StudentRepositoryImpl;
import ir.maktabsharif.Repository.impls.TeacherRepositoryImpl;
import ir.maktabsharif.model.Course;
import ir.maktabsharif.model.Exam;
import ir.maktabsharif.model.Student;
import ir.maktabsharif.model.Teacher;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentRepositoryImpl();
        TeacherRepository teacherRepository = new TeacherRepositoryImpl();
        CourseRepository courseRepository = new CourseRepositoryImpl();
        ExamRepository examRepository = new ExamRepositoryImpl();

        // Student CRUD
//        Student student  = Student.builder()
//                .firstName("Behnam")
//                .lastName("Borhani")
//                .dob(new Date(1997 - 1900, 9 - 1, 24))
//                .gpu(15.5F)
//                .nationalCode("111").build();
//        studentRepository.add(student);

//        studentRepository.update(student);

//        System.out.println(studentRepository.findById(1L).get());

//        studentRepository.findAll().forEach(System.out::println);

//        studentRepository.delete(1L);


        // Teacher CRUD
//        Teacher teacher = Teacher.builder()
//                .firstName("Ali")
//                .lastName("Najafi")
//                .dob(new Date(1993 - 1900, 2 - 1, 2))
//                .nationalCode("333").build();
//        teacherRepository.add(teacher);

//        teacherRepository.update(teacher);

//        System.out.println(teacherRepository.findById(4L).get());

//        teacherRepository.findAll().forEach(System.out::println);

//        teacherRepository.delete(4L);


        // Course CRUD
//        Course course = Course.builder()
//                .title("Database")
//                .unit(2).build();
//        courseRepository.add(course);

//        courseRepository.update(course);

//        System.out.println(courseRepository.findById(6L).get());

//        courseRepository.findAll().forEach(System.out::println);

//        courseRepository.delete(6L);


        // Exam CRUD
//        Exam exam = Exam.builder()
//                .examDate(new Date(2025 - 1900, 2 - 1, 4))
//                .build();
//        examRepository.add(exam);

//        System.out.println(examRepository.findById(7L).get());

//        examRepository.findAll().forEach(System.out::println);

//        examRepository.delete(8L);


        //Thread
//        StudentCountThread studentCountThread = new StudentCountThread();
//        Thread thread = new Thread(studentCountThread);
//        thread.start();

    }
}
