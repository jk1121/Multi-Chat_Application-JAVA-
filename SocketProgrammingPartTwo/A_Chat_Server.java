package SocketProgrammingPartTwo;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class A_Chat_Server {

	public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
	public static ArrayList<String> CurrentUsers = new ArrayList<String>();
	static final int PORT = 9999;
	
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
 
		ServerSocket SERVER = new ServerSocket(PORT);
		System.out.println("Waiting for Clients.....");
		
		while(true)
		{
			Socket SOCK = SERVER.accept();
			ConnectionArray.add(SOCK);
			
			System.out.println("Client Connected From "+SOCK.getLocalAddress().getHostName());
			addUserName(SOCK);
			A_Chat_Server_Return CHAT = new A_Chat_Server_Return(SOCK);
			Thread X = new Thread(CHAT);
			X.start();
		}
	}

	public static void addUserName(Socket SOCK) throws Exception{
		// TODO Auto-generated method stub
			Scanner input = new Scanner(SOCK.getInputStream());
			String userName = input.nextLine();
			CurrentUsers.add(userName);
			
			for(int i =1 ;i<=A_Chat_Server.ConnectionArray.size();i++)
			{
				Socket tempSock = (Socket)A_Chat_Server.ConnectionArray.get(i-1);
				PrintWriter out = new PrintWriter(tempSock.getOutputStream());
				out.println("#?!"+ CurrentUsers);
				out.flush();
						
			}
	}

}
