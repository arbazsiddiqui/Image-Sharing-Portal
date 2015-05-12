import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;


public class album extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	PreparedStatement pst=null;
	ResultSet rs=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					album frame = new album();
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
	public album() {
		connect=DBConnect.dbConn();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAlbumTitle = new JLabel("Album Title :");
		lblAlbumTitle.setBounds(102, 73, 73, 14);
		contentPane.add(lblAlbumTitle);
		
		textField = new JTextField();
		textField.setBounds(183, 70, 134, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAddImages = new JButton("Add Images");
		btnAddImages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try{
		        	String user = Login.usern;
					String q1="insert into portal.album (name, cdate, username) values (?, ?, ?) ";
					PreparedStatement pst=connect.prepareStatement(q1);
					pst.setString(1, textField.getText());
					pst.setDate(2, new java.sql.Date((new Date(System.currentTimeMillis())).getTime()));
					pst.setString(3, Login.usern);
					pst.execute();
					pst.close();
		        	
		        	
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
		btnAddImages.setBounds(142, 123, 118, 23);
		contentPane.add(btnAddImages);
	}
}
