package capstoneproject.domain;

import capstoneproject.domain.*;
import capstoneproject.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class Prescribed extends AbstractEvent {

    private Long id;
    private Long patientId;
    private Date receptionDt;
    private String priscribeCode;
    private Date priscribeDt;
    private String status;

    public Prescribed(Diagnosis aggregate) {
        super(aggregate);
    }

    public Prescribed() {
        super();
    }
}
//>>> DDD / Domain Event
