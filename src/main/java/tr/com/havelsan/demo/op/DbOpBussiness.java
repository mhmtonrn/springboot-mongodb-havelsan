package tr.com.havelsan.demo.op;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonObject;
import com.mongodb.client.result.DeleteResult;

import tr.com.havelsan.demo.repository.User;
import tr.com.havelsan.demo.utils.Common;
import tr.com.havelsan.demo.utils.types.LogType;

@Repository
public class DbOpBussiness {

    @Autowired
    private CustomMongoRepository customMongoRepository;
    
    @Autowired
    private User user;


    public <T> T save(T t) {
        return getT(t, LogType.Save);
    }

    public <T> T update(T t) {
        return getT(t, LogType.Update);
    }


    private <T> T getT(T t, LogType logType) {
        String collection= Common.collectionName(t);
        T savedObject = customMongoRepository.save(t);
        log(collection,savedObject,logType.getLogType());
        return savedObject;
    }

    public <T> DeleteResult remove(T t) {
        String collection= Common.collectionName(t);
        DeleteResult returnValue = customMongoRepository.remove(t);
        log(collection,t,LogType.Delete.getLogType());
        return returnValue;
    }

    public <T> T findOne(Query query, Class<T> t) {
        String collection= t.getSimpleName();
        T selectedObject = customMongoRepository.findOne(query,t);
        if (selectedObject != null) {
        	log(collection,selectedObject,LogType.Select.getLogType());
		}
        return selectedObject;
    }

    public <T> List<T> find(Query query, Class<T> t) {
        String collection= t.getSimpleName();
        List<T> selectedObjects = customMongoRepository.find(query,t);
        for (T selectedObject:selectedObjects){
            log(collection,selectedObject,LogType.Select.getLogType());
        }

        return selectedObjects;
    }

    private <T> void log(String collection,T selectedObject, String logType) {
        JsonObject jsonObject = getLogModel(selectedObject);
        jsonObject.addProperty("logtype", logType);
        customMongoRepository.save(org.bson.Document.parse(jsonObject.toString()),collection+"_LOG");
    }


    private <T> JsonObject getLogModel(T savedObject) {
        JsonObject jsonObject = Common.modelToJsonObject(savedObject);
        jsonObject.addProperty("user", user.getName());
        jsonObject.addProperty("time",Common.getToday());
        return jsonObject;
    }

	public <T> List<T> removeCollecton(Class<T> t) {
		return customMongoRepository.removeColllection(t);
	}


}
