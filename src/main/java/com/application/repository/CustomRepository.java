package com.application.repository;

import com.application.model.BaseDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomRepository extends MongoRepository<BaseDocument, String> {
}
