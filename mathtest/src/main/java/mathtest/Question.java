package mathtest;

import java.util.concurrent.ThreadLocalRandom;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	public int getId() {
		return id;
	}
	
    private String content, answer;
	public Question() {  // String content, String answer) {
		int a = ThreadLocalRandom.current().nextInt(1, 9 + 1);
	    int b = ThreadLocalRandom.current().nextInt(1, 9 + 1);
	    this.content = a + " + " + b + " = ";     
	    this.answer = "" + (a + b);
	}
	
	public String getContent() {    
	    return content;    
	}    
	//public void setContent(String content) {    
	//    this.content = content;    
	//}
	
	public String getAnswer() {    
	    return answer;    
	}    
	//public void setAnswer(String answer) {    
	//    this.answer = answer;    
	//}
}
