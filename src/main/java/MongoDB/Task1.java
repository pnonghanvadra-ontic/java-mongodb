package MongoDB;

import com.mongodb.*;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        System.out.println("Created");
        DB db = mongoClient.getDB("Users");
        DBCollection collection = db.getCollection("User Detail");

        Scanner input = new Scanner(System.in);
        boolean itr = true;
        while (itr) {
            System.out.println("1. Insert");
            System.out.println("2. Search");
            System.out.println("3. View Collection");
            System.out.println("4. Quit");
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Insert User detail: ");
                    System.out.println("Email: ");
                    String email = input.nextLine();
                    System.out.println("First Name: ");
                    String firstName = input.nextLine();
                    System.out.println("Last Name: ");
                    String lastName = input.nextLine();
                    System.out.println("Location: ");
                    String location = input.nextLine();
                    System.out.println("Age: ");
                    int age = input.nextInt();
                    input.nextLine();


                    DBObject person = new BasicDBObject()
                            .append("email", email)
                            .append("firstName", firstName)
                            .append("lastName", lastName)
                            .append("age", age)
                            .append("location", location);

                    collection.insert(person);
                    break;
                case 2:
                    System.out.println("Enter email to find: ");
                    email = input.nextLine();
                    DBObject whereQuery = new BasicDBObject();
                    whereQuery.put("email", email);
                    DBCursor cursor = collection.find(whereQuery);
                    if(cursor.count() == 0){
                        System.out.println("No records Found");
                    }
                    else {
                        while (cursor.hasNext()) {
                            System.out.println(cursor.next());
                        }
                    }
                    break;
                case 3:
                    cursor = collection.find();
                    if(cursor.count() == 0){
                        System.out.println("No records Found");
                    }
                    while(cursor.hasNext()){
                        System.out.println(cursor.next());
                    }
                    break;
                default:
                    itr = false;
                    break;
            }
        }
        System.out.println("Done");
}
}
