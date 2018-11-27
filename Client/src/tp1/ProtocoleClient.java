package tp1;

import java.io.IOException;

import tp1.Transport;

public class ProtocoleClient {
	
	
	private static final int PORT = 1234;
	public Transport tr;

	public ProtocoleClient(String argv) {
		try {
			// s = new Socket();
			// creation du transport
			this.tr = new Transport(argv, PORT);

		} catch (Exception e) {
			System.err.println("Unable to connect to server");
			e.printStackTrace();
			System.exit(1);
		}
		
		// TODO Auto-generated constructor stub
	}

	public Transport getTr() {
		return tr;
	}

	public void setTr(Transport t) {
		this.tr = t;
	}

	public void envoyer(String argv) {
		try {
			// s = new Socket();
			// creation du transport
			tr.envoyer(argv);

		} catch (Exception e) {
			System.err.println("Unable to connect to server");
			e.printStackTrace();
			System.exit(1);
		}
	}

	public String test(String argv) {
		try {

			// OutputStream out = s.getOutputStream();
			// new PrintStream(out).print(argv[1]+"\n");

			tr.envoyer(argv);
		//	int ch;
			// in = new BufferedInputStream(s.getInputStream());
			// DataInputStream din = new DataInputStream(in);

			String serverStatus = (String) tr.recevoir();
			if (serverStatus.startsWith("Bad")) {
				return("Bad");
				//exitCode = 1;
			} else {
				
				/*
				 * while((ch = (int) tr.recevoir()) >= 0) { System.out.write((char)ch); } //
				 * while
				 */
				return((String)tr.recevoir());
			} // if
		} catch (IOException | ClassNotFoundException e) {
			// exitCode = 1;
			return("Bad");
		} finally {
			try {
				if (tr != null)
					tr.fermer();

			} catch (IOException e) {

			}
		}
	}

	

}
