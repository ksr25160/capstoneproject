package capstoneproject.infra;

import capstoneproject.config.kafka.KafkaProcessor;
import capstoneproject.domain.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    DiagnosisRepository diagnosisRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='TreatmentReceived'"
    )
    public void wheneverTreatmentReceived_PatientInfoTransfer(
        @Payload TreatmentReceived treatmentReceived
    ) {
        TreatmentReceived event = treatmentReceived;
        System.out.println(
            "\n\n##### listener PatientInfoTransfer : " +
            treatmentReceived +
            "\n\n"
        );

        // Sample Logic //
        Diagnosis.patientInfoTransfer(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ExaminationCanceled'"
    )
    public void wheneverExaminationCanceled_UpdatePrescribeStatus(
        @Payload ExaminationCanceled examinationCanceled
    ) {
        ExaminationCanceled event = examinationCanceled;
        System.out.println(
            "\n\n##### listener UpdatePrescribeStatus : " +
            examinationCanceled +
            "\n\n"
        );

        // Sample Logic //
        Diagnosis.updatePrescribeStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ExaminationCompleted'"
    )
    public void wheneverExaminationCompleted_UpdatePrescribeStatus(
        @Payload ExaminationCompleted examinationCompleted
    ) {
        ExaminationCompleted event = examinationCompleted;
        System.out.println(
            "\n\n##### listener UpdatePrescribeStatus : " +
            examinationCompleted +
            "\n\n"
        );

        // Sample Logic //
        Diagnosis.updatePrescribeStatus(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
