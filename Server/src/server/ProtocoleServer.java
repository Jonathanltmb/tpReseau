package server;

import java.io.IOException;
import java.net.Socket;

import server.Transport;

public class ProtocoleServer {

	
	
	
	private static final int PORT = 1234;
	public Transport tr;

	public ProtocoleServer(Socket s) {
		try {
			// s = new Socket();
			// creation du transport
			this.tr = new Transport(s);

		} catch (Exception e) {
			System.err.println("Unable to connect to server");
			e.printStackTrace();
			System.exit(1);
		}
		
		// TODO Auto-generated constructor stub
	}
	
	
	public String recevoir() {
		try {
		return (String)tr.recevoir();
		} catch (IOException | ClassNotFoundException ie) {
		 return null;
		}
	}
	
	public void envoyerBad() {
		
		try {
		
				tr.envoyer("Bad");
			tr.fermer();
		} catch (IOException ie) {
		}
	}
	
	
public void envoyerGood() {
		
		try {
			
				tr.envoyer("Good");
		
		} catch (IOException ie) {
		}
	}



public void envoyerTxt(String t) {
	try {
		tr.envoyer(t);
		//tr.fermer();
	} catch (IOException ie) {
	}
}
	
	
}
