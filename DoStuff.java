import java.net.*;
import java.io.*; 
public class DoStuff extends Thread{
    private byte[] buffer = new byte[1000];
    private String input_file,output_file;
   	private Socket          socket   = null;
   //	private ChatServer      server   = null;
   	private int             ID       = -1;
   	private DataInputStream streamIn =  null;
    private FileInputStream inputStream; //=  new FileInputStream("aadil.jpg");
  //  private FileOutputStream outputStream; //=  new FileOutputStream("output.jpg");
            // read fills buffer with data and returns
            // the number of bytes read (which of course
            // may be less than the buffer size, but
            // it will never be more).
      private int total;// = 0;
      private int nRead;// = 0;
   	public DoStuff(Socket s){
   		socket=s;
   	}
   public void run(){
   		String str;
   		try{
        	DataInputStream in =  new DataInputStream(socket.getInputStream());
        	DataOutputStream out =   	  new DataOutputStream(socket.getOutputStream());
        	input_file=in.readUTF();//file name to be sent
          inputStream=new  FileInputStream(input_file);
        //  try {
         
               do {
                   try{
                      nRead = inputStream.read(buffer);
                  }
                   catch(IOException ex){}
                  // Convert to String so we can display it.
                  // Of course you wouldn't want to do this with
                  // a 'real' binary file.
                  try{
                    System.out.println("nRead="+nRead);
                    if(nRead!=-1)
                       out.write(buffer,0,nRead);
                     else
                        break;
                      //out.write(buffer,0,0);
                  }
                  catch(IOException e){}
                  //System.out.println(new String(buffer));
                  total += nRead;
              }
            while(nRead!=-1);
            //out.writeUTF("done");  
            socket.close(); 
    	     System.out.println("file sent");
      //}
    	//catch(IOException e){

    	//}
            

	}catch(IOException e){}
}
}

