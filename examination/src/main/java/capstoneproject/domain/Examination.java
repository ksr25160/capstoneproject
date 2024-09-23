package capstoneproject.domain;

import capstoneproject.ExaminationApplication;
import capstoneproject.domain.ExaminationCanceled;
import capstoneproject.domain.ExaminationCompleted;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Examination_table")
@Data
// <<< DDD / Aggregate Root
public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long patientId;

    private Long examId;

    private Date examDt;

    private String status;

    @PostPersist
    public void onPostPersist() {
    }

    @PostUpdate
    public void onPostUpdate() {

        if (this.getStatus() != null) {

            if (this.getStatus().equals("검사완료")) {

                ExaminationCompleted examinationCompleted = new ExaminationCompleted(this);
                examinationCompleted.publishAfterCommit();

            } else if (this.getStatus().equals("검사거부")) {

                ExaminationCanceled examinationCanceled = new ExaminationCanceled(this);
                examinationCanceled.publishAfterCommit();
            }
        }
    }

    public static ExaminationRepository repository() {
        ExaminationRepository diagnosisRepository = ExaminationApplication.applicationContext.getBean(
                ExaminationRepository.class);
        return diagnosisRepository;
    }

    // <<< Clean Arch / Port Method
    public static void prescriptionInfoTransfer(Prescribed prescribed) {

        Examination exam = new Examination();

        exam.setPatientId(prescribed.getPatientId());
        exam.setExamId(prescribed.getId());

        repository().save(exam);

    }
    // >>> Clean Arch / Port Method

}
// >>> DDD / Aggregate Root
