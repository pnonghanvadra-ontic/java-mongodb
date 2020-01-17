package MongoDB;

public class Student {
    private final String id;
    private final String name;
    private String grade;

    private Student(StudentBuilder builder) {
        this.id = builder.id;
        this.name= builder.name;
        this.grade = builder.grade;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    private String getGrade(){
        return grade;
    }

    public static class StudentBuilder
    {
        private final String id;
        private String name;
        private String grade;
        public StudentBuilder(String id) {
            this.id = id;
        }


        public StudentBuilder name(String name) {
            this.name = name;
            return this;
        }
        public StudentBuilder grade(String phone) {
            this.grade = grade;
            return this;
        }
        public Student build() {
            return new Student(this);
        }
    }
}
