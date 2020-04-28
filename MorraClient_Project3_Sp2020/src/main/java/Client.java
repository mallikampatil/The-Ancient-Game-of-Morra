import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.ListView;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Thread {
	
    String ip ; 
    int port;
    Socket socketClient;
	
	ObjectOutputStream out;
	ObjectInputStream in;
    
	private Consumer<MorraInfo> callback;
	
	Client(Consumer<MorraInfo> call, String ip, int port){
		callback = call;
		this.port = port; 
		this.ip = ip;
	}

	public void run() {
		try {
			socketClient = new Socket(ip, port);
		    out = new ObjectOutputStream(socketClient.getOutputStream());
		    in = new ObjectInputStream(socketClient.getInputStream());
		    socketClient.setTcpNoDelay(true);
			}
			catch(Exception e) {}
			
			while(true) {
				try {
					MorraInfo message = (MorraInfo)in.readObject();
					callback.accept(message);
				}
				catch(Exception e) {}
			}
		} /* end run() */ 
	
	public void send (MorraInfo data) {
		try {
			out.writeObject(data); 
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}
}
    
