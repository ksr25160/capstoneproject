package capstoneproject.domain;

import capstoneproject.domain.*;
import capstoneproject.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ExaminationCompleted extends AbstractEvent {

    private Long id;
    private Long patientId;
    private Long examId;
    private Long examDt;
    private String status;

    public ExaminationCompleted(Examination aggregate) {
        super(aggregate);
    }

    public ExaminationCompleted() {
        super();
    }
}
//>>> DDD / Domain Event
