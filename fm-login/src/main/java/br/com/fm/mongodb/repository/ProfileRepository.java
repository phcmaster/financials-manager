package br.com.fm.mongodb.repository;


import br.com.fm.mongodb.entity.ProfileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface ProfileRepository extends MongoRepository<ProfileEntity, String> {

    Optional<ProfileEntity> findByRole(String role);

}
