package MongoDB;

import MongoDB.database.Database;
import MongoDB.database.MongoDB;
import MongoDB.services.StudentServices;
import MongoDB.services.UserServices;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean(name="mongoclient")
    public MongoClient getMongoClient(){
        return new MongoClient("localhost", 27017);
    }

    @Bean("database")
    public Database getDabase(){
        return new MongoDB();
    }

    @Bean(name = "userservices")
    public UserServices getUserServices(Database db){
        return new UserServices(db);
    }

    @Bean(name = "studentservices")
    public StudentServices getStudentServices(Database db){
        return new StudentServices(db);
    }
}
