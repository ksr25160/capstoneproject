package capstoneproject.domain;

import capstoneproject.domain.*;
import capstoneproject.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ExaminationCompleted extends AbstractEvent {

    private Long id;
    private Long patientId;
    private Long examId;
    private Long examDt;
    private String status;
}
