package capstoneproject.domain;

import capstoneproject.domain.*;
import capstoneproject.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ExaminationCanceled extends AbstractEvent {

    private Long id;
    private Long patientId;
    private Long examId;
    private Long examDt;
    private String status;

    public ExaminationCanceled(Examination aggregate) {
        super(aggregate);
    }

    public ExaminationCanceled() {
        super();
    }
}
//>>> DDD / Domain Event
