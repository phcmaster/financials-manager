package br.com.fm.mongodb.repository;


import br.com.fm.mongodb.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories
public interface UserRepository extends MongoRepository<UserEntity, Integer> {

}
