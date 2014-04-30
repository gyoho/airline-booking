package models;

import play.db.jpa.*;
import play.data.validation.*;
import javax.persistence.*;
import java.util.*;
import java.text.*;
import java.math.*;

@Entity
public class Booking extends Model {
    
    @Required
    @ManyToOne
    public User user;
    
    @Required
    @ManyToOne
    public Flight flight;
    
    @Required(message="Credit card number is required")
    @Match(value="^\\d{16}$", message="Credit card number must be numeric and 16 digits long")
    public String creditCard;
    
    @Required(message="Credit card name is required")
    @MinSize(value=3, message="Credit card name is required")
    @MaxSize(value=70, message="Credit card name is required")
    public String creditCardName;
    public int creditCardExpiryMonth;
    public int creditCardExpiryYear;

    public Booking(Flight flight, User user) {
        this.flight = flight;
        this.user = user;
    }
   
    // Total amount of fare   
    // public BigDecimal getFare() {
    //    If more than one flight
    //     return flight.price + flight.price
    // }

    // Total trip time
    // public int getTripTime() {
    //     return (int) ( arrv_date.getTime() - dep_date.getTime() ) / 1000 / 60 / 60 / 24;
    // }


    public String toString() {
        return "Booking(" + user + ","+ flight + ")";
    }

}
