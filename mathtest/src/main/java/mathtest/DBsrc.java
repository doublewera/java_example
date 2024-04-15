package mathtest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

import org.hibernate.Session;    
import org.hibernate.SessionFactory;    
import org.hibernate.Transaction;  
import org.hibernate.boot.Metadata;  
import org.hibernate.boot.MetadataSources;  
import org.hibernate.boot.registry.StandardServiceRegistry;  
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;  

public class DBsrc {
	private Properties prop = new Properties();
	//private Configuration cfg = new Configuration();
	private StandardServiceRegistry ssr;
	private Metadata meta;
    
	public DBsrc() {
		String pss;
		try {
			pss = readPgpass("academy_top", "tutor");
		}  catch (IOException ioe) {
			pss = "";
		}
		prop.setProperty("hibernate.connection.password", pss);
		//cfg.setProperties(prop);
		ssr = new StandardServiceRegistryBuilder(
				).configure("hibernate.cfg.xml").applySettings(prop).build();
		meta = new MetadataSources(ssr).getMetadataBuilder().build();
	}
	
	private static String readPgpass(String dbname, String username) throws IOException {
    	// Join standard path for pgpass in our system
    	Path filePath = Paths.get(
    			System.getenv("APPDATA"),
    			"postgresql",
    			"pgpass.conf");
    	// Open file
    	String pss = "";
    	String line = "";
    	String[] parts;
    	System.out.println(filePath.toString());
    	File file = new File(filePath.toString());
        Scanner inputFile = new Scanner(file);
        // Read lines from the file until no more are left.
        while (inputFile.hasNext() && pss == "") {
            line = inputFile.nextLine();
            // hostname:port:dbname:username:password
            // 0        1    2      3        4
            System.out.println(line);
            parts = line.split(":");
            if (parts.length >= 5 && parts[2].equals(dbname) && parts[3].equals(username)) {
            	pss = parts[4].trim();  // deleting end of line
                System.out.println(pss);	
            }   
        }
        // Close the file.
        inputFile.close();
        return pss;
    }
	
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
