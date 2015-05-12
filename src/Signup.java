import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class Signup extends JFrame {

	private JPanel contentPane;
	private JTextField fn;
	private JTextField ln;
	private JTextField un;
	private JTextField ps;
	private JTextField age;
	private JTextField email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
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
	public Signup() {
		connect=DBConnect.dbConn();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(62, 14, 78, 14);
		contentPane.add(lblFirstName);
		
		fn = new JTextField();
		fn.setBounds(239, 11, 86, 20);
		contentPane.add(fn);
		fn.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(62, 45, 65, 14);
		contentPane.add(lblLastName);
		
		ln = new JTextField();
		ln.setBounds(239, 42, 86, 20);
		contentPane.add(ln);
		ln.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(62, 76, 65, 14);
		contentPane.add(lblUsername);
		
		un = new JTextField();
		un.setBounds(239, 73, 86, 20);
		contentPane.add(un);
		un.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(62, 107, 46, 14);
		contentPane.add(lblPassword);
		
		ps = new JTextField();
		ps.setBounds(239, 104, 86, 20);
		contentPane.add(ps);
		ps.setColumns(10);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(62, 139, 46, 14);
		contentPane.add(lblAge);
		
		age = new JTextField();
		age.setBounds(239, 136, 86, 20);
		contentPane.add(age);
		age.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Email ID");
		lblNewLabel.setBounds(62, 170, 46, 14);
		contentPane.add(lblNewLabel);
		
		email = new JTextField();
		email.setBounds(239, 167, 86, 20);
		contentPane.add(email);
		email.setColumns(10);
		
		JButton btnNewButton = new JButton("Signup");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					
					String q="insert into portal.user (fname,lname,username,password,age,emailid) values (?, ?, ?, ?, ?, ?) ";
					PreparedStatement pst=connect.prepareStatement(q);
					pst.setString(1, fn.getText());
					pst.setString(2, ln.getText());
					pst.setString(3, un.getText());
					Login.usern = un.getText();
					pst.setString(4, ps.getText());
					pst.setString(5, age.getText());
					pst.setString(6, email.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null, "Successfully Registered");
					Myaccount my=new Myaccount();
					
					my.setVisible(true);
					pst.close();
				
				}
				
				catch(Exception f)
				{
					JOptionPane.showMessageDialog(null, "Username already taken !");
					f.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(144, 227, 89, 23);
		contentPane.add(btnNewButton);
	}

}
