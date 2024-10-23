/**
 * 
 */
package callcentre;

import java.io.*;
import java.lang.System.Logger;
import java.net.*;

/**
 * 
 */

public class HelpDesk {
	int port;
	ServerSocket listener;   // слушающий сокет
	private static final Logger logger = System.getLogger(HelpDesk.class.getName());
	
	HelpDesk(int port) {
		this.port = port;
		try {
		    listener = new ServerSocket (port);
			//System.out.printf("Я у телефона %d…", port);
			logger.log(Logger.Level.INFO, "Я у телефона {0}…", port);
		} catch (IOException ioe) {
			logger.log(Logger.Level.ERROR, "Не могу подойти {0}, перезвоните позже", port);
		}
	}
	
	void listen () throws IOException {
		Socket client = listener.accept();
		String message = "";
		ObjectInputStream in = new ObjectInputStream (client.getInputStream ());
		ObjectOutputStream out = new ObjectOutputStream (client.getOutputStream ());
		try {
			message = (String) in.readObject ();
			out.writeObject("Horosho, budet sdelano");
		} catch (ClassNotFoundException onf) {
			logger.log(Logger.Level.ERROR, "Пишите разборчиво!");
			//return;
		}
		// String message = in.readLine();  // deprecated
		System.out.println (message);
	}
	public static void main(String[] args) {
        HelpDesk server = new HelpDesk(18765);
        while (true)
        	try {
			    server.listen();
    		}
    		catch (IOException ex) {
    			logger.log(Logger.Level.ERROR, "Вас не слышно");
    			ex.printStackTrace();
    		}
	}

}
