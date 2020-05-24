package tr.com.havelsan.demo.repository;

import com.mongodb.client.result.DeleteResult;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface CrupOperations {

    <T> T save(T entity, String tableName);

    <T> T save(T entity);

    boolean deleteAll(String tableName);

    <T>  T findOne(Query query, Class<T> tClass) ;

    <T> List<T> find(Query query, Class<T> tClass);

    <T> DeleteResult remove(T t);

}
