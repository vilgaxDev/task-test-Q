package com.vilgaxdevtask.test.repositories;

import com.vilgaxdevtask.test.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    Iterable<Project> findAllById(Iterable<Long> longs);

    Project findByProjectIdentifier(String project);

    Iterable<Project> findAllByProjectOwner(String username);

}
