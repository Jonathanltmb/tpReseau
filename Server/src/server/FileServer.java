package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class FileServer {
	// The default port number that this server will listen on
	private final static int DEFAULT_PORT_NUMBER = 1234;

	// The maximum connections the operating system should
	// accept when the server is not waiting to accept one.
	private final static int MAX_BACKLOG = 20;

	// Timeout in milliseconds for accepting connections.
	// It may go this long before noticing a request to shut down.
	private final static int TIMEOUT = 500;

	// The port number to listen for connections on
	private int portNumber;

	// Sets to true when server should shut down.
	private boolean shutDownFlag = false;

	private int activeConnectionCount = 0;

	//private Transport tr;

	/**
	 * Constructor for server that listens on default port.
	 */
	public FileServer() {
		this(DEFAULT_PORT_NUMBER);
	} // constructor()

	/**
	 * Two instances of the server will not be able to successfully listen for
	 * connections on the same port.
	 * 
	 * @param port The port number to listen on.
	 */
	public FileServer(int port) {
		portNumber = port;
	} // constructor(int)

	/**
	 * Return the number of active connections
	 */
	public int getActiveConnectionCount() {
		return activeConnectionCount;
	} // getActiveConnectionCount()

	/**
	 * This is the top level method for the file server. It does not return until
	 * the server shuts down.
	 */
	public void runServer() {
		ServerSocket s;

		try {
			// Create the ServerSocket.
			System.out.println("Lancement du serveur");
			s = new ServerSocket(portNumber, MAX_BACKLOG);

			// Set timeout for accepting connections so server won't
			// wait forever until noticing a request to shut down.
			s.setSoTimeout(TIMEOUT);
		} catch (IOException e) {
			System.err.println("Unable to create socket");
			e.printStackTrace();
			return;
		} // try

		// loop to keep accepting new connections and talking to clients
		try {
			Socket socket;
			serverLoop: while (true) { // Keep accepting connections.
				try {
					socket = s.accept(); // Accept a connection.
				} catch (java.io.InterruptedIOException e) {
					socket = null;
					if (!shutDownFlag)
						continue serverLoop;
				} // try
				if (shutDownFlag) {
					if (socket != null)
						socket.close();
					s.close();
					return;
				} // if
					// Create worker object to process connection.
				System.out.println("Acceptation d'un client ");
				new FileServerWorker(socket);
			} // while
		} catch (IOException e) {
			// if there is an I/O error just return
		} // try
	} // start()

	/**
	 * This is called to request the server to shut down.
	 */
	public void stop() {
		shutDownFlag = true;
	} // shutDown()

	private class FileServerWorker implements Runnable {
		// private Socket s;
		//private Transport tr;
		private ProtocoleServer pro;
		FileServerWorker(Socket s) {

			pro = new ProtocoleServer(s);
			//tr = new Transport(s);
			new Thread(this).start();

		}

		public void run() {
			/*
			 * InputStream in;
			 * 
			 * PrintStream out = null;
			 * 
			 */

			String fileName = "";
			FileInputStream f;

			activeConnectionCount++;
			System.out.println("Lancement du thread pour gérer" + "le protocole avec un client");
			// read the file name sent by the client and open the file.
			try {
				// in = s.getInputStream();
				// out = new PrintStream(s.getOutputStream());
				fileName = (String) pro.recevoir();
				f = new FileInputStream(fileName);
			} catch (IOException e) {
				activeConnectionCount--;
				/*
				try {
					if (tr.recevoir() != null)
						tr.envoyer("Bad:" + fileName);
					tr.fermer();
				} catch (IOException | ClassNotFoundException ie) {
				}
				
				*/
				
				pro.envoyerBad();
				
				return;
			} // try

			// send contents of file to client.
			BufferedReader br = null;
			// byte[] buffer = new byte[4096];
			try {
				
				pro.envoyerGood();
				

				br = new BufferedReader(new FileReader(fileName));
				String line;

				// int len;
				String t = "";
				while (!shutDownFlag && (line = br.readLine()) != null) {
					t += line;

					// out.write(buffer, 0, len);
				}
				
				
				
				pro.envoyerTxt(t);
				//tr.envoyer(t);// while
			} catch (IOException e) {
			} finally {
				try {
					activeConnectionCount--;
					
					if (br != null)
						br.close();
				} catch (IOException e) {
				} // try
			} // try
		} // run()
	} // class FileServerWorker
} // class FileServer
