package br.com.fm.mongodb.config;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("br.com.fm.mongodb.config.repository")
public class MongoDbConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String uriMongoDB;

    @Value("${spring.data.mongodb.database}")
    private String mongoDB;



    public MongoMappingContext mongoMappingContext()
            throws ClassNotFoundException {
        // TODO Auto-generated method stub
        return super.mongoMappingContext();
    }

    @Override
    protected String getDatabaseName() {
        // TODO Auto-generated method stub
        return mongoDB;
    }

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(uriMongoDB);
    }


}
