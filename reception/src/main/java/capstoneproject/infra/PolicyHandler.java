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
    ReceptionRepository receptionRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DiagnosisRejected'"
    )
    public void wheneverDiagnosisRejected_UpdateReceptionStatus(
        @Payload DiagnosisRejected diagnosisRejected
    ) {
        DiagnosisRejected event = diagnosisRejected;
        System.out.println(
            "\n\n##### listener UpdateReceptionStatus : " +
            diagnosisRejected +
            "\n\n"
        );

        // Sample Logic //
        Reception.updateReceptionStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DiagnosisCompleted'"
    )
    public void wheneverDiagnosisCompleted_UpdateReceptionStatus(
        @Payload DiagnosisCompleted diagnosisCompleted
    ) {
        DiagnosisCompleted event = diagnosisCompleted;
        System.out.println(
            "\n\n##### listener UpdateReceptionStatus : " +
            diagnosisCompleted +
            "\n\n"
        );

        // Sample Logic //
        Reception.updateReceptionStatus(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
