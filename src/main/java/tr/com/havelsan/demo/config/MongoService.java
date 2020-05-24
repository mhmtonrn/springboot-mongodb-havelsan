package tr.com.havelsan.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
public class MongoService {

    @Value("${spring.data.mongodb.host}")
    private String hostName;
    @Value("${spring.data.mongodb.database}")
    private String dataBase;
    @Value("${spring.data.mongodb.username}")
    private String user;
    @Value("${spring.data.mongodb.password}")
    private String password;


    public MongoOperations mongoOperations() {
        return mongoTemplate();
    }

    @SuppressWarnings("deprecation")
	public MongoTemplate mongoTemplate()  {
        MongoTemplate mongoTemplate =	new MongoTemplate(new MongoClient(hostName+""),dataBase+"");
        return mongoTemplate;

    }
}
