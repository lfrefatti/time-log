package br.com.lfr.timelog.repositories;

import br.com.lfr.timelog.domain.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String>{
}
