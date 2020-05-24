package tr.com.havelsan.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tr.com.havelsan.demo.model.Users;

@Repository
public interface UserMongoRepository extends MongoRepository<Users, String> {
    Users findByName(String name);
}