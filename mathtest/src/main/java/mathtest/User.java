package mathtest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String firstName, lastName, password;
	
	public User(String fn, String ln, String psw) {
		firstName = fn;
		lastName = ln;
		password = psw;  // is it a real password?
	}
	
	public int getId() {
		return id;
	}
	
	public String getFirstName() {    
	    return firstName;    
	}    
	public String getLastName() {    
	    return lastName;    
	}    
	public String getPassword() {    
	    return password;    
	}
}
