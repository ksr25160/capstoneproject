package capstoneproject.domain;

import capstoneproject.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class TreatmentCanceled extends AbstractEvent {

    private Long id;
    private Long patientId;
    private Date receptionDt;
    private String patientNm;
    private String address;
    private String status;
}
