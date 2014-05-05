package models;

import play.db.jpa.*;
import play.data.validation.*;
import javax.persistence.*;
import java.math.*;
import java.util.*;

@Entity
public class Flight extends Model {
    
    @Required
    @Match(value="\\w{2}\\d{3,4}", message="Not a valid flight no")
    public String flight_no;

    @Required
    @MaxSize(10)
    @MinSize(2)
    public String airline;
    
    @Required
    @Match(value="\\w{3}", message="Not a valid airport")
    public String dep_city;

    @Required
    @Match(value="\\w{3}", message="Not a valid airport")
    public String arrv_city;
    
    // TemporalType.DATE: Map as java.sql.Date

    @Required
    @Temporal(TemporalType.DATE)
    public Date dep_date;

    @Required
    @Temporal(TemporalType.DATE)
    public Date arrv_date;

    @Required
    @Match(value="^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message="Not a valid time")
    public String dep_time;

    @Required
    @Match(value="^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message="Not a valid time")
    public String arrv_time;

    @Required
    @Match(value="\\d{1,3}", message="Not a valid number for seats")
    public Integer seats;
    
    @Required
    @Column(precision=6, scale=2)
    public BigDecimal price;

    public String toString() {
        return "Flight(" + flight_no + "," + airline + "," + dep_city + ","
         + arrv_city + "," + dep_date + "," + arrv_date + "," + price + ")";
    }    
}
