package capstoneproject.domain;

import capstoneproject.domain.*;
import capstoneproject.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class DiagnosisCompleted extends AbstractEvent {

    private Long id;
    private Long patientId;
    private Date receptionDt;
    private String priscribeCode;
    private Date priscribeDt;
    private String status;
}
