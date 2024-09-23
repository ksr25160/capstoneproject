package capstoneproject.infra;

import capstoneproject.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ReceptionHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Reception>> {

    @Override
    public EntityModel<Reception> process(EntityModel<Reception> model) {
        return model;
    }
}
