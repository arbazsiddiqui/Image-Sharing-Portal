import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;


public class Edit extends JFrame {

	private JPanel contentPane;
	
	public static  BufferedImage getRescale(BufferedImage originalImage)
    {
        int w=originalImage.getWidth();
        int h=originalImage.getHeight();
        if(w>1000)
        {
            w=1000;
        }
        if(h>300)
        {
            h=300;
        }
        BufferedImage resizedImage = new BufferedImage(w,h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, w,h, null);
        g.dispose();
        System.out.println("golubbnf");
        return resizedImage;
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Edit frame = new Edit();
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
	public Edit() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 197);
		contentPane.add(panel);
		
		JButton btnRotateLeft = new JButton("Rotate AntiClockwise");
		btnRotateLeft.setBounds(10, 227, 89, 23);
		contentPane.add(btnRotateLeft);
		
		JButton btnRotateClockwise = new JButton("Rotate Clockwise");
		btnRotateClockwise.setBounds(109, 227, 89, 23);
		contentPane.add(btnRotateClockwise);
	}
}
