package capstoneproject.domain;

import capstoneproject.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "receptions",
    path = "receptions"
)
public interface ReceptionRepository
    extends PagingAndSortingRepository<Reception, Long> {}
