package appjavaswing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TelaCaixa {

	private JFrame frame;
	private JLabel label;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCaixa window = new TelaCaixa();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	 * Create the application.
	 */
	public TelaCaixa() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label = new JLabel("New label");
		label.setBounds(80, 98, 45, 13);
		frame.getContentPane().add(label);
	}

}
