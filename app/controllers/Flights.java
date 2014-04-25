package controllers;

import play.*;
import play.mvc.*;
import play.data.validation.*;

import java.util.*;

import models.*;

public class Flights extends Application {
    
    // Interception methods
    // Invoked before for each action call of this controller class
    // Verifying if a user is authenticated
    @Before
    static void checkUser() {
        if(connected() == null) {
            flash.error("Please log in first");
            Application.index();
        }
    }
    
    // ~~~
    
    public static void index() {
        List<Booking> bookings = Booking.find("byUser", connected()).fetch();
        render(bookings);
    }

    public static void list(String search, Integer size, Integer page) {
        List<Flight> flights = null;
        page = page != null ? page : 1;
        if(search.trim().length() == 0) {
            flights = Flight.all().fetch(page, size);
        } else {
            search = search.toLowerCase();
            flights = Flight.find("lower(name) like ? OR lower(city) like ?", "%"+search+"%", "%"+search+"%").fetch(page, size);
        }
        render(flights, search, size, page);
    }
    
    public static void show(Long id) {
        Flight flight = Flight.findById(id);
        render(flight);
    }
    
    public static void book(Long id) {
        Flight flight = Flight.findById(id);
        render(flight);
    }
    
    public static void confirmBooking(Long id, Booking booking) {
        Flight flight = Flight.findById(id);
        booking.flight = flight;
        booking.user = connected();
        validation.valid(booking);
        
        // Errors or revise
        if(validation.hasErrors() || params.get("revise") != null) {
            render("@book", flight, booking);
        }
        
        // Confirm
        if(params.get("confirm") != null) {
            booking.save();
            flash.success("Thank you, %s, your confimation number for %s is %s", connected().name, flight.name, booking.id);
            index();
        }
        
        // Display booking
        render(flight, booking);
    }
    
    public static void cancelBooking(Long id) {
        Booking booking = Booking.findById(id);
        booking.delete();
        flash.success("Booking cancelled for confirmation number %s", booking.id);
        index();
    }
    
    public static void settings() {
        render();
    }
    
    public static void saveSettings(String password, String verifyPassword) {
        User connected = connected();
        connected.password = password;
        validation.valid(connected);
        validation.required(verifyPassword);
        validation.equals(verifyPassword, password).message("Your password doesn't match");
        if(validation.hasErrors()) {
            render("@settings", connected, verifyPassword);
        }
        connected.save();
        flash.success("Password updated");
        index();
    }
    
}

