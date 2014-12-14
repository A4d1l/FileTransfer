import java.net.*;
import java.io.*;

public class GreetingClient
{
	 private byte[] buffer = new byte[1000];
	private OutputStream outToServer;// = client.getOutputStream();
    private DataOutputStream out;// =  new DataOutputStream(outToServer);
	private InputStream inFromServer;// = client.getInputStream();
    private DataInputStream in;// =  new DataInputStream(inFromServer);
    private BufferedReader br;// = new BufferedReader( new InputStreamReader(System.in));
	private String output_file,str;
	private FileOutputStream outputStream;
	private Socket client;
	public GreetingClient(String serverName,int port){
		String str="sdf";
        System.out.println("Connecting to " + serverName
                             + " on port " + port);
        try{
        	client = new Socket(serverName, port);
        }
        catch(IOException ex){}
        System.out.println("Just connected to "+ client.getRemoteSocketAddress());
        try{
        	outToServer = client.getOutputStream();
        	out =  new DataOutputStream(outToServer);
			inFromServer = client.getInputStream();
        	in =  new DataInputStream(inFromServer);
        	br = new BufferedReader( new InputStreamReader(System.in));
        }	
        catch(IOException e){}
	}
	public void DoSomething(){
		try
      {
        System.out.println("enter the input filename with path");
		str=br.readLine();
		out.writeUTF(str);//sending the name of the file
		//out.writeUTF("Hello from "+ client.getLocalSocketAddress());
		System.out.println("enter the output filename with path");
		output_file=br.readLine();
		outputStream =  new FileOutputStream(output_file);
		int trxBytes=0;
		while( (trxBytes=in.read(buffer,0,buffer.length))!=-1){
			//in.readFully(buffer);
		//str=br.readLine();

		System.out.println("Transfering bytes : "+trxBytes+"buffer.length="+buffer.length );
		if(trxBytes<=0)
			break;
         outputStream.write(buffer,0,trxBytes);	
         System.out.println("Transfering bytes : "+trxBytes+"buffer.length="+buffer.length );
         //System.out.println("Server says " + in.readUTF());
     	}
     	//while(buffer!=null);
     	outputStream.close();
     	System.out.println("read successfully");
         client.close();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
	}
   public static void main(String [] args)
   {
      String serverName = args[0];
      int port = Integer.parseInt(args[1]);
      GreetingClient gc=new GreetingClient(serverName,port);
      gc.DoSomething();
      
      
   }
}
