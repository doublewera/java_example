package callcentre;

import java.net.*;
import java.io.*;
import java.util.Random;

public class CallingUser {
	String host;
	int port;
	Socket socket;
	
	CallingUser() {
		host = "localhost";
		port = 18765;
	}
	
	void connect() {
		try {
		    socket = new Socket(host, port);
		}catch(IOException e){
		    System.out.println("Vechno ne dozvonishsia");
		}
	}
    
	void bye() {
		try {
			socket.close();
		}catch(IOException e){
		    System.out.println("Vot i pogovorili...");
		}
	}
	
	public static void main(String[] args) {
		Random random = new Random();
		CallingUser cu = new CallingUser();//, host, port);
		cu.connect();
	    try{
	    	String msg = random.nextInt(1000) + "";
	    	cu.say(msg);
	    	System.out.println(cu.hear());
	    }catch(IOException e){
	    	System.out.printf("Сервер на порту %d не отвечает", 18765);  // port
	    	e.printStackTrace();
	    }
	    cu.bye();
	}
	
	String hear() throws IOException {
		String result = "";
        ObjectInputStream ois = new ObjectInputStream(
        		socket.getInputStream());
        try {
			result = (String) ois.readObject ();
		} catch (ClassNotFoundException onf) {
			System.out.printf("Cannot read ");
		}
        System.out.println(result);
        //ois.close();  // почему нельзя закрывать?
		return result;
	}
	
	void say(String msg) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(
        		socket.getOutputStream());
        String isaid = "I think, the answer is " + msg + "\n";
        oos.writeObject(isaid);
        System.out.println(isaid);
        //oos.close();  // почему нельзя закрывать?
	}

}
