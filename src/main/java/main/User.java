package main;

import com.mongodb.BasicDBObject;

public class User {

    private static BasicDBObject user = new BasicDBObject();

    private static String email = "";

    private static Boolean confirmed = false;

    private static Boolean loggedIn = false;

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static Boolean getConfirmed() {
        return confirmed;
    }

    public static void setConfirmed(Boolean confirmed) {
        User.confirmed = confirmed;
    }

    public static Boolean getLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(Boolean loggedIn) {
        User.loggedIn = loggedIn;
    }

    public static BasicDBObject getUser() {
        return user;
    }

    public static void setUser(BasicDBObject user) {
        User.user = user;
    }
}
