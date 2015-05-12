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



public class Upload extends JFrame {
	static int x;
	static int y;
	static int flag;
	static long sizeimg;
	static String imgpath;
	static String curtag;
	static String curuse;
	static String[] tags = {"Nature","Technology","Vacation", "Festival", "Abstract Art", "Food and Drink", "Concept and ideas", "Friends and family", "Personal art"};
	public JPanel panel, panel_1,panel_2;
	File targetFile;
	BufferedImage targetImg;
	private static final int basesize=175;
	private static final String basepath="C:/Users/siddiqui/Desktop";
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Upload frame = new Upload();
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
	Connection connect1=null;
	public Upload() {
		
		connect=DBConnect.dbConn();
		connect1=DBConnect.dbConn();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 899, 683);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel=new JPanel();
		panel_1=new JPanel();
		panel_2 = new JPanel();
		panel_2.setBounds(98, 36, 775, 597);
		panel_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
		contentPane.add(panel_2);
		
		
		
		JLabel lblQ = new JLabel("Welcome, "+ Login.usern);
		lblQ.setBounds(10, 11, 219, 14);
		contentPane.add(lblQ);
		
		final JComboBox comboBox = new JComboBox(tags);
		comboBox.setBounds(10, 171, 78, 20);
		contentPane.add(comboBox);

		
		JButton btnBrowseAndUpload = new JButton("Browse");
		btnBrowseAndUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				browseButtonActionPerformed(e);
				
			}

			 public BufferedImage rescale(BufferedImage originalImage)
			    {
			        x = originalImage.getWidth();
			        y = originalImage.getHeight();
			        sizeimg=x*y;
			        if(x>757)
			        	x=1*x/2;
			        if(y>597)
			        	y=1*y/2;
			        BufferedImage resizedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
			        Graphics2D g = resizedImage.createGraphics();
			        System.out.println(x);
			        System.out.print(y);
			        g.drawImage(originalImage, 0, 0, x, y, null);
			        g.dispose();
			        return resizedImage;
			    }
			 
			 public void setTarget(File reference)
			    {
			        try {
			            targetFile = reference;
			            imgpath = targetFile.getPath();
			            //System.out.print(imgpath);
			            targetImg = rescale(ImageIO.read(reference));
			        } catch (IOException ex) {
			            //Logger.getLogger(ImageDemo.class.getName()).log(Level.SEVERE, null, ex);
			        }

					panel_2.setLayout(new BorderLayout(0, 0));
			        panel_2.add(new JLabel(new ImageIcon(targetImg)));
			        setVisible(true);
			    }
			 
			    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {
			        JFileChooser fc = new JFileChooser(basepath);
			        //String h= fc.ge
			       // fc.setFileFilter(new JPEGImageFileFilter());
			        int res = fc.showOpenDialog(null);
			        // We have an image!
			        try {
			            if (res == JFileChooser.APPROVE_OPTION) {
			                File file = fc.getSelectedFile();
			                
			                //System.out.print(file);
			                setTarget(file);
			            } // Oops!
			            else {
			                JOptionPane.showMessageDialog(null,
			                        "You must select one image to be the reference.", "Aborting...",
			                        JOptionPane.WARNING_MESSAGE);
			            }
			        } 
			        catch (Exception iOException) 
			        {
			        }

			    }
			    
			    
		});
		btnBrowseAndUpload.setBounds(10, 110, 78, 23);
		contentPane.add(btnBrowseAndUpload);
		
		JButton btnNewButton = new JButton("Edit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ImEdit a = new ImEdit(imgpath);
				
				
			}
		});
		btnNewButton.setBounds(10, 137, 78, 23);
		contentPane.add(btnNewButton);
		
		
		
		
		
		
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try 
				{
					curtag = (String) comboBox.getSelectedItem();
					String q="insert into portal.photo (size, uploaddate, username ,img , tag) values (?, ?, ?, ?, ?) ";
					PreparedStatement pst=connect.prepareStatement(q);
					pst.setLong(1, sizeimg);
					pst.setDate(2, new java.sql.Date((new Date(System.currentTimeMillis())).getTime()));
					pst.setString(3, Login.usern);
					pst.setString(4, imgpath);
					pst.setString(5, curtag);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Image Uploaded");
					pst.close();
					
				}
				
				catch(Exception f)
				{
					f.printStackTrace();
				}

			}
			
			
		});
		
		btnSave.setBounds(10, 202, 78, 23);
		contentPane.add(btnSave);
		
	
		

	}
	
	 
	 
	 
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
