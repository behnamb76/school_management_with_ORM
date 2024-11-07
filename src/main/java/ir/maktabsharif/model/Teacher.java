package ir.maktabsharif.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Teacher extends BaseModel {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    @Column(name = "national_code", unique = true)
    private String nationalCode;
    @Temporal(TemporalType.DATE)
    private Date dob;
    @OneToOne
    @JoinColumn(name = "fk_course")
    private Course course;
}
