package tr.com.havelsan.demo.op;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;

@Repository
public class DbOperations {

    @Autowired
    private DbOpBussiness dbOpBussiness;

    public <T> T save(T t){
       return  dbOpBussiness.save(t);
    }

    public <T> T update(T t){
        return  dbOpBussiness.update(t);
    }

    public <T> T findOne(Query query,Class<T> t){
        return  dbOpBussiness.findOne(query,t);
    }

    public <T> List<T> find(Query query, Class<T> t){
        return  dbOpBussiness.find(query,t);
    }

	public <T> DeleteResult remove(T t) {
		return dbOpBussiness.remove(t);
	}
	
	public <T> List<T> removeCollection(Class<T> t) {
		return dbOpBussiness.removeCollecton(t);
	}
   

}
