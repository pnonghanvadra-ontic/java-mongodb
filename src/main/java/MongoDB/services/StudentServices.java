package MongoDB.services;

import MongoDB.Student;
import MongoDB.database.Database;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.main.Option;

import java.io.IOException;

public class StudentServices {

    private Database db;

    public StudentServices(Database db){
        this.db = db;
    }

    public void insert(Student student) throws IOException, Option.InvalidValueException {
        if (student == null || student.getId() == null)
            return;
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(student);
        db.insert(jsonString, "studentDetails");
    }


}
