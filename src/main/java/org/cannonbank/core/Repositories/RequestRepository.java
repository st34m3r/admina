package org.cannonbank.core.Repositories;


import java.util.List;

import org.cannonbank.core.Entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RequestRepository extends JpaRepository<Request,Integer> {
    Request findById(int requestId);

    @Query("SELECT u FROM Request u WHERE u.open = :open")
	List<Request> findByOpen(@Param("open") boolean open);
}
