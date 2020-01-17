package MongoDB.services;

import MongoDB.Student;
import MongoDB.builder.QueryBuilder;
import MongoDB.database.Database;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentServices {

    private Database db;

    public StudentServices(Database db){
        this.db = db;
    }

    public void insert(Student student) throws IOException{
        if (student == null || student.getId() == null)
            return;
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(student);
        db.insert(jsonString, "studentDetails");
    }

    public void update(QueryBuilder queryParams, QueryBuilder setParams){
        if (queryParams.getQueryParams().isEmpty())
            return;
        db.update(queryParams.getQueryParams(), setParams.getQueryParams(), "studentDetails");
    }

    public void delete(QueryBuilder queryParams){
        if (queryParams.getQueryParams().isEmpty())
            return;
        db.delete(queryParams.getQueryParams(), "studentDetails");
    }

    public List<Student> search(QueryBuilder queryParams) throws IOException, JSONException {

        if (!queryParams.getQueryParams().isEmpty()) {
            List<String> studentList = db.get(queryParams.getQueryParams(), "studentDetails");
            ObjectMapper mapper = new ObjectMapper();
            List<Student> students = new ArrayList<>();
            for (String student : studentList) {
                students.add(mapper.readValue(student, Student.class));
            }
            return students;
        } else {
            return new ArrayList<Student>(0);
        }
    }


}
