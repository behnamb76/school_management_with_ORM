package ir.maktabsharif.Repository.impls;

import ir.maktabsharif.Repository.StudentRepository;
import ir.maktabsharif.exceptions.NotFoundException;
import ir.maktabsharif.model.Student;
import ir.maktabsharif.util.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository {
    private final EntityManager em;

    public StudentRepositoryImpl() {
        this.em = EntityManagerProvider.getEntityManager();
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = em.createQuery("from Student", Student.class).getResultList();
        return students;
    }

    @Override
    public void add(Student student) {
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void update(Student student) throws NotFoundException {
        try {
            Optional<Student> studentOptional = findById(student.getId());
            if (studentOptional.isPresent()) {
                Student foundStudent = studentOptional.get();
                em.getTransaction().begin();
                foundStudent.setFirstName(student.getFirstName());
                foundStudent.setLastName(student.getLastName());
                foundStudent.setNationalCode(student.getNationalCode());
                foundStudent.setDob(student.getDob());
                foundStudent.setGpu(student.getGpu());
                em.getTransaction().commit();
            } else {
                throw new NotFoundException("Student with id of ".concat(String.valueOf(student.getId())).concat(" not found!"));
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Optional<Student> studentOptional = findById(id);
        if (studentOptional.isPresent()) {
            em.getTransaction().begin();
            em.remove(studentOptional.get());
            em.getTransaction().commit();
        } else {
            throw new NotFoundException("Student with id of ".concat(String.valueOf(id)).concat(" not found!"));
        }
    }

    @Override
    public Optional<Student> findById(Long id) {
        Student studentOptional = em.find(Student.class, id);
        return Optional.ofNullable(studentOptional);
    }

    @Override
    public Long countStudents() {
        return em.createQuery("select count(*) from Student", Long.class).getSingleResult();
    }
}
