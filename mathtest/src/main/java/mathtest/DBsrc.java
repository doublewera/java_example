package mathtest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;    
import org.hibernate.SessionFactory;    
import org.hibernate.Transaction;  
import org.hibernate.boot.Metadata;  
import org.hibernate.boot.MetadataSources;  
import org.hibernate.boot.registry.StandardServiceRegistry;  
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DBsrc {
	private static final Logger consolelog = LogManager.getLogger(DBsrc.class);
	private Properties prop = new Properties();
	private StandardServiceRegistry ssr;
	private Metadata meta;
	private static Path filePath;
	private SessionFactory factory;
	private Session session;
	
    
	public DBsrc(String cfg_path, Path pgpass_path) {
		// Join standard path for pgpass in our systemfilePath = Paths.get(
		filePath = pgpass_path;
		String pss;
		consolelog.error("\n\n\n\nWriting to console.");
		try {
			pss = readPgpass("academy_top", "tutor");
			consolelog.info("Data Base connected.");
		}  catch (IOException ioe) {
			consolelog.info("Password reading error.");
			pss = "";
		}
		prop.setProperty("hibernate.connection.password", pss);
		ssr = new StandardServiceRegistryBuilder(
				).configure(cfg_path).applySettings(prop).build();
		meta = new MetadataSources(ssr).getMetadataBuilder().build();
		factory = meta.getSessionFactoryBuilder().build();
    	
	}
	
	private static String readPgpass(String dbname, String username) throws IOException {
    	// Open file
    	String pss = "";
    	String line = "";
    	String[] parts;
    	File file = new File(filePath.toString());
        Scanner inputFile = new Scanner(file);
        // Read lines from the file until no more are left.
        while (inputFile.hasNext() && pss == "") {
            line = inputFile.nextLine();
            // hostname:port:dbname:username:password
            // 0        1    2      3        4
            parts = line.split(":");
            if (parts.length >= 5 && parts[2].equals(dbname) && parts[3].equals(username)) {
            	pss = parts[4].trim();  // deleting end of line
            }   
        }
        // Close the file.
        inputFile.close();
        return pss;
    }
	
    public void saveToTbl(Object myo) {
    	session = factory.openSession();
    	Transaction t = session.beginTransaction();   
    	session.persist(myo);
        t.commit();
        session.close();
    }
    
    public Object findIt(Class mycls, int id) {
    	session = factory.openSession();  
    	Object result = session.get(mycls, id);
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
