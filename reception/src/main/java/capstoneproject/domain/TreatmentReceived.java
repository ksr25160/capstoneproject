package capstoneproject.domain;

import capstoneproject.domain.*;
import capstoneproject.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class TreatmentReceived extends AbstractEvent {

    private Long id;
    private Long patientId;
    private Date receptionDt;
    private String patientNm;
    private String address;
    private String status;

    public TreatmentReceived(Reception aggregate) {
        super(aggregate);
    }

    public TreatmentReceived() {
        super();
    }
}
//>>> DDD / Domain Event
