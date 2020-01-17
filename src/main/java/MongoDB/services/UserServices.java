package MongoDB.services;

import MongoDB.User;
import MongoDB.database.Database;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.sun.tools.javac.main.Option;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import MongoDB.builder.QueryBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UserServices {

    private final Database db;

    @Autowired
    public UserServices(Database db) {
        this.db = db;
    }


    public DBObject getDBObject(User user) throws Option.InvalidValueException, IOException {
        if (user == null || user.getEmail() == null) {
            throw new Option.InvalidValueException("Invlid Values");
        }
        DBObject dbObject = new BasicDBObject()
                .append("email", user.getEmail())
                .append("firstName", user.getFirstName())
                .append("lastName", user.getLastName())
                .append("age", user.getAge())
                .append("phone", user.getPhone());

        return dbObject;
    }

    public User getUser(DBObject dbObject) {
        return new User.UserBuilder((String) dbObject.get("email"), (String) dbObject.get("firstName"), (String) dbObject.get("lastName"))
                .age((String) dbObject.get("age"))
                .phone((String) dbObject.get("phone"))
                .build();
    }

    public void insert(User user) throws IOException, Option.InvalidValueException {
        if (user == null || user.getEmail() == null)
            return;
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(user);
        db.insert(jsonString, "userdetails");
    }

    public List<User> search(QueryBuilder queryParams) throws IOException, JSONException {

        if (!queryParams.getQueryParams().isEmpty()) {
            List<String> userList = db.get(queryParams.getQueryParams(), "userdetails");
            ObjectMapper mapper = new ObjectMapper();
            List<User> users = new ArrayList<>();
            for (String user : userList) {
                users.add(mapper.readValue(user, User.class));
            }
            return users;
//            return db.get(queryParams.getQueryParams(), "userdetails");
        } else {
            return new ArrayList<User>(0);
        }
    }

    public void update(QueryBuilder queryParams, QueryBuilder setParams) {
        if (queryParams.getQueryParams().isEmpty())
            return;
        db.update(queryParams.getQueryParams(), setParams.getQueryParams(), "userdetails");
    }

    public void deleteUser(QueryBuilder queryParams) {
        if (queryParams.getQueryParams().isEmpty())
            return;
        db.delete(queryParams.getQueryParams(), "userdetails");
    }

    public List<User> showCollection() throws IOException {
        List<String> results = db.show("userdetails");
        List<User> users = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (String user : results) {
            users.add(mapper.readValue(user, User.class));
        }
        return users;
    }

}
