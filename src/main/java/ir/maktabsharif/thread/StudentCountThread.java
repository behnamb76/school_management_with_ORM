package ir.maktabsharif.thread;

import ir.maktabsharif.Repository.StudentRepository;
import ir.maktabsharif.Repository.impls.StudentRepositoryImpl;

public class StudentCountThread implements Runnable {
    private final StudentRepository studentRepository;

    public StudentCountThread() {
        this.studentRepository = new StudentRepositoryImpl();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Long studentCount = studentRepository.countStudents();
                System.out.println("Total number of students: " + studentCount);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }
}
