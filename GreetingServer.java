import java.net.*;
import java.io.*;

public class GreetingServer implements Runnable //extends Thread
{
   private ServerSocket serverSocket;
   private DoStuff ds=null;
   private Thread thread=null; 
   public GreetingServer(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      System.out.println("server started");
      start();
     // serverSocket.setSoTimeout(10000);
   }

   public void start(){
   	if (thread == null)
      {  thread = new Thread(this); 
         thread.start();
      }

   }
   public void run()
   {
      while(true)
      {
         //try
         //{
            System.out.println("Waiting for client on port " +
            serverSocket.getLocalPort() + "...");
            try{
            	Socket server = serverSocket.accept();
            	addThread(server);
            }
            catch(IOException e){}
            //addThread(server);
            //server.close();
         //}catch(IOException s)
         //{
           // System.out.println("Socket timed out!");
           // break;
         //}
      }
   }

   public void addThread(Socket socket)
   {  System.out.println("Client accepted: " + socket);
      ds = new DoStuff( socket);
      //try
      //{  //client.open();
         ds.start();
     // }
      //catch(IOException ioe)
      //{  System.out.println("Error opening thread: " + ioe); 
 	 //}
   }

      public static void main(String [] args)
   {
      int port = Integer.parseInt(args[0]);
      try
      {
      	GreetingServer gs=new GreetingServer(port);
         //Thread t = new GreetingServer(port);
         //t.start();
      	//gs.Run();

      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }

}

   
