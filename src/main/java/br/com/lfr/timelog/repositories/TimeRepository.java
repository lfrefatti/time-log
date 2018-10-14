package br.com.lfr.timelog.repositories;

import br.com.lfr.timelog.domain.Time;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends MongoRepository<Time, String>{
}
