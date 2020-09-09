package br.com.fm.mongodb.repository;


import br.com.fm.mongodb.entity.ProfileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProfileRepository extends MongoRepository<ProfileEntity, String> {

}
