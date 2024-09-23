package capstoneproject.domain;

import capstoneproject.ReceptionApplication;
import capstoneproject.domain.TreatmentCanceled;
import capstoneproject.domain.TreatmentReceived;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Reception_table")
@Data
//<<< DDD / Aggregate Root
public class Reception {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long patientId;

    private Date receptionDt;

    private String patientNm;

    private String address;

    private String status;

    @PostPersist
    public void onPostPersist() {


        TreatmentReceived treatmentReceived = new TreatmentReceived(this);
        treatmentReceived.publishAfterCommit();

  
    }

    public static ReceptionRepository repository() {
        ReceptionRepository receptionRepository = ReceptionApplication.applicationContext.getBean(
            ReceptionRepository.class
        );
        return receptionRepository;
    }

    //<<< Clean Arch / Port Method
    public static void updateReceptionStatus(
        DiagnosisRejected diagnosisRejected
    ) {

        
        repository().findById(diagnosisRejected.getPatientId()).ifPresent(reception->{
            
            if(reception != null){

                reception.setStatus("진료거부");
                repository().save(reception);
            }

         });
    

    }
    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void updateReceptionStatus(
        DiagnosisCompleted diagnosisCompleted
    ) {
        repository().findById(diagnosisCompleted.getPatientId()).ifPresent(reception->{
            
            if(reception != null){

                reception.setStatus("진료완료");
                repository().save(reception);
            }

         });

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
