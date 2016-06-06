package SocketProgrammingPartTwo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class A_Chat_Client implements Runnable {

	Socket SOCK;
	Scanner input;
	PrintWriter out;
	Scanner send = new Scanner(System.in);
	public A_Chat_Client(Socket socket){
		this.SOCK = socket; 
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			input  = new Scanner(SOCK.getInputStream());
			out = new PrintWriter(SOCK.getOutputStream());
			out.flush();
			checkStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				SOCK.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	private void checkStream() {
		// TODO Auto-generated method stub
		while(true)
		{
			recieve();
		}
	}
	private void recieve() {
		// TODO Auto-generated method stub
		if(input.hasNext())
		{
			String message = input.nextLine();
			if(message.contains("#?!"))
			{
				String temp1 = message.substring(3);
				temp1 = temp1.replace("[","");
				temp1 = temp1.replace("]","");
				String currentUsers[] = temp1.split(", ");
				A_Chat_Client_Gui.JL_Online.setListData(currentUsers);
			}
			else
			{
				A_Chat_Client_Gui.TA_Conversation.append(message + "\n");
			}
		}
	}
	public void SEND(String text) {
		// TODO Auto-generated method stub
		out.println(A_Chat_Client_Gui.username +" : "+text);
		out.flush();
		A_Chat_Client_Gui.TF_Message.setText("");
//		A_Chat_Client_Gui.TA_Conversation.append(text + "\n");
	}
	public void DISCONNECT() throws Exception {
		// TODO Auto-generated method stub
		out.println(A_Chat_Client_Gui.username+" has disconnected.");
		out.flush();
		SOCK.close();
		JOptionPane.showMessageDialog(null,"You Disconncted");
		System.exit(0);
	}
}
