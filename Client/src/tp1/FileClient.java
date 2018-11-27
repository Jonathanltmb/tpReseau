package tp1;
//gjhghjghjghj
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;

public class FileClient {
    private static final int PORT = 1234;
     public static ProtocoleClient pro;
    
    private static boolean usageOk(String[] argv) {
    	System.out.println(argv[0]);
        if (argv.length != 2) {
            String msg = "usage is: "
                       + "FileClient server-name file-name";
            System.out.println(msg);
            return false;
        } // if
        return true;
    }
    
    public static void main(String[] argv) {/*
        int exitCode = 0;
        
        if (!usageOk(argv)) {
          System.exit(1);
         }
        Socket s = null;
        try {
          //  s = new Socket();
            //creation du transport
            tr = new Transport(argv[0], PORT);
            
        } catch (Exception e) {
            System.err.println("Unable to connect to server");
            e.printStackTrace();
            System.exit(1);
        } 
       // try
        //InputStream in = null;*/
        
    	
    	try {
            //  s = new Socket();
              //creation du protocole
              
              pro = new ProtocoleClient(argv[0]);
              
          } catch (Exception e) {
              System.err.println("Unable to connect to server");
              e.printStackTrace();
              System.exit(1);
          } 
    	
    	
    		System.out.println(pro.test(argv[1]));
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	/*
    	try {
        	
        	
            //OutputStream out = s.getOutputStream();
            //new PrintStream(out).print(argv[1]+"\n");
            
            pro.envoyer(argv[1]);
           // int ch;
           // in = new BufferedInputStream(s.getInputStream());
           // DataInputStream din = new DataInputStream(in);
            
            String serverStatus = (String) tr.recevoir();
            if (serverStatus.startsWith("Bad")) {
                System.out.println ("Bad");
                exitCode = 1;
            } else {
            	System.out.println ("Good");
            	/*
                while((ch = (int) tr.recevoir()) >= 0) {
                    System.out.write((char)ch);
                } // while
                
            	System.out.println(tr.recevoir());
            } // if
        } catch (IOException|ClassNotFoundException e) {
            exitCode = 1;
        } finally {
            try {
                if (tr != null)
                  tr.fermer();
               
            } catch (IOException e) {
            } // try
        } 
    	
    	*/
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	// try
        //System.exit(exitCode);
    } // main(String[])
} // class FileClient
