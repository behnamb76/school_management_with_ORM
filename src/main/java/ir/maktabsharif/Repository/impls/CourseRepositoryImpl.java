package ir.maktabsharif.Repository.impls;

import ir.maktabsharif.Repository.CourseRepository;
import ir.maktabsharif.exceptions.NotFoundException;
import ir.maktabsharif.model.Course;
import ir.maktabsharif.util.EntityManagerProvider;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class CourseRepositoryImpl implements CourseRepository {
    private final EntityManager em;

    public CourseRepositoryImpl() {
        this.em = EntityManagerProvider.getEntityManager();
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = em.createQuery("from Course", Course.class).getResultList();
        return courses;
    }

    @Override
    public void add(Course course) {
        try {
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void update(Course course) throws NotFoundException {
        try {
            Optional<Course> courseOptional = findById(course.getId());
            if (courseOptional.isPresent()) {
                Course foundCourse = courseOptional.get();
                em.getTransaction().begin();
                foundCourse.setTitle(course.getTitle());
                foundCourse.setUnit(course.getUnit());
                em.getTransaction().commit();
            } else {
                throw new NotFoundException("Course with id of ".concat(String.valueOf(course.getId())).concat(" not found!"));
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Optional<Course> courseOptional = findById(id);
        if (courseOptional.isPresent()) {
            em.getTransaction().begin();
            em.remove(courseOptional.get());
            em.getTransaction().commit();
        } else {
            throw new NotFoundException("Course with id of ".concat(String.valueOf(id)).concat(" not found!"));
        }
    }

    @Override
    public Optional<Course> findById(Long id) {
        Course courseOptional = em.find(Course.class, id);
        return Optional.ofNullable(courseOptional);
    }
}
