package SocketProgrammingPartTwo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;
public class A_Chat_Client_Gui {

	private static A_Chat_Client chatClient;
	public static String username = "Anonymous";
	
	// GUI Globals Main Window
	static JFrame MainWindow = new JFrame();
	
	static JButton B_ABOUT = new JButton();
	static JButton B_CONNECT = new JButton();
	static JButton B_DISCONNECT = new JButton();
	static JButton B_HELP = new JButton();
	static JButton B_SEND = new JButton();
	
	static JLabel L_Message = new JLabel("Message: ");
	static JTextField TF_Message = new JTextField(20);
	
	static JLabel L_Conversation= new JLabel();
	static JTextArea TA_Conversation = new JTextArea();
	static JScrollPane SP_Conversation = new JScrollPane();
	
	static JLabel L_Online  = new JLabel();
	static JList JL_Online = new JList();
	static JScrollPane SP_Online = new JScrollPane();
	
	static JLabel L_LoggedInAs =new JLabel();
	static JLabel L_LoggedInAsBox =new JLabel();

	
	// Gui Globals - Login Window
	static JFrame LogInWindow = new JFrame();
	static JTextField TF_Username = new JTextField(25);
	static JButton B_Enter = new JButton();
	static JLabel L_EnterUserName = new JLabel("Enter Username : ");
	static JPanel P_Login = new JPanel();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BuildMainWindow();
		Initialize();
	}

	public static void connect(){
		try
		{
			final int PORT = 9999;
			final String HOST = "localhost";
			Socket SOCK = new Socket(HOST,PORT);
			chatClient = new A_Chat_Client(SOCK);
			System.out.println("You Connected  to  : "+HOST);
			
			// Send Name To Add To Online List
			PrintWriter out =  new PrintWriter(SOCK.getOutputStream());
			out.println(username);
			out.flush();
			
			Thread X = new Thread(chatClient);
			X.start();
		}catch(Exception e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null,"Server Not Responding.. ");
			System.exit(0);
		}
	}
	
	
	private static void Initialize() {
		// TODO Auto-generated method stub
		B_SEND.setEnabled(false);
		B_CONNECT.setEnabled(true);
		B_DISCONNECT.setEnabled(false);
	}

	private static void BuildMainWindow() {
		// TODO Auto-generated method stub
		MainWindow.setTitle(username+"'s chat box");
		MainWindow.setSize(500,320);
		MainWindow.setResizable(false);
		MainWindow.setVisible(true);
		MainWindow.setLocation(220,180);
		configureMainWindow();
		mainWIndowAction();
	}

	private static void mainWIndowAction() {
		// TODO Auto-generated method stub
		B_SEND.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actionBSend();
			}
		});
		B_CONNECT.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				actionBConnect();
				buildLoginWindow();
			}
		});
		B_DISCONNECT.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actionBDSisconnect();
			}
		});
		B_HELP.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actionBHelp();
			}
		});
		B_ABOUT.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actionBAbout();
			}
		});
		
	}

	protected static void buildLoginWindow() {
		// TODO Auto-generated method stub
		LogInWindow.setTitle("What's Your Name ? ");
		LogInWindow.setSize(400,100);
		LogInWindow.setResizable(false);
		LogInWindow.setLocation(250,200);
		LogInWindow.setVisible(true);
		P_Login = new JPanel();
		P_Login.add(L_EnterUserName);
		P_Login.add(TF_Username);
		B_Enter.setText("ENTER");
		P_Login.add(B_Enter);
		LogInWindow.add(P_Login);
		loginAction();
		
	}

	protected static void actionBAbout() {
		// TODO Auto-generated method stub
		
	}

	protected static void actionBHelp() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "MultiClient Chat Program by JATIN KHATTAR 2016");
	}

	protected static void actionBDSisconnect() {
		// TODO Auto-generated method stub
		try {
			chatClient.DISCONNECT();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected static void actionBSend() {
		// TODO Auto-generated method stub
		if(!TF_Message.getText().equals(""))
		{
			chatClient.SEND(TF_Message.getText());
			TF_Message.requestFocus();
			TF_Message.setText("");
		}
	}

	private static void configureMainWindow() {
		// TODO Auto-generated method stub
		MainWindow.setBackground(new java.awt.Color(255,255,255));
		MainWindow.getContentPane().setLayout(null);
		
		B_SEND.setBackground(new java.awt.Color(0,0,255));
		B_SEND.setForeground(new java.awt.Color(255,255,255));
		B_SEND.setText("SEND");
		MainWindow.getContentPane().add(B_SEND);
		B_SEND.setBounds(250, 40, 81, 25);
		
		B_DISCONNECT.setBackground(new java.awt.Color(0,0,255));
		B_DISCONNECT.setForeground(new java.awt.Color(255,255,255));
		B_DISCONNECT.setText("DISCONNECT");
		MainWindow.getContentPane().add(B_DISCONNECT);
		B_DISCONNECT.setBounds(10, 40, 110, 25);
		
		B_CONNECT.setBackground(new java.awt.Color(0,0,255));
		B_CONNECT.setForeground(new java.awt.Color(255,255,255));
		B_CONNECT.setText("CONNECT");
		MainWindow.getContentPane().add(B_CONNECT);
		B_CONNECT.setBounds(130, 40, 110, 25);
		
		B_HELP.setBackground(new java.awt.Color(0,0,255));
		B_HELP.setForeground(new java.awt.Color(255,255,255));
		B_HELP.setText("HELP");
		MainWindow.getContentPane().add(B_HELP);
		B_HELP.setBounds(420, 40, 70, 25);
		
		B_ABOUT.setBackground(new java.awt.Color(0,0,255));
		B_ABOUT.setForeground(new java.awt.Color(255,255,255));
		B_ABOUT.setText("ABOUT");
		MainWindow.getContentPane().add(B_ABOUT);
		B_ABOUT.setBounds(340, 40, 75, 25);
		
		L_Message.setText("Message:");
		MainWindow.getContentPane().add(L_Message);
		L_Message.setBounds(10,10,60,20);
		
		TF_Message.setForeground(new java.awt.Color(0,0,255));
		TF_Message.requestFocus();
		MainWindow.getContentPane().add(TF_Message);
		TF_Message.setBounds(70, 4, 260, 30);
		
		L_Conversation.setText("Conversation");
		L_Conversation.setHorizontalAlignment(SwingConstants.CENTER);
		MainWindow.getContentPane().add(L_Conversation);
		L_Conversation.setBounds(100,70,140,16);

		TA_Conversation.setColumns(20);
		TA_Conversation.setFont(new java.awt.Font("Tahoma",0,12));
		TA_Conversation.setForeground(new java.awt.Color(0,0,255));
		TA_Conversation.setLineWrap(true);
		TA_Conversation.setRows(5);
		TA_Conversation.setEditable(false);
		
		SP_Conversation.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		SP_Conversation.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);	
		SP_Conversation.setViewportView(TA_Conversation);
		MainWindow.getContentPane().add(SP_Conversation);
		SP_Conversation.setBounds(10,90,330,180);
		
		L_Online.setHorizontalAlignment(SwingConstants.CENTER);
		L_Online.setText("Online Users");
		L_Online.setToolTipText("");
		MainWindow.getContentPane().add(L_Online);
		L_Online.setBounds(350,70,130,16);

//		String [] testnames = {"ABC","DEF","XYZ","YYY"};
		JL_Online.setForeground(new java.awt.Color(0,0,255));
//		JL_Online.setListData(testnames);
		
		SP_Online.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		SP_Online.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);	
		SP_Online.setViewportView(JL_Online);
		MainWindow.getContentPane().add(SP_Online);
		SP_Online.setBounds(350,90,130,180);
		
		L_LoggedInAs.setFont(new java.awt.Font("Tahoma",0,12));
		L_LoggedInAs.setText("Currently Logged In As");
		MainWindow.getContentPane().add(L_LoggedInAs);
		L_LoggedInAs.setBounds(348,0,140,15);
		
		L_LoggedInAsBox.setFont(new java.awt.Font("Tahoma",0,12));
		L_LoggedInAsBox.setHorizontalAlignment(SwingConstants.CENTER);
		L_LoggedInAsBox.setForeground(new java.awt.Color(0,0,255));
		L_LoggedInAsBox.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
		MainWindow.getContentPane().add(L_LoggedInAsBox);
		L_LoggedInAsBox.setBounds(340,17,150,20);
	}
	
		public static void loginAction()
		{
			B_Enter.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
						actionBEnter();
				}
			});
		}

		protected static void actionBEnter() {
			// TODO Auto-generated method stub
			if(!TF_Username.getText().equals(""))
			{
				 username = TF_Username.getText().trim();
				 L_LoggedInAsBox.setText(username);
				 A_Chat_Server.CurrentUsers.add(username);
				 MainWindow.setTitle(username+"'s chat box");
				 LogInWindow.setVisible(false);
				 B_SEND.setEnabled(true);
				 B_DISCONNECT.setEnabled(true);
				 B_CONNECT.setEnabled(false);
				 connect();
			}else{
				JOptionPane.showMessageDialog(null,"Please Enter a Name !!");
			}
		}

		

}
