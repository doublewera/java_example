package mathtest;

import org.hibernate.Session;    
import org.hibernate.SessionFactory;    
import org.hibernate.Transaction;  
import org.hibernate.boot.Metadata;  
import org.hibernate.boot.MetadataSources;  
import org.hibernate.boot.registry.StandardServiceRegistry;  
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;  

public class StoreData { 
	
    public void saveAllToDb(Question q) {
    	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    	Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
    	SessionFactory factory = meta.getSessionFactoryBuilder().build();
    	Session session = factory.openSession();
    	Transaction t = session.beginTransaction();   
                
        session.persist(q);
        t.commit();  
        factory.close();  
        session.close();  
    } 
	
    public void saveAnswer(Question q, String a) {
    	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    	Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
    	SessionFactory factory = meta.getSessionFactoryBuilder().build();
    	Session session = factory.openSession();
    	Transaction t = session.beginTransaction();   
                
    	MathResult m = new MathResult(q, a);
        session.persist(m);
        t.commit();  
        factory.close();  
        session.close();  
    }
    
 
	
    public Question getQuestion(int id) {
    	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    	Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
    	SessionFactory factory = meta.getSessionFactoryBuilder().build();
    	Session session = factory.openSession();
    	Transaction t = session.beginTransaction();   
    	Question q = (Question) session.get(
				Question.class, id);
        factory.close();  
        session.close();
        return q;
    }
	
    /*public static void main(String[] args) {    
        
 
	Session session = factory.openSession();  
	Transaction t = session.beginTransaction();   
            
    int a = ThreadLocalRandom.current().nextInt(1, 9 + 1);
    int b = 7;
    Question q = new Question(a + "+" + b + "=", "" + a + b);
    saveToDb(q);
    //MathResult e1 = new MathResult();    
    //e1.setId(101);    
    //e1.setFirstName("Student");    
    //e1.setLastName("Good");    
    //System.out.println(e1);
    //session.persist(e1);  
    t.commit();  
    System.out.println("successfully saved");    
    factory.close();  
    session.close();    
        
    }*/
}   
