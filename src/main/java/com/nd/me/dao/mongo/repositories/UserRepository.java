package com.nd.me.dao.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDomain, String>{

}
