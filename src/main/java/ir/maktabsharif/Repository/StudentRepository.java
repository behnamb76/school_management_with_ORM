package ir.maktabsharif.Repository;

import ir.maktabsharif.model.Student;

public interface StudentRepository extends BaseRepository<Student> {
    Long countStudents();
}
