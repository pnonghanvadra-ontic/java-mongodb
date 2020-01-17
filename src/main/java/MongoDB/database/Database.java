package MongoDB.database;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Database {
    public void insert(String json, String destination);
    public void update(Map<String,String> queryParams, Map<String, String> setParams, String destination);
    public void delete(Map<String,String> queryParams, String destination);
    public List<String> show(String destination);
    public List<String> get(Map<String,String> queryParams, String from) throws IOException, JSONException;
}
