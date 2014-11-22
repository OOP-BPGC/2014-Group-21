package driver_file;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import Base_Classes.Core;
import Base_Classes.Designation;
import Base_Classes.Message;
import Base_Classes.MessageHelper;
import Base_Classes.Person;
import Base_Classes.ProjectHead;
import Base_Classes.Volunteer;

import com.google.gson.Gson;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import tests.MessageBuilder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Login {

	private JFrame frmLoginNirmaan;
	private JTextField txtUsr;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLoginNirmaan.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginNirmaan = new JFrame();
		frmLoginNirmaan.setTitle("Login - Nirmaan Activity Manager");
		frmLoginNirmaan.setBounds(100, 100, 602, 296);
		frmLoginNirmaan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginNirmaan.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Enter GMail ID");
		frmLoginNirmaan.getContentPane().add(lblNewLabel, "4, 4");
		
		txtUsr = new JTextField();
		frmLoginNirmaan.getContentPane().add(txtUsr, "8, 4");
		txtUsr.setColumns(10);
		
		JLabel lblEnterPassword = new JLabel("Enter password");
		frmLoginNirmaan.getContentPane().add(lblEnterPassword, "4, 8");
		
		txtPassword = new JPasswordField();
		frmLoginNirmaan.getContentPane().add(txtPassword, "8, 8, fill, default");
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String u = txtUsr.getText();
				String p = String.copyValueOf(txtPassword.getPassword());
			
				//if(Base_Classes.GmailUtilities.checkCredentials(u, p)){
				if(true){
					
					//RETRIEVE PERSON FROM USERLIST
					//nm.setPerson();
					Person per = new Person();
					File file1 = new File("userlist.txt");
					String json = "";
					try {
						Scanner in = new Scanner(file1);
						String op = "";
						
						String type;
			
						while (in.hasNextLine()){
							op = in.nextLine();
							Gson gson = new Gson();	
							String splits[] = op.split("@@@@");
								
								String searcher = "\"Credentials\":[\"" + u + "\",\"" + p + "\"]";
								if (splits[2].contains(searcher)){
									type = splits[3];
									json = splits[2];
									//System.out.println(type + "  " + json);
									if (type.contains("CORE")) {
										per = gson.fromJson(json, Core.class);			
									}
									else if (type.contains("PH")) {
										per = gson.fromJson(json, ProjectHead.class);
									}
									else if (type.contains("VOL")){
										per = gson.fromJson(json, Volunteer.class);
									}
									break;
								}
							}
						
						in.close();
					} catch (FileNotFoundException e) {
						System.out.println("Error occured while checking for data. Please check the database");
						//return; UNCOMMENT FOR PRODUCTION CODE
					}
					
					NirmaanManager nm = new NirmaanManager(per.designation,json);
					
					nm.main(per.designation,json);
					nm.setPerson(json);
					nm.setDesignation(per.designation);
					
					frmLoginNirmaan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frmLoginNirmaan.setVisible(false);
				}
				else
					lblStatus.setText("Failed login.");
					//System.exit(0);
			}
		});
		frmLoginNirmaan.getContentPane().add(btnLogin, "8, 12");
		
		lblStatus = new JLabel(" ");
		lblStatus.setBackground(Color.ORANGE);
		frmLoginNirmaan.getContentPane().add(lblStatus, "8, 14");
	}

}
