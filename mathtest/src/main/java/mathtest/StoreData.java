package mathtest;

import org.hibernate.Session;    
import org.hibernate.SessionFactory;    
import org.hibernate.Transaction;  
import org.hibernate.boot.Metadata;  
import org.hibernate.boot.MetadataSources;  
import org.hibernate.boot.registry.StandardServiceRegistry;  
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;  

public class StoreData {
	private StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	private Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
    
    public void saveToTbl(Object myo) {
    	SessionFactory factory = meta.getSessionFactoryBuilder().build();
    	Session session = factory.openSession();
    	Transaction t = session.beginTransaction();   
    	session.persist(myo);
        t.commit();
        factory.close();  
        session.close();
    }
    
    public Object findIt(Class mycls, int id) {
    	SessionFactory factory = meta.getSessionFactoryBuilder().build();
    	Session session = factory.openSession();
    	Transaction t = session.beginTransaction();   
    	Object result = session.get(mycls, id);
        factory.close();  
        session.close();
        return result;
    }
	
    public void saveAnswer(Question q, String a) {
    	MathResult m = new MathResult(q, a);
    	saveToTbl(m);
    }

    public Question getQuestion(int id) {  
    	return (Question) findIt(Question.class, id);
    }
    
    public User getUser(String cookies) {
    	int id = 1;  // get from cookies  
    	return (User) findIt(User.class, id);
    }
}   
