package controllers;

import play.mvc.*;
import play.data.validation.*;

import models.*;

public class Application extends Controller {
    
    // Interception methods
    // Invoked before for each action call of this controller class
    // Check a user is already registered
    @Before
    static void addUser() {
        User user = connected();
        if(user != null) {
            renderArgs.put("user", user);
        }
    }
    
    static User connected() {
        if(renderArgs.get("user") != null) {
            return renderArgs.get("user", User.class);
        }
        String username = session.get("user");
        if(username != null) {
            return User.find("byUsername", username).first();
        } 
        return null;
    }
    
    // ~~

    public static void index() {
        if(connected() != null) {
            Flights.index();
        }
        /*Check if he/she is admin, if so render admin page*/
        // if(admin == true) {
        //     Flights.admin.html.render()
        // }

        render();
    }
    
    public static void register() {
        render();
    }
    
    public static void saveUser(@Valid User user, String verifyPassword) {
        validation.required(verifyPassword);
        validation.equals(verifyPassword, user.password).message("Your password doesn't match");
        if(validation.hasErrors()) {
            render("@register", user, verifyPassword);
        }
        // Set the user to non admin
        user.admin = false;
        user.save();
        session.put("user", user.username);
        flash.success("Welcome, " + user.name);
        Flights.index();
    }
    
    public static void login(String username, String password) {
        User user = User.find("byUsernameAndPassword", username, password).first();
        if(user != null) {
            session.put("user", user.username);
            flash.success("Welcome, " + user.name);
            Flights.index();         
        }
        // If cannot find the user in db
        flash.put("username", username);
        flash.error("Login failed");
        index();
    }
    
    public static void logout() {
        session.clear();
        index();
    }

}