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
    ExaminationRepository examinationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Prescribed'"
    )
    public void wheneverPrescribed_PrescriptionInfoTransfer(
        @Payload Prescribed prescribed
    ) {
        Prescribed event = prescribed;
        System.out.println(
            "\n\n##### listener PrescriptionInfoTransfer : " +
            prescribed +
            "\n\n"
        );

        // Sample Logic //
        Examination.prescriptionInfoTransfer(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
