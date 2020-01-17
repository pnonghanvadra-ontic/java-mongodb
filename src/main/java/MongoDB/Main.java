package MongoDB;

import MongoDB.services.StudentServices;
import MongoDB.services.UserServices;
import com.sun.tools.javac.main.Option;
import org.json.JSONException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import MongoDB.builder.QueryBuilder;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws IOException, Option.InvalidValueException, JSONException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        UserServices userServices = (UserServices) context.getBean("userservices");

        User user1 = new User.UserBuilder("p@ontic.ai","Pragnesh","Patel").age("21").build();
        userServices.insert(user1);

        User user2 = new User.UserBuilder("pnonghanvadra@ontic.ai","Pragnesh","Nonghanvadra").age("20").build();
        userServices.insert(user2);

        User user3 = new User.UserBuilder("prageshbn092@gmail.com","Pragnesh", "Nonghanvadra").age("22").phone("9978423362").build();
        userServices.insert(user3);

        User user4 = new User.UserBuilder("random@gmail.com","Random", "Name").age("99").phone("0000011111").build();
        userServices.insert(user4);

        QueryBuilder query = new QueryBuilder();
        query.add("email","p@ontic.ai");

        List<User> searchResults = userServices.search(query);
        for (User user : searchResults) {
            System.out.println(user);
        }

        StudentServices studentServices = (StudentServices) context.getBean("studentservices");
        Student student = new Student.StudentBuilder("1").name("Pragnesh").grade("AA").build();
        studentServices.insert(student);

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.add("email", "random@gmail.com");

        userServices.deleteUser(queryBuilder);
        List<User> results = userServices.showCollection();
        for (User user : results) {
            System.out.println(user);
        }
    }
}
