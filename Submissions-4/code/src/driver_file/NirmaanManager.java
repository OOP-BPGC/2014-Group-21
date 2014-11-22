package driver_file;

import java.awt.EventQueue;
import Base_Classes.*;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import com.google.gson.Gson;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JTabbedPane;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;

public class NirmaanManager {

	private JFrame frmNirmaanActivityManager;
	private Designation d;
	private String js;
	private Person p;
	/**
	 * Launch the application.
	 */
	
	public void setDesignation(Designation des){
		d = des;
	}

	public void setPerson(Core c){
		try {
			p = (Core) c.clone();
		} catch (CloneNotSupportedException e) {
			
		}
	}
	
	public void setPerson(ProjectHead ph){
		try {
			p = (ProjectHead) ph.clone();
		} catch (CloneNotSupportedException e) {
			
		}
	}
	
	public void setPerson(Volunteer v){
		try {
			p = (Volunteer) v.clone();
		} catch (CloneNotSupportedException e) {
			
		}
	}
	
	public static void main(Designation des, String args) {
		final Designation de = des;
		final String json = args;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NirmaanManager window = new NirmaanManager(de,json);
					window.frmNirmaanActivityManager.setVisible(true);
					
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NirmaanManager(){
		initialize();
	}
	
	public NirmaanManager(Designation des, String json) {
		this.d = des;
		this.js = json;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	 private void initialize() {
		frmNirmaanActivityManager = new JFrame();
		frmNirmaanActivityManager.setTitle("Nirmaan Activity Manager");
		frmNirmaanActivityManager.setBounds(100, 100, 625, 471);
		frmNirmaanActivityManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNirmaanActivityManager.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		final JTextPane txtPane = new JTextPane();
		txtPane.setBackground(Color.LIGHT_GRAY);
		txtPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPane.setEditable(false);
		frmNirmaanActivityManager.getContentPane().add(txtPane, "8, 2, 7, 21, fill, fill");
		
			
		switch(d){
		case PROJECT_HEAD:
			
			
			
			JButton btnVolunteerProjectRequest = new JButton("Project Request");
			frmNirmaanActivityManager.getContentPane().add(btnVolunteerProjectRequest, "4, 4");
			btnVolunteerProjectRequest.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtPane.setText(txtPane.getText() + "\nCheck console for volunteer requests.");
					ProjectHead ph = (ProjectHead) p;
					
					if( ph != null){ 
						ph.handleProjectRequests();
						}
						else{
							Gson gson = new Gson();
							ph = (ProjectHead)gson.fromJson(js, ProjectHead.class);
							ph.handleProjectRequests();
						}
					
				}
			});
			
	
			JButton btnGetProjectsph = new JButton("Get Projects");
			frmNirmaanActivityManager.getContentPane().add(btnGetProjectsph, "4, 8");
			btnGetProjectsph.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtPane.setText(txtPane.getText() + "\nCheck console for list of projects.");
					ProjectHead ph = (ProjectHead) p;
					
					if( ph != null){ 
						ph.getProjects(ph.desig, "projectdatabase");
						}
						else{
							Gson gson = new Gson();
							ph = (ProjectHead)gson.fromJson(js, ProjectHead.class);
							ph.getProjects(ph.desig, "projectdatabase");
						}
					
				}
			});
			
			JButton btnGetPrjName = new JButton("Get Project Name");
			frmNirmaanActivityManager.getContentPane().add(btnGetPrjName, "4, 10");
			btnGetPrjName.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtPane.setText(txtPane.getText() + "\nCheck console for project name.");
					ProjectHead ph = (ProjectHead) p;
					if( ph != null){ 
						ph.getProjectName();
						}
						else{
							Gson gson = new Gson();
							ph = (ProjectHead)gson.fromJson(js, ProjectHead.class);
							ph.getProjectName();
						}
					
				}
			});
			
			JButton btnListMessagesph = new JButton("List Messages");
			frmNirmaanActivityManager.getContentPane().add(btnListMessagesph, "4, 14");
			btnListMessagesph.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtPane.setText(txtPane.getText() + "\nCheck console for list of messages.");
					ProjectHead ph = (ProjectHead) p;
					if( ph != null){ 
						ph.listMessages();
						}
						else{
							Gson gson = new Gson();
							ph = (ProjectHead)gson.fromJson(js, ProjectHead.class);
							ph.listMessages();
						}
					
				}
			});
			
			JButton btnListEventsph = new JButton("List Events");
			frmNirmaanActivityManager.getContentPane().add(btnListEventsph, "4, 16");
			btnListEventsph.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtPane.setText(txtPane.getText() + "\nCheck console for list of events.");
					ProjectHead ph = (ProjectHead) p;
					if( ph != null){ 
						ph.listEvents();
						}
						else{
							Gson gson = new Gson();
							ph = (ProjectHead)gson.fromJson(js, ProjectHead.class);
							ph.listEvents();
						}
					
				}
			});
			
			break;
		case CORE:
			JButton btnGetProjects = new JButton("Get Projects");
			frmNirmaanActivityManager.getContentPane().add(btnGetProjects, "4, 4");
			btnGetProjects.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtPane.setText(txtPane.getText() + "\nCheck console for list of projects.");
					Core c = (Core) p;
					if( c != null){ 
						c.getProjects();
						}
						else{
							Gson gson = new Gson();
							c = (Core)gson.fromJson(js, Core.class);
							c.getProjects();
						}
					
				}
			});
			
			JButton btnCreateProject = new JButton("Create Project");
			frmNirmaanActivityManager.getContentPane().add(btnCreateProject, "4, 6");
			btnCreateProject.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtPane.setText(txtPane.getText() + "\nUse the console to create a project.");
					Core c = (Core) p;
					if( c != null){ 
						c.CreateProject();
						}
						else{
							Gson gson = new Gson();
							c = (Core)gson.fromJson(js, Core.class);
							c.CreateProject();
						}
					
				}
			});
			
			JButton btnCreateMessage = new JButton("Create Message");
			frmNirmaanActivityManager.getContentPane().add(btnCreateMessage, "4, 8");
			btnCreateMessage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtPane.setText(txtPane.getText() + "\nUse the console to create a message.");
					Core c = (Core) p;
					if( c != null){ 
						c.CreateMessage();
						}
						else{
							Gson gson = new Gson();
							c = (Core)gson.fromJson(js, Core.class);
							c.CreateMessage();
						}
					
				}
			});
			
			JButton btnCreateEvent = new JButton("Create Event");
			frmNirmaanActivityManager.getContentPane().add(btnCreateEvent, "4, 10");
			btnCreateEvent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtPane.setText(txtPane.getText() + "\nUse the console to create an event.");
					Core c = (Core) p;
					if( c != null){ 
						c.CreateEvent();
						}
						else{
							Gson gson = new Gson();
							c = (Core)gson.fromJson(js, Core.class);
							c.CreateEvent();
						}
					
				}
			});
			
			JButton btnCreateUser = new JButton("Create User");
			frmNirmaanActivityManager.getContentPane().add(btnCreateUser, "4, 12");
			btnCreateUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtPane.setText(txtPane.getText() + "\nUse the console to create a user.");
					Core c = (Core) p;
					if( c != null){ 
						c.CreateUser();
						}
						else{
							Gson gson = new Gson();
							c = (Core)gson.fromJson(js, Core.class);
							c.CreateUser();
						}
					
				}
			});
			
			JButton btnListMessages = new JButton("List Messages");
			frmNirmaanActivityManager.getContentPane().add(btnListMessages, "4, 14");
			btnListMessages.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtPane.setText(txtPane.getText() + "\nCheck console for list of messages.");
					Core c = (Core) p;
					if( c != null){ 
					c.listMessages();
					}
					else{
						Gson gson = new Gson();
						c = (Core)gson.fromJson(js, Core.class);
						c.listMessages();
					}
				}
			});
			
			JButton btnListEvents = new JButton("List Events");
			frmNirmaanActivityManager.getContentPane().add(btnListEvents, "4, 16");
			btnListEvents.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtPane.setText(txtPane.getText() + "\nCheck console for list of events.");
					Gson gson = new Gson();
					Core c;
					c = (Core)gson.fromJson(js, Core.class);
					c.listEvents();
					
				}
			});
			
			break;
		case VOLUNTEER:
			
			JButton btnGetProjectsv = new JButton("Get Projects");
			frmNirmaanActivityManager.getContentPane().add(btnGetProjectsv, "4, 8");
			btnGetProjectsv.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtPane.setText(txtPane.getText() + "\nCheck console for list of projects.");
					Gson gson = new Gson();
					Volunteer v;
					v = (Volunteer)gson.fromJson(js, Volunteer.class);
					v.getProjects(v.desig, "projectdatabase");
					
				}
			});
			
			if(p.hasProject){
			JButton btnGetPrjNamev = new JButton("Get Current Projects");
			frmNirmaanActivityManager.getContentPane().add(btnGetPrjNamev, "4, 10");
			btnGetPrjNamev.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Gson gson = new Gson();
					Volunteer v;
					v = (Volunteer)gson.fromJson(js, Volunteer.class);
					txtPane.setText(txtPane.getText() + "\nYour current project: " + v.getCurrentProject());
										
				}
			});}
			
			JButton btnListMessagesv = new JButton("List Messages");
			frmNirmaanActivityManager.getContentPane().add(btnListMessagesv, "4, 14");
			btnListMessagesv.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtPane.setText(txtPane.getText() + "\nCheck console for list of messages.");
					Gson gson = new Gson();
					Volunteer v;
					v = (Volunteer)gson.fromJson(js, Volunteer.class);
					v.listMessages();
					
				}
			});
			
			
			JButton btnListEventsv = new JButton("List Events");
			frmNirmaanActivityManager.getContentPane().add(btnListEventsv, "4, 16");
			btnGetProjectsv.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtPane.setText(txtPane.getText() + "\nCheck console for list of events.");
					Gson gson = new Gson();
					Volunteer v;
					v = (Volunteer)gson.fromJson(js, Volunteer.class);
					v.listEvents();
					
				}
			});
			
			break;
		}
		
	}

	public void setPerson(String json) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();	
		switch(d){
		case CORE:
			p = (Core)gson.fromJson(json, Core.class);
			break;
		case PROJECT_HEAD:
			p = (ProjectHead)gson.fromJson(json, ProjectHead.class);
			break;
		case VOLUNTEER:
			p = (Volunteer)gson.fromJson(json, Volunteer.class);
			break;
		}
	}
}
