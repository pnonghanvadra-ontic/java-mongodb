package MongoDB;

public class User {
    private String email;
    private String firstName; // required
    private String lastName; // required
    private String age;
    private String phone;

    public User(){}

    private User(UserBuilder builder) {
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
    }

    public String getEmail() {
        return email;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getAge() {
        return age;
    }
    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "User: "+this.email+","+this.firstName+", "+this.lastName+", "+this.age+", "+(this.phone==null?"-":this.phone);
    }

    public static class UserBuilder
    {
        private final String email;
        private final String firstName;
        private final String lastName;
        private String age;
        private String phone;

        public UserBuilder(String email, String firstName, String lastName) {
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public UserBuilder age(String age) {
            this.age = age;
            return this;
        }
        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }
        public User build() {
            return new User(this);
        }
    }
}
