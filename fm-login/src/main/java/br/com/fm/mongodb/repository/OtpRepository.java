package br.com.fm.mongodb.repository;


import br.com.fm.mongodb.entity.OtpEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends MongoRepository<OtpEntity, String> {

    Optional<OtpEntity> findByEmailAndOtp(String email, String otp);
}
