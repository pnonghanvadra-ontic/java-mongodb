package MongoDB.database;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MongoDB implements Database {

    private MongoClient mongoClient;
    private DB db;

    public MongoDB(){
        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDB("MyDB");
    }

    @Override
    public void insert(String json, String collection){
        DBCollection mongoCollection = ((DB) this.db).getCollection(collection);
        DBObject dbObject = (DBObject) JSON.parse(json);
        mongoCollection.insert(dbObject);
    }

    @Override
    public void update(Map<String, String> query, Map<String,String> set, String collection){
        DBCollection mongoCollection = ((DB) this.db).getCollection(collection);
        mongoCollection.update(new BasicDBObject(query),new BasicDBObject().append("$set", set));

    }

    @Override
    public void delete(Map<String, String> query, String collection){
        DBCollection mongoCollection = ((DB) this.db).getCollection(collection);
        mongoCollection.remove(new BasicDBObject(query));
    }

    @Override
    public List<String> show(String collection){
        DBCollection mongoCollection = ((DB) this.db).getCollection(collection);
        DBCursor cursor = mongoCollection.find();
        List<String> results = new ArrayList<>();
        if(cursor.count() == 0){
            System.out.println("No records Found");
        }
        while(cursor.hasNext()){
            DBObject dbObject = cursor.next();
            dbObject.removeField("_id");
            results.add(dbObject.toString());
//            System.out.println(cursor.next());
        }
        return results;
    }

    @Override
    public List<String> get(Map<String, String> queryParams, String collection) throws IOException, JSONException {
        if (!queryParams.isEmpty()) {
            DBCollection mongoCollection = ((DB) this.db).getCollection(collection);
            DBObject query = new BasicDBObject(queryParams);
            DBCursor cursor = mongoCollection.find(query);
            List<String> results = new ArrayList<String>(cursor.count());
            DBObject dbObject;
//            ObjectMapper mapper = new ObjectMapper();
//            String jsonString;
            if (cursor.count() == 0) {
                System.out.println("No records Found");
            } else {
                while (cursor.hasNext()) {
                    dbObject = cursor.next();
//                    JSONObject json = new JSONObject(JSON.serialize(dbObject));
//                    json.remove("_id");
//                    jsonString = mapper.writeValueAsString(cursor.next());
//                    System.out.println(jsonString);
//                    users.add(json.toString());
                    dbObject.removeField("_id");
                    results.add(dbObject.toString());
                }
            }
            return results;
        }
        return new ArrayList<>(0);

    }
}
