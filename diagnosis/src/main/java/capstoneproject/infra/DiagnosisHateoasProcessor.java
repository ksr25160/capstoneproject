package capstoneproject.infra;

import capstoneproject.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class DiagnosisHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Diagnosis>> {

    @Override
    public EntityModel<Diagnosis> process(EntityModel<Diagnosis> model) {
        return model;
    }
}
