package ir.maktabsharif.Repository.impls;

import ir.maktabsharif.Repository.ExamRepository;
import ir.maktabsharif.exceptions.NotFoundException;
import ir.maktabsharif.model.Exam;
import ir.maktabsharif.util.EntityManagerProvider;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ExamRepositoryImpl implements ExamRepository {
    private final EntityManager em;

    public ExamRepositoryImpl() {
        this.em = EntityManagerProvider.getEntityManager();
    }

    @Override
    public List<Exam> findAll() {
        List<Exam> exams = em.createQuery("from Exam", Exam.class).getResultList();
        return exams;
    }

    @Override
    public void add(Exam exam) {
        try {
            em.getTransaction().begin();
            em.persist(exam);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void update(Exam exam) throws NotFoundException {
        try {
            Optional<Exam> examOptional = findById(exam.getId());
            if (examOptional.isPresent()) {
                Exam foundExam = examOptional.get();
                em.getTransaction().begin();
                foundExam.setExamDate(exam.getExamDate());
                foundExam.setScore(exam.getScore());
                em.getTransaction().commit();
            } else {
                throw new NotFoundException("Exam with id of ".concat(String.valueOf(exam.getId())).concat(" not found!"));
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Optional<Exam> examOptional = findById(id);
        if (examOptional.isPresent()) {
            em.getTransaction().begin();
            em.remove(examOptional.get());
            em.getTransaction().commit();
        } else {
            throw new NotFoundException("Exam with id of ".concat(String.valueOf(id)).concat(" not found!"));
        }
    }

    @Override
    public Optional<Exam> findById(Long id) {
        Exam examOptional = em.find(Exam.class, id);
        return Optional.ofNullable(examOptional);
    }
}
