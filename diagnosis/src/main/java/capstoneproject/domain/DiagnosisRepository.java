package capstoneproject.domain;

import capstoneproject.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "diagnoses", path = "diagnoses")
public interface DiagnosisRepository
    extends PagingAndSortingRepository<Diagnosis, Long> {}
