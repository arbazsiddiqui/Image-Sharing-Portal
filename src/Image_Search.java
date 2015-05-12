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


public class Image_Search extends JFrame {

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
					Image_Search frame = new Image_Search();
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
	public Image_Search() {
		connect=DBConnect.dbConn();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final JComboBox comboBox = new JComboBox(Upload.tags);
		comboBox.setBounds(313, 57, 97, 20);
		contentPane.add(comboBox);
		
		JButton Button1 = new JButton("Search");
		Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Button1.setBounds(171, 92, 89, 23);
		contentPane.add(Button1);
		
		textField = new JTextField();
		textField.setBounds(313, 150, 97, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton Button2 = new JButton("Search");
		Button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try{
		        	String user = textField.getText();
		        	if(connect!=null)
		        	{
		            	System.out.println("00000");
		        	}
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
		
		
		Button2.setBounds(171, 204, 89, 23);
		contentPane.add(Button2);
		
		JLabel lblSearchViaTag = new JLabel("Search via Tag :");
		lblSearchViaTag.setBounds(31, 60, 134, 14);
		contentPane.add(lblSearchViaTag);
		
		JLabel lblSearchViaUsername = new JLabel("Search via Username :");
		lblSearchViaUsername.setBounds(31, 153, 123, 14);
		contentPane.add(lblSearchViaUsername);
		Button1.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        String tag1 =(String)comboBox.getSelectedItem();
		        System.out.println(tag1);
		        try
		        {
		        	if(connect!=null)
		        	{
		            	System.out.println("00000");
		        	}
		        	String q="select * from portal.photo where tag="+"'"+tag1+"'";
		        	//String j="reorg table project.upload_photo";
		        	String l="update portal.photo set views = views + 1 where tag="+"'"+tag1+"'";
		        	//System.out.println(q);
		        	pst= connect.prepareStatement(q);
		        	PreparedStatement pst1 = connect.prepareStatement(l);
		        	pst1.executeUpdate();
		        	rs=pst.executeQuery();
		        	
		        	
		        	String query="select count from portal.tags where tname="+"'"+tag1+"'";
		        	PreparedStatement pst2 = connect.prepareStatement(query);
		        	ResultSet rs1 = pst2.executeQuery();
		        	rs1.next();
		        	int cnt = rs1.getInt(1);
		        	JOptionPane.showMessageDialog(null, "Total number of pics in the selected tag =" + cnt);
		        	show_image3 ig=new show_image3(rs);
		        	ig.setVisible(true);
		        	
		        	pst2.close();
		        }
		        catch(Exception e1)
		        {
		        	
		        }
		        
		      }
		    });
	}
}
