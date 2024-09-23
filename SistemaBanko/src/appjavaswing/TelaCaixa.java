package appjavaswing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TelaCaixa {

	private JFrame frame;
	private JLabel label;
	private JLabel label_1;
	private JTextField textField;
	private JLabel label_2;
	private JTextField textField_1;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JLabel label_3;
	private JButton button_3;

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
		
		label = new JLabel("BANKO");
		label.setForeground(new Color(153, 50, 204));
		label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 13));
		label.setBackground(Color.GRAY);
		label.setBounds(52, 10, 85, 20);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("Digite Sua Senha");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(30, 128, 118, 13);
		frame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setBounds(30, 151, 96, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		label_2 = new JLabel("Digite Seu CPF");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(30, 72, 96, 13);
		frame.getContentPane().add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(30, 95, 96, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		button = new JButton("CREDITAR");
		button.setEnabled(false);
		button.setFont(new Font("Tahoma", Font.BOLD, 10));
		button.setBounds(302, 78, 96, 21);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("DEBITAR");
		button_1.setEnabled(false);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		button_1.setBounds(302, 116, 96, 21);
		frame.getContentPane().add(button_1);
		
		button_2 = new JButton("TRANSFERIR");
		button_2.setEnabled(false);
		button_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		button_2.setBounds(302, 150, 96, 21);
		frame.getContentPane().add(button_2);
		
		label_3 = new JLabel("CAIXA ");
		label_3.setForeground(new Color(255, 0, 0));
		label_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		label_3.setBounds(10, 15, 45, 13);
		frame.getContentPane().add(label_3);
		
		button_3 = new JButton("OK");
		button_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		button_3.setBounds(40, 180, 75, 21);
		frame.getContentPane().add(button_3);
	}
}
