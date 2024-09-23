package capstoneproject.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "PatientManagment_table")
@Data
public class PatientManagment {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long patientId;
    private Long diagnosisId;
    private Long examId;
    private Date receptionDt;
    private String status;
}
