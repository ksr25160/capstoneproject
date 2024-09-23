package capstoneproject.infra;

import capstoneproject.config.kafka.KafkaProcessor;
import capstoneproject.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PatientManagmentViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private PatientManagmentRepository patientManagmentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenTreatmentReceived_then_CREATE_1(
        @Payload TreatmentReceived treatmentReceived
    ) {
        try {
            if (!treatmentReceived.validate()) return;

            // view 객체 생성
            PatientManagment patientManagment = new PatientManagment();
            // view 객체에 이벤트의 Value 를 set 함
            patientManagment.setId(treatmentReceived.getId());
            patientManagment.setPatientId(treatmentReceived.getPatientId());
            patientManagment.setReceptionDt(treatmentReceived.getReceptionDt());
            patientManagment.setStatus(treatmentReceived.getStatus());
            // view 레파지 토리에 save
            patientManagmentRepository.save(patientManagment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenTreatmentReceived_then_UPDATE_1(
        @Payload TreatmentReceived treatmentReceived
    ) {
        try {
            if (!treatmentReceived.validate()) return;
            // view 객체 조회
            Optional<PatientManagment> patientManagmentOptional = patientManagmentRepository.findById(
                treatmentReceived.getId()
            );

            if (patientManagmentOptional.isPresent()) {
                PatientManagment patientManagment = patientManagmentOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                patientManagment.setStatus("진료상태값");
                // view 레파지 토리에 save
                patientManagmentRepository.save(patientManagment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenTreatmentCanceled_then_UPDATE_2(
        @Payload TreatmentCanceled treatmentCanceled
    ) {
        try {
            if (!treatmentCanceled.validate()) return;
            // view 객체 조회
            Optional<PatientManagment> patientManagmentOptional = patientManagmentRepository.findById(
                treatmentCanceled.getId()
            );

            if (patientManagmentOptional.isPresent()) {
                PatientManagment patientManagment = patientManagmentOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                patientManagment.setStatus("진료상태값");
                // view 레파지 토리에 save
                patientManagmentRepository.save(patientManagment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
