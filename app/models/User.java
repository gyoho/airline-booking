package models;

import play.db.jpa.*;
import play.data.validation.*;
import javax.persistence.*;

@Entity
@Table(name="Customer")
public class User extends Model {
    
    // username and password ave constraints
    // Not allowing to use special characters
    @Required
    @MaxSize(15)
    @MinSize(4)
    @Match(value="^\\w*$", message="Not a valid username")
    public String username;
    
    @Required
    @MaxSize(15)
    @MinSize(5)
    public String password;
    
    // real name with less constraints than username
    @Required
    @MaxSize(20)
    public String name;
   
    public User(String name, String password, String username) {
        this.name = name;
        this.password = password;
        this.username = username;
    }

    public String toString()  {
        return "User(" + username + ")";
    }
    
    public boolean admin = false;
}
