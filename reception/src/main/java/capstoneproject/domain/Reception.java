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

        TreatmentCanceled treatmentCanceled = new TreatmentCanceled(this);
        treatmentCanceled.publishAfterCommit();
    }

    public static ReceptionRepository repository() {
        ReceptionRepository receptionRepository = ReceptionApplication.applicationContext.getBean(
            ReceptionRepository.class
        );
        return receptionRepository;
    }

    public void receive() {
        //implement business logic here:

        TreatmentReceived treatmentReceived = new TreatmentReceived(this);
        treatmentReceived.publishAfterCommit();
    }

    //<<< Clean Arch / Port Method
    public static void updateReceptionStatus(
        DiagnosisRejected diagnosisRejected
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Reception reception = new Reception();
        repository().save(reception);

        TreatmentReceived treatmentReceived = new TreatmentReceived(reception);
        treatmentReceived.publishAfterCommit();
        TreatmentCanceled treatmentCanceled = new TreatmentCanceled(reception);
        treatmentCanceled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(diagnosisRejected.get???()).ifPresent(reception->{
            
            reception // do something
            repository().save(reception);

            TreatmentReceived treatmentReceived = new TreatmentReceived(reception);
            treatmentReceived.publishAfterCommit();
            TreatmentCanceled treatmentCanceled = new TreatmentCanceled(reception);
            treatmentCanceled.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateReceptionStatus(
        DiagnosisCompleted diagnosisCompleted
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Reception reception = new Reception();
        repository().save(reception);

        TreatmentReceived treatmentReceived = new TreatmentReceived(reception);
        treatmentReceived.publishAfterCommit();
        TreatmentCanceled treatmentCanceled = new TreatmentCanceled(reception);
        treatmentCanceled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(diagnosisCompleted.get???()).ifPresent(reception->{
            
            reception // do something
            repository().save(reception);

            TreatmentReceived treatmentReceived = new TreatmentReceived(reception);
            treatmentReceived.publishAfterCommit();
            TreatmentCanceled treatmentCanceled = new TreatmentCanceled(reception);
            treatmentCanceled.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
