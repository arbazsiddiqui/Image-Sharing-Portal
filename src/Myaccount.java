import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import javax.swing.JLabel;


public class Myaccount extends JFrame {

	private JPanel contentPane;
	PreparedStatement pst=null;
	ResultSet rs=null;
	static String[] albumname = new String[10];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Myaccount frame = new Myaccount();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Connection connect=null;
	public Myaccount() {
		connect=DBConnect.dbConn();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome, "+ Login.usern );
		lblWelcome.setBounds(10, 11, 173, 14);
		contentPane.add(lblWelcome);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Upload up=new Upload();
				up.setVisible(true);
			}
		});
		btnUpload.setBounds(175, 22, 120, 23);
		contentPane.add(btnUpload);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Image_Search is=new Image_Search();
				is.setVisible(true);
			}
		});
		btnSearch.setBounds(175, 56, 120, 23);
		contentPane.add(btnSearch);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.usern=null;
				System.exit(0);
				
			}
		});
		btnLogout.setBounds(175, 226, 120, 23);
		contentPane.add(btnLogout);
		
		JButton btnMyImages = new JButton("My Images");
		btnMyImages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try{
		        	String user = Login.usern;
		        	String q="select * from portal.photo where username="+"'"+user+"'";
		        	System.out.println(q);
		        	pst= connect.prepareStatement(q);
		          	System.out.println("12345");
		          //	pst.setString(1, tag1);
		        	rs=pst.executeQuery();
		        	System.out.println("dgfhj");
		        	show_image3 ig=new show_image3(rs);
		        	ig.setVisible(true);
		        }catch(Exception e1)
		        {
		        	
		        }
			}
		});
		btnMyImages.setBounds(175, 124, 120, 23);
		contentPane.add(btnMyImages);
		
		JButton btnCreateAlbum = new JButton("Create Album");
		btnCreateAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				album up=new album();
				up.setVisible(true);
			}
		});
		btnCreateAlbum.setBounds(175, 90, 120, 23);
		contentPane.add(btnCreateAlbum);
		
		JButton btnMyAlbums = new JButton("My albums");
		btnMyAlbums.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        try{
		        	String user = Login.usern;
		        	String q="select name from portal.album where username="+"'"+user+"'";
		        	pst= connect.prepareStatement(q);
		        	rs=pst.executeQuery();
		        	int i=0;
		        	while(rs.next())
		        	{
		        		
		        		albumname[i] = rs.getString(1);
		        		i++;
		        	}
		        	for(i=0; i<5; i++)
		        	{
		        		System.out.println(albumname[i]);
		        	}
		        	
					myalbums up=new myalbums();
					up.setVisible(true);
		        }
		        catch(Exception e1)
		        {
		        	
		        }
				
			}
		});
		btnMyAlbums.setBounds(175, 158, 120, 23);
		contentPane.add(btnMyAlbums);
		
		JButton btnNewButton = new JButton("My Favourite");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        try{
		        	String user = Login.usern;
		        	String q="select * from portal.fav where username="+"'"+user+"'";
		        	System.out.println(q);
		        	pst= connect.prepareStatement(q);
		          	System.out.println("12345");
		          //	pst.setString(1, tag1);
		        	rs=pst.executeQuery();
		        	System.out.println("dgfhj");
		        	show_image3 ig=new show_image3(rs);
		        	ig.setVisible(true);
		        }catch(Exception e1)
		        {
		        	
		        }
			}
		});
		btnNewButton.setBounds(175, 192, 120, 23);
		contentPane.add(btnNewButton);
	}
}
