package main;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Database {

    private static MongoClient mongoClient;

    private static DB database;

    private static DBCollection collection;

    public static DBCollection getCollection() {
        return collection;
    }

    public static void connectDB() {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDB("EmailConfUsers");
        collection = database.getCollection("Users");
    }
}
