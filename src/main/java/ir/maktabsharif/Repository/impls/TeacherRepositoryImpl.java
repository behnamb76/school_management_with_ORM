package ir.maktabsharif.Repository.impls;

import ir.maktabsharif.Repository.TeacherRepository;
import ir.maktabsharif.exceptions.NotFoundException;
import ir.maktabsharif.model.Teacher;
import ir.maktabsharif.util.EntityManagerProvider;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TeacherRepositoryImpl implements TeacherRepository {
    private final EntityManager em;

    public TeacherRepositoryImpl() {
        this.em = EntityManagerProvider.getEntityManager();
    }

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teachers = em.createQuery("from Teacher", Teacher.class).getResultList();
        return teachers;
    }

    @Override
    public void add(Teacher teacher) {
        try {
            em.getTransaction().begin();
            em.persist(teacher);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void update(Teacher teacher) throws NotFoundException {
        try {
            Optional<Teacher> teacherOptional = findById(teacher.getId());
            if (teacherOptional.isPresent()) {
                Teacher foundTeacher = teacherOptional.get();
                em.getTransaction().begin();
                foundTeacher.setFirstName(teacher.getFirstName());
                foundTeacher.setLastName(teacher.getLastName());
                foundTeacher.setNationalCode(teacher.getNationalCode());
                foundTeacher.setDob(teacher.getDob());
                em.getTransaction().commit();
            } else {
                throw new NotFoundException("Teacher with id of ".concat(String.valueOf(teacher.getId())).concat(" not found!"));
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Teacher> teacherOptional = findById(id);
        if (teacherOptional.isPresent()) {
            em.getTransaction().begin();
            em.remove(teacherOptional.get());
            em.getTransaction().commit();
        } else {
            throw new NotFoundException("Teacher with id of ".concat(String.valueOf(id)).concat(" not found!"));
        }
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        Teacher teacherOptional = em.find(Teacher.class, id);
        return Optional.ofNullable(teacherOptional);
    }
}
