package appjavaswing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TelaPrincipal {

	private JFrame frame;
	private JLabel label;
	private JLabel label_1;
	private JButton button;
	private JButton button_1;
	private JButton button_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label = new JLabel("Olá, Seja Bem Vindo!");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(74, 57, 281, 33);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("BANKO");
		label_1.setBackground(Color.GRAY);
		label_1.setForeground(new Color(153, 50, 204));
		label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 13));
		label_1.setBounds(10, 10, 85, 20);
		frame.getContentPane().add(label_1);
		
		button = new JButton("CAIXA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCaixa tc = new TelaCaixa();			
			}
		});
		button.setBounds(153, 100, 118, 21);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("CONTA");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConta tconta = new TelaConta();
			}
		});
		button_1.setBounds(153, 131, 118, 21);
		frame.getContentPane().add(button_1);
		
		button_2 = new JButton("CORRENTISTA");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCorrentista tcorrentista = new TelaCorrentista();
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 8));
		button_2.setBounds(153, 162, 118, 21);
		frame.getContentPane().add(button_2);
	}
}
