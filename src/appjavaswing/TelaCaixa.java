package appjavaswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import regras_negocio.Fachada;
import repositorio.Repositorio;

public class TelaCaixa {

    private JFrame frame;
    private JLabel label;
    private JButton buttonCreditar;
    private JButton buttonDebitar;
    private JButton buttonTransferir;
    private JLabel labelMensagem;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_cpf;
	private JLabel label_senha;
	private JLabel label_idconta;
	private JLabel label_valor;
	private JLabel label_desntino;
	private JTextField textFieldcpf;
	private JTextField textFieldsenha;
	private JTextField textFieldidconta;
	private JTextField textFieldvalor;
	private JTextField textFieldcontadestino;
	private JButton button;

    public TelaCaixa() {
        initialize();
        frame.setVisible(true);
    }

    public void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        label = new JLabel("BANKO");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(new Color(153, 50, 204));
        label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 13));
        label.setBounds(215, 10, 71, 20);
        frame.getContentPane().add(label);

        buttonCreditar = new JButton("CREDITAR");
        buttonCreditar.setFont(new Font("Tahoma", Font.BOLD, 10));
        buttonCreditar.setBounds(59, 78, 100, 21);
        frame.getContentPane().add(buttonCreditar);

        buttonDebitar = new JButton("DEBITAR");
        buttonDebitar.setFont(new Font("Tahoma", Font.BOLD, 10));
        buttonDebitar.setBounds(184, 78, 100, 21);
        frame.getContentPane().add(buttonDebitar);

        buttonTransferir = new JButton("TRANSFERIR");
        buttonTransferir.setFont(new Font("Tahoma", Font.BOLD, 10));
        buttonTransferir.setBounds(330, 78, 100, 21);
        frame.getContentPane().add(buttonTransferir);

        // Label para mostrar a mensagem das operações
        labelMensagem = new JLabel("");
        labelMensagem.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelMensagem.setBounds(30, 300, 400, 50);
        frame.getContentPane().add(labelMensagem);
        
        label_1 = new JLabel("CAIXA");
        label_1.setForeground(new Color(255, 0, 0));
        label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
        label_1.setBounds(184, 12, 45, 19);
        frame.getContentPane().add(label_1);
        
        label_2 = new JLabel("Selecione uma operação.");
        label_2.setHorizontalAlignment(SwingConstants.CENTER);
        label_2.setFont(new Font("Verdana", Font.BOLD, 10));
        label_2.setBounds(138, 40, 197, 13);
        frame.getContentPane().add(label_2);
        
      /** label_cpf = new JLabel("CPF");
        label_cpf.setBounds(10, 157, 45, 13);
        frame.getContentPane().add(label_cpf);
        
        label_senha = new JLabel("Senha");
        label_senha.setBounds(10, 187, 45, 13);
        frame.getContentPane().add(label_senha);
        
        label_idconta = new JLabel("ID da Conta");
        label_idconta.setBounds(10, 218, 110, 13);
        frame.getContentPane().add(label_idconta);
        
        label_valor = new JLabel("Valor");
        label_valor.setBounds(10, 247, 45, 13);
        frame.getContentPane().add(label_valor);
        
        label_desntino = new JLabel("ID Conta-Destino");
        label_desntino.setBounds(10, 277, 121, 13);
        frame.getContentPane().add(label_desntino);
        
        textFieldcpf = new JTextField();
        textFieldcpf.setBounds(165, 154, 121, 19);
        frame.getContentPane().add(textFieldcpf);
        textFieldcpf.setColumns(10);
        
        textFieldsenha = new JTextField();
        textFieldsenha.setBounds(165, 184, 121, 19);
        frame.getContentPane().add(textFieldsenha);
        textFieldsenha.setColumns(10);
        
        textFieldidconta = new JTextField();
        textFieldidconta.setBounds(165, 215, 121, 19);
        frame.getContentPane().add(textFieldidconta);
        textFieldidconta.setColumns(10);
        
        textFieldvalor = new JTextField();
        textFieldvalor.setBounds(165, 244, 121, 19);
        frame.getContentPane().add(textFieldvalor);
        textFieldvalor.setColumns(10);
        
        textFieldcontadestino = new JTextField();
        textFieldcontadestino.setBounds(165, 274, 121, 19);
        frame.getContentPane().add(textFieldcontadestino);
        textFieldcontadestino.setColumns(10);
        
        button = new JButton("OK");
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        button.setFont(new Font("Verdana", Font.BOLD, 12));
        button.setBounds(350, 214, 85, 21);
        frame.getContentPane().add(button);**/

        buttonCreditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	label_cpf = new JLabel("CPF");
                label_cpf.setBounds(10, 157, 45, 13);
                frame.getContentPane().add(label_cpf);
                
                label_senha = new JLabel("Senha");
                label_senha.setBounds(10, 187, 45, 13);
                frame.getContentPane().add(label_senha);
                
                label_idconta = new JLabel("ID da Conta");
                label_idconta.setBounds(10, 218, 63, 13);
                frame.getContentPane().add(label_idconta);
                
                label_valor = new JLabel("Valor");
                label_valor.setBounds(10, 247, 45, 13);
                frame.getContentPane().add(label_valor);
                 
                 textFieldcpf = new JTextField();
                 textFieldcpf.setBounds(165, 154, 121, 19);
                 frame.getContentPane().add(textFieldcpf);
                 textFieldcpf.setColumns(10);
                 
                 textFieldidconta = new JTextField();
                 textFieldidconta.setBounds(165, 215, 121, 19);
                 frame.getContentPane().add(textFieldidconta);
                 textFieldidconta.setColumns(10);
                 
                 textFieldsenha = new JTextField();
                 textFieldsenha.setBounds(165, 184, 121, 19);
                 frame.getContentPane().add(textFieldsenha);
                 textFieldsenha.setColumns(10);
                 
                 label_idconta = new JLabel("ID da Conta");
                 label_idconta.setBounds(10, 218, 110, 13);
                 frame.getContentPane().add(label_idconta);
                 
                 textFieldvalor = new JTextField();
                 textFieldvalor.setBounds(165, 244, 121, 19);
                 frame.getContentPane().add(textFieldvalor);
                 textFieldvalor.setColumns(10);
                 
                 button = new JButton("OK");
                 button.addActionListener(new ActionListener() {
                 	public void actionPerformed(ActionEvent e) {
                 		Integer id = Integer.parseInt( textFieldidconta.getText());
                 		String cpf = textFieldcpf.getText();
                 		String senha = textFieldsenha.getText();
                 		Double valor =Double.parseDouble(textFieldvalor.getText());
                 		try {
                 			if(id == null || cpf == null|| senha == null || valor == null) {
                				throw new Exception ("Preencha todos os campos");
                			}
							Fachada.creditarValor(id, cpf, senha, valor);
							labelMensagem.setText("Depósito de "+ valor+ " feito com sucesso.");
						} catch (Exception e1) {	
							labelMensagem.setText(e1.getMessage());
						}
                 	}
                 });
                 button.setFont(new Font("Verdana", Font.BOLD, 12));
                 button.setBounds(350, 214, 85, 21);
                 frame.getContentPane().add(button);
                 
                 frame.repaint();
                 frame.revalidate();
            }
        });

        buttonDebitar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	label_cpf = new JLabel("CPF");
                label_cpf.setBounds(10, 157, 45, 13);
                frame.getContentPane().add(label_cpf);
                
                label_senha = new JLabel("Senha");
                label_senha.setBounds(10, 187, 45, 13);
                frame.getContentPane().add(label_senha);
                
                label_idconta = new JLabel("ID da Conta");
                label_idconta.setBounds(10, 218, 110, 13);
                frame.getContentPane().add(label_idconta);
                
                label_valor = new JLabel("Valor");
                label_valor.setBounds(10, 247, 45, 13);
                frame.getContentPane().add(label_valor);
                
                textFieldcpf = new JTextField();
                textFieldcpf.setBounds(165, 154, 121, 19);
                frame.getContentPane().add(textFieldcpf);
                textFieldcpf.setColumns(10);
                
                textFieldsenha = new JTextField();
                textFieldsenha.setBounds(165, 184, 121, 19);
                frame.getContentPane().add(textFieldsenha);
                textFieldsenha.setColumns(10);
                
                textFieldidconta = new JTextField();
                textFieldidconta.setBounds(165, 215, 121, 19);
                frame.getContentPane().add(textFieldidconta);
                textFieldidconta.setColumns(10);
                
                textFieldvalor = new JTextField();
                textFieldvalor.setBounds(165, 244, 121, 19);
                frame.getContentPane().add(textFieldvalor);
                textFieldvalor.setColumns(10);
                
                button = new JButton("OK");
                button.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		Integer id = Integer.parseInt( textFieldidconta.getText());
                		String cpf = textFieldcpf.getText();
                		String senha = textFieldsenha.getText();
                		Double valor =Double.parseDouble(textFieldvalor.getText());
                		try {
                			if(id == null || cpf == null|| senha == null || valor == null) {
                				throw new Exception ("Preencha todos os campos");
                			}
							Fachada.debitarValor(id, cpf, senha, valor);
							labelMensagem.setText("Débito de "+ valor+ " feito com sucesso.");
						} catch (Exception e2) {	
							labelMensagem.setText(e2.getMessage());
						}
                	}
                });
                button.setFont(new Font("Verdana", Font.BOLD, 12));
                button.setBounds(350, 214, 85, 21);
                frame.getContentPane().add(button);
                
                frame.repaint();
                frame.revalidate();
            }
        });

        buttonTransferir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                textFieldcpf = new JTextField();
                textFieldcpf.setBounds(165, 154, 121, 19);
                frame.getContentPane().add(textFieldcpf);
                textFieldcpf.setColumns(10);
                
                textFieldsenha = new JTextField();
                textFieldsenha.setBounds(165, 184, 121, 19);
                frame.getContentPane().add(textFieldsenha);
                textFieldsenha.setColumns(10);
                
                label_idconta = new JLabel("ID da Conta");
                label_idconta.setBounds(10, 218, 110, 13);
                frame.getContentPane().add(label_idconta);
                
                textFieldvalor = new JTextField();
                textFieldvalor.setBounds(165, 244, 121, 19);
                frame.getContentPane().add(textFieldvalor);
                textFieldvalor.setColumns(10);
                
                label_desntino = new JLabel("ID Conta-Destino");
                label_desntino.setBounds(10, 277, 121, 13);
                frame.getContentPane().add(label_desntino);
                
                textFieldcontadestino = new JTextField();
                textFieldcontadestino.setBounds(165, 274, 121, 19);
                frame.getContentPane().add(textFieldcontadestino);
                textFieldcontadestino.setColumns(10);
                
                button = new JButton("OK");
                button.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		Integer id = Integer.parseInt( textFieldidconta.getText());
                		String cpf = textFieldcpf.getText();
                		String senha = textFieldsenha.getText();
                		Double valor =Double.parseDouble(textFieldvalor.getText());
                		Integer idDestino = Integer.parseInt(textFieldcontadestino.getText());
                		try {
                			if(id == null || cpf == null|| senha == null || valor == null || idDestino == null  ) {
                				throw new Exception ("Preencha todos os campos");
                			}
							Fachada.transferirValor(id, cpf, senha, valor, idDestino);
							labelMensagem.setText("Tranferencia de "+ valor+ " a conta de ID= "+idDestino+" feito com sucesso.");
						} catch (Exception e4) {	
							labelMensagem.setText(e4.getMessage());
						}
                	}
                });
                button.setFont(new Font("Verdana", Font.BOLD, 12));
                button.setBounds(350, 214, 85, 21);
                frame.getContentPane().add(button);
                
                frame.repaint();
                frame.revalidate();
                
            }
        });
    }



  



   
    }

