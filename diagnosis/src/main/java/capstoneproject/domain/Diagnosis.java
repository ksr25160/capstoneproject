package capstoneproject.domain;

import capstoneproject.domain.Prescribed;
import capstoneproject.domain.DiagnosisCompleted;
import capstoneproject.domain.DiagnosisRejected;
import capstoneproject.DiagnosisApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.time.LocalDate;


@Entity
@Table(name="Diagnosis_table")
@Data

//<<< DDD / Aggregate Root
public class Diagnosis  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    private Long id;
    
    
    
    
    private Long patientId;
    
    
    
    
    private Date receptionDt;
    
    
    
    
    private String priscribeCode;
    
    
    
    
    private Date priscribeDt;
    
    
    
    
    private String diagnosisStatus;

    @PostPersist
    public void onPostPersist(){


        Prescribed prescribed = new Prescribed(this);
        prescribed.publishAfterCommit();



        DiagnosisCompleted diagnosisCompleted = new DiagnosisCompleted(this);
        diagnosisCompleted.publishAfterCommit();



        DiagnosisRejected diagnosisRejected = new DiagnosisRejected(this);
        diagnosisRejected.publishAfterCommit();

    
    }

    public static DiagnosisRepository repository(){
        DiagnosisRepository diagnosisRepository = DiagnosisApplication.applicationContext.getBean(DiagnosisRepository.class);
        return diagnosisRepository;
    }



    public void patientPrescribe(){
        //implement business logic here:
        
        Prescribed prescribed = new Prescribed(this);
        prescribed.publishAfterCommit();
        
        
    }
    public void patientDiagnosis(){
        //implement business logic here:
        
        DiagnosisRejected diagnosisRejected = new DiagnosisRejected(this);
        diagnosisRejected.publishAfterCommit();
        
        
        capstoneproject.external.DiagnosisQuery diagnosisQuery = new capstoneproject.external.DiagnosisQuery();
        DiagnosisApplication.applicationContext
            .getBean(capstoneproject.external.Service.class)
            .( diagnosisQuery);
    }

//<<< Clean Arch / Port Method
    public static void patientInfoTransfer(TreatmentReceived treatmentReceived){
        
        //implement business logic here:

        /** Example 1:  new item 
        Diagnosis diagnosis = new Diagnosis();
        repository().save(diagnosis);

        */

        /** Example 2:  finding and process
        
        repository().findById(treatmentReceived.get???()).ifPresent(diagnosis->{
            
            diagnosis // do something
            repository().save(diagnosis);


         });
        */

        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void updatePrescribeStatus(ExaminationCanceled examinationCanceled){
        
        //implement business logic here:

        /** Example 1:  new item 
        Diagnosis diagnosis = new Diagnosis();
        repository().save(diagnosis);

        */

        /** Example 2:  finding and process
        
        repository().findById(examinationCanceled.get???()).ifPresent(diagnosis->{
            
            diagnosis // do something
            repository().save(diagnosis);


         });
        */

        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void updatePrescribeStatus(ExaminationCompleted examinationCompleted){
        
        //implement business logic here:

        /** Example 1:  new item 
        Diagnosis diagnosis = new Diagnosis();
        repository().save(diagnosis);

        */

        /** Example 2:  finding and process
        
        repository().findById(examinationCompleted.get???()).ifPresent(diagnosis->{
            
            diagnosis // do something
            repository().save(diagnosis);


         });
        */

        
    }
//>>> Clean Arch / Port Method


}
//>>> DDD / Aggregate Root
