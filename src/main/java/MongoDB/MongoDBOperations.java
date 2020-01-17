package MongoDB;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;


public class MongoDBOperations {

    public static DBObject getDBObject(User user) {
        DBObject dbObject = new BasicDBObject()
                .append("email", user.getEmail())
                .append("firstName", user.getFirstName())
                .append("lastName", user.getLastName())
                .append("age", user.getAge())
                .append("phone", user.getPhone());

        return dbObject;
    }

    public static User getUser(DBObject dbObject) {
        return new User.UserBuilder((String) dbObject.get("email"), (String) dbObject.get("firstName"), (String) dbObject.get("lastName"))
                .age((String) dbObject.get("age"))
                .phone((String) dbObject.get("phone"))
                .build();
    }

    public static void insert(DBCollection collection, User user) {
        DBObject dbObject = getDBObject(user);
        collection.insert(dbObject);
    }

    public static List<User> searchByEmail(DBCollection collection, String email) {

        if (email != null && collection != null) {
            DBObject query = new BasicDBObject();
            query.put("email", email);
            DBCursor cursor = collection.find(query);
            List<User> users = new ArrayList<User>(cursor.count());
            DBObject dbObject;
            if (cursor.count() == 0) {
                System.out.println("No records Found");
            } else {
                while (cursor.hasNext()) {
                    dbObject = cursor.next();
                    users.add(getUser(dbObject));
                }
            }
            return users;
        }
        else{
            return new ArrayList<User>(0);
        }
    }

    public static void updateUser(DBCollection collection, String email, String phone){
        if(email==null)
            return;
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("phone", phone));
        BasicDBObject searchQuery = new BasicDBObject().append("email", email);
        collection.update(searchQuery, newDocument);
    }

    public static void deleteUser(DBCollection collection, String email){
        if(email==null)
            return;
        collection.remove(new BasicDBObject().append("email",email));
    }

    public static void showCollection(DBCollection collection){
        DBCursor cursor = collection.find();
        DBObject dbObject;
        if(cursor.count() == 0){
            System.out.println("No records Found");
        }
        while(cursor.hasNext()){
            dbObject = cursor.next();
            System.out.println(getUser(dbObject));
        }
    }
}
