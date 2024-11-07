package ir.maktabsharif.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Exam extends BaseModel {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "exam_date")
    private Date examDate;
    private Float score;

    @ManyToOne
    @JoinColumn(name = "fk_course")
    private Course course;

    @ManyToMany(mappedBy = "exams")
    private List<Student> students = new ArrayList<>();
}
