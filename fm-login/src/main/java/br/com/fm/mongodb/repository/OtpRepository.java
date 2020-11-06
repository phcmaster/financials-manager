package br.com.fm.mongodb.repository;


import br.com.fm.mongodb.entity.OtpEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OtpRepository extends MongoRepository<OtpEntity, String> {

    Optional<OtpEntity> findByEmail(String email);
}
