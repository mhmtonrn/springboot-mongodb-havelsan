package tr.com.havelsan.demo.op;

import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tr.com.havelsan.demo.config.MongoService;
import tr.com.havelsan.demo.repository.CrupOperations;

import java.util.List;

@Repository
@Transactional
class CustomMongoRepository implements CrupOperations {

    @Autowired
    MongoService mongoService;


    @Override
    public <T> T save(T entity) {
        return mongoService.mongoOperations().save(entity);
    }

    @Override
    public <T> T save(T entity, String tableName) {
        return mongoService.mongoOperations().insert(entity,tableName);
    }

    @Override
    public boolean deleteAll(String tableName) {
        mongoService.mongoOperations().dropCollection(tableName);
        return false;
    }

    @Override
    public <T> T findOne(Query query, Class<T> tClass) {
        T entity = mongoService.mongoOperations().findOne(query, tClass);
        return entity;
    }

    @Override
    public <T> List<T> find(Query query, Class<T> tClass) {
        return mongoService.mongoOperations().find(query,tClass);
    }

    @Override
    public <T> DeleteResult remove(T t) {
        return mongoService.mongoOperations().remove(t);
    }

	public <T> List<T> removeColllection(Class<T> t) {
		return mongoService.mongoOperations().findAllAndRemove(new Query(), t);
	}

}
