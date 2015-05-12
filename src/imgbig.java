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
import java.sql.SQLException;

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
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;


public class imgbig extends JFrame {

	private JPanel contentPane,panel;
	File targetFile,f1;
	BufferedImage targetImg;
	static String imgpath;
	private static final int basesize=300;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					imgbig frame = new imgbig(null);
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
	public imgbig(String str) {
		connect=DBConnect.dbConn();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(21, 11, 567, 305);
		contentPane.add(panel);
		
		JButton btnAddToFavourite = new JButton("Add to Favourite");
		btnAddToFavourite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try 
				{
					String q="select imgid from portal.photo where img = ?";
					PreparedStatement pst=connect.prepareStatement(q);
					pst.setString(1, imgpath);
					ResultSet rs=pst.executeQuery();
					rs.next();
					int id=rs.getInt(1);
					System.out.print(id);
					
					
					String q1="insert into portal.fav (username, imgid, img) values (?, ?, ?) ";
					PreparedStatement pst1=connect.prepareStatement(q1);
					pst1.setString(1, Login.usern);
					pst1.setInt(2, id);
					System.out.print(imgpath);
					pst1.setString(3, imgpath);
					pst1.execute();
					
					//JOptionPane.showMessageDialog(null, "Successfully Registered");
					//Myaccount my=new Myaccount();
					
					//my.setVisible(true);
					pst.close();
				
				}
				
				catch(Exception f)
				{
					f.printStackTrace();
				}
				//System.out.print(imgpath);
			}
		});
		btnAddToFavourite.setBounds(183, 353, 140, 23);
		contentPane.add(btnAddToFavourite);
		
		JButton btnDownload = new JButton("Download");
		btnDownload.setBounds(345, 353, 101, 23);
		contentPane.add(btnDownload);
		
		JToggleButton tglbtnLike = new JToggleButton("Like");
		tglbtnLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try 
				{
					
					String q="select likes from portal.photo where img = ?";
					PreparedStatement pst=connect.prepareStatement(q);
					pst.setString(1, imgpath);
					ResultSet rs=pst.executeQuery();
					rs.next();
					int likes=rs.getInt(1);
					likes=likes+1;
					System.out.print(likes);
					
					
					//String q="select * from portal.photo where tag="+"'"+tag1+"'";
					String q1= "update portal.photo set likes = ? where img = ?";
					PreparedStatement pst1=connect.prepareStatement(q1);
					pst1.setInt(1, likes);
					pst1.setString(2, imgpath);
					pst1.executeUpdate();
					//JOptionPane.showMessageDialog(null, "Successfully Registered");
					//Myaccount my=new Myaccount();
		
					//my.setVisible(true);
					pst.close();
					pst1.close();
				}
				
				catch(Exception f)
				{
					f.printStackTrace();
				}
			}
		});
		tglbtnLike.setBounds(38, 353, 121, 23);
		contentPane.add(tglbtnLike);
		
		JButton btnAddToAlbum = new JButton("Add to album");
		btnAddToAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try 
				{
					String query="Select max(albumid) from portal.album";
					PreparedStatement pst=connect.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					rs.next();
					int x= rs.getInt(1);
					System.out.print(x);
					
					String q1= "update portal.photo set albumid = ? where img = ?";
					PreparedStatement pst1=connect.prepareStatement(q1);
					pst1.setInt(1, x);
					pst1.setString(2, imgpath);
					pst1.executeUpdate();
					
				} 
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnAddToAlbum.setBounds(477, 353, 111, 23);
		contentPane.add(btnAddToAlbum);
		f1=new File(str);
		setTarget(f1);
		
		
		
	}
public  BufferedImage rescale(BufferedImage originalImage)
{
    BufferedImage resizedImage = new BufferedImage(basesize,basesize, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = resizedImage.createGraphics();
    g.drawImage(originalImage, 0, 0, basesize,basesize, null);
    g.dispose();
    System.out.println("golubbnf");
    return resizedImage;
}
 public void setTarget(File reference)
    {
        try {
            targetFile = reference;
            System.out.println(targetFile);
            imgpath=targetFile.getPath();
            targetImg = rescale(ImageIO.read(reference));
        } catch (IOException ex) {
        }
        panel.add(new JLabel(new ImageIcon(targetImg)));
        setVisible(true);
    }
}
