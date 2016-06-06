package SocketProgrammingPartTwo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class A_Chat_Server_Return implements Runnable {


	//Globals
	Socket SOCK;
	Scanner input;
	PrintWriter out;
	String message;
	
	public A_Chat_Server_Return(Socket socket) {
		// TODO Auto-generated constructor stub
		this.SOCK = socket;
	}

	public  void checkConnection() throws IOException
	{
		if(!SOCK.isConnected())
		{
			for(int i = 1;i<=A_Chat_Server.ConnectionArray.size();i++)
			{
				if(A_Chat_Server.ConnectionArray.get(i-1)==SOCK)
				{
					A_Chat_Server.ConnectionArray.remove(i-1);
				}
			}
			for(int i=1;i<=A_Chat_Server.ConnectionArray.size();i++)
			{
				Socket tSock = (Socket)A_Chat_Server.ConnectionArray.get(i-1);
				PrintWriter tOut = new PrintWriter(tSock.getOutputStream());
				tOut.println(tSock.getLocalAddress().getHostName() + " : disconnected");
				tOut.flush();
				// Shows disconnected at server
				System.out.println(tSock.getLocalAddress().getHostName() + " : disconnected");
			}
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			input  = new Scanner(SOCK.getInputStream());
			out = new PrintWriter(SOCK.getOutputStream());
			while(true)
			{
				if(!input.hasNext())
				return;
				 message = input.nextLine();
				 System.out.println("Client : "+message);
					for(int i=1;i<=A_Chat_Server.ConnectionArray.size();i++)
					{
						Socket tSock = (Socket)A_Chat_Server.ConnectionArray.get(i-1);
						PrintWriter tOut = new PrintWriter(tSock.getOutputStream());
						tOut.println(message);
						tOut.flush();
						System.out.println("Sent to : "+tSock.getLocalAddress().getHostName());
					}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				SOCK.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
