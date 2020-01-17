package MongoDB.builder;

import java.util.HashMap;
import java.util.Map;

public class QueryBuilder {
    private Map< String,String> queryParams;

    public QueryBuilder(){
        queryParams = new HashMap<>();
    }
    public void add(String key, String value){
        queryParams.put(key,value);
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }
}
