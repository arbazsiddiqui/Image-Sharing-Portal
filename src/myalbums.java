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
import java.sql.*;

import javax.swing.JLabel;

public class myalbums extends JFrame {

	private JPanel contentPane;
	PreparedStatement pst=null;
	ResultSet rs=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myalbums frame = new myalbums();
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
	public myalbums() {
		connect=DBConnect.dbConn();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JComboBox comboBox = new JComboBox(Myaccount.albumname);
		comboBox.setBounds(149, 53, 120, 20);
		contentPane.add(comboBox);
		
		JButton btnShowImages = new JButton("Show Images");
		btnShowImages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        try{
		        	String curalbum = (String) comboBox.getSelectedItem();
		        	String q="select albumid from portal.album where name="+"'"+curalbum+"'";
		        	
		        	pst = connect.prepareStatement(q);
		        	rs=pst.executeQuery();
		        	rs.next();
		        	int albumid=rs.getInt(1);
		        	System.out.print(albumid);
		        	
		        	String user = Login.usern;
		        	String q1="select * from portal.photo where albumid="+"'"+albumid+"'";
		        	System.out.println(q1);
		        	PreparedStatement pst1 = connect.prepareStatement(q1);
		          
		          //	pst.setString(1, tag1);
		        	ResultSet rs1 = pst1.executeQuery();
		        	show_image3 ig=new show_image3(rs1);
		        	ig.setVisible(true);
		    
		        }catch(Exception e1)
		        {
		        	
		        }
			}
			
		});
		btnShowImages.setBounds(149, 138, 120, 23);
		contentPane.add(btnShowImages);
	}
}
