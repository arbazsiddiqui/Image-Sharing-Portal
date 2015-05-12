import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.*;


public class show_image3 extends JFrame {

	private JPanel contentPane;
	File f1[]=new File[9];
	BufferedImage targetImg;
	private static final int basesize=200;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					show_image3 frame = new show_image3(null);
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
	public show_image3(ResultSet rs) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setSize(1000,900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3,3));
		
		int i=0;
		JButton b[]= new JButton[6];
		while(i<6)
		{
		b[i]=new JButton(String.valueOf(i));
		contentPane.add(b[i]);
		i++;
		}
		try{
			i=0;
			while(rs.next())
			{
			String str=rs.getString("img");
			f1[i]=new File(str);
			targetImg=Edit.getRescale(ImageIO.read(f1[i]));
			b[i].setIcon(new ImageIcon(targetImg));
			b[i].addActionListener(new Handler());
			i++;
			}
			}catch(Exception e)
			{
				
			}
		
	}
	class Handler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
				JButton ob=(JButton)(e.getSource());
				int t=Integer.parseInt(ob.getText());
				System.out.println(f1[t]);
				ImageIcon ib=(ImageIcon) ob.getIcon();
				imgbig d=new imgbig(f1[t].getPath());
				d.setVisible(true);
		}
	}

}
