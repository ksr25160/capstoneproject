package capstoneproject.domain;

import capstoneproject.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "examinations",
    path = "examinations"
)
public interface ExaminationRepository
    extends PagingAndSortingRepository<Examination, Long> {}
