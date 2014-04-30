package models;

import play.db.jpa.*;
import play.data.validation.*;
import javax.persistence.*;
import java.math.*;
import java.util.*;

@Entity
public class Flight extends Model {
    
    @Required
    @MaxSize(10)
    public String flight_no;

    @Required
    @MaxSize(10)
    @MinSize(2)
    public String airline;
    
    @Required
    @MaxSize(10)
    public String dep_city;

    @Required
    @MaxSize(10)
    public String arrv_city;
    
    @Required
    @Temporal(TemporalType.DATE)
    public Date dep_date;

    @Required
    @Temporal(TemporalType.DATE)
    public Date arrv_date;


   /*java.util DATE, TIME*/

    @Required
    @Temporal(TemporalType.TIME)
    public Date dep_time;

    @Required
    @Temporal(TemporalType.TIME)
    public Date arrv_time;
    
    
    @Column(precision=6, scale=2)
    public BigDecimal price;

    public String toString() {
        return "Flight(" + flight_no + "," + airline + "," + dep_city + ","
         + arrv_city + "," + dep_date + "," + arrv_date + "," + price + ")";
    }
    
}
