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
    private JTextField textFieldCpf;
    private JTextField textFieldSenha;
    private JTextField textFieldValor;
    private JTextField textFieldContaDestino;
    private JTextField textFieldIdConta;
    private JButton buttonCreditar;
    private JButton buttonDebitar;
    private JButton buttonTransferir;
    private JButton buttonOk;
    private JLabel labelMensagem;
    private Repositorio repositorio;

    public TelaCaixa() {
        this.repositorio = new Repositorio(); 
        this.repositorio.carregarObjetos();
        initialize();
        frame.setVisible(true);
    }

    public void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        label = new JLabel("BANKO");
        label.setForeground(new Color(153, 50, 204));
        label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 13));
        label.setBounds(52, 10, 85, 20);
        frame.getContentPane().add(label);

        JLabel labelCpf = new JLabel("Digite Seu CPF");
        labelCpf.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelCpf.setBounds(30, 72, 96, 13);
        frame.getContentPane().add(labelCpf);

        textFieldCpf = new JTextField();
        textFieldCpf.setBounds(30, 95, 120, 19);
        frame.getContentPane().add(textFieldCpf);

        JLabel labelSenha = new JLabel("Digite Sua Senha");
        labelSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelSenha.setBounds(30, 128, 120, 13);
        frame.getContentPane().add(labelSenha);

        textFieldSenha = new JTextField();
        textFieldSenha.setBounds(30, 151, 120, 19);
        frame.getContentPane().add(textFieldSenha);

        buttonOk = new JButton("OK");
        buttonOk.setFont(new Font("Tahoma", Font.BOLD, 13));
        buttonOk.setBounds(170, 150, 75, 21);
        frame.getContentPane().add(buttonOk);

        buttonCreditar = new JButton("CREDITAR");
        buttonCreditar.setEnabled(false);
        buttonCreditar.setFont(new Font("Tahoma", Font.BOLD, 10));
        buttonCreditar.setBounds(320, 80, 100, 21);
        frame.getContentPane().add(buttonCreditar);

        buttonDebitar = new JButton("DEBITAR");
        buttonDebitar.setEnabled(false);
        buttonDebitar.setFont(new Font("Tahoma", Font.BOLD, 10));
        buttonDebitar.setBounds(320, 120, 100, 21);
        frame.getContentPane().add(buttonDebitar);

        buttonTransferir = new JButton("TRANSFERIR");
        buttonTransferir.setEnabled(false);
        buttonTransferir.setFont(new Font("Tahoma", Font.BOLD, 10));
        buttonTransferir.setBounds(320, 160, 100, 21);
        frame.getContentPane().add(buttonTransferir);

        JLabel labelValor = new JLabel("Valor:");
        labelValor.setBounds(30, 200, 50, 20);
        frame.getContentPane().add(labelValor);

        textFieldValor = new JTextField();
        textFieldValor.setBounds(80, 200, 120, 20);
        frame.getContentPane().add(textFieldValor);

        JLabel labelContaDestino = new JLabel("Conta Destino:");
        labelContaDestino.setBounds(30, 230, 100, 20);
        frame.getContentPane().add(labelContaDestino);

        textFieldContaDestino = new JTextField();
        textFieldContaDestino.setBounds(130, 230, 120, 20);
        frame.getContentPane().add(textFieldContaDestino);

        JLabel labelIdConta = new JLabel("ID da Conta:");
        labelIdConta.setBounds(30, 260, 100, 20);
        frame.getContentPane().add(labelIdConta);

        textFieldIdConta = new JTextField();
        textFieldIdConta.setBounds(130, 260, 120, 20);
        frame.getContentPane().add(textFieldIdConta);

        // Label para mostrar a mensagem das operações
        labelMensagem = new JLabel("");
        labelMensagem.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelMensagem.setBounds(30, 300, 400, 50);
        frame.getContentPane().add(labelMensagem);

        buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validarCpfSenha();
            }
        });

        buttonCreditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                creditar();
            }
        });

        buttonDebitar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                debitar();
            }
        });

        buttonTransferir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                transferir();
            }
        });
    }

    private void validarCpfSenha() {
        try {
            String cpf = textFieldCpf.getText();
            String senha = textFieldSenha.getText();
            if (repositorio.localizarCorrentista(cpf) != null) {
                buttonCreditar.setEnabled(true);
                buttonDebitar.setEnabled(true);
                buttonTransferir.setEnabled(true);
                labelMensagem.setText("Autenticação realizada com sucesso!");
                labelMensagem.setForeground(Color.GREEN);
            } else {
                labelMensagem.setText("CPF ou senha inválidos!");
                labelMensagem.setForeground(Color.RED);
            }
        } catch (Exception e) {
            labelMensagem.setText("Erro: " + e.getMessage());
            labelMensagem.setForeground(Color.RED);
        }
    }

    private void creditar() {
        try {
            String cpf = textFieldCpf.getText();
            String senha = textFieldSenha.getText();
            double valor = Double.parseDouble(textFieldValor.getText());
            int idConta = Integer.parseInt(textFieldIdConta.getText());
            Fachada.creditarValor(idConta, cpf, senha, valor);
            labelMensagem.setText("Crédito de R$" + valor + " realizado com sucesso na conta ID " + idConta);
            labelMensagem.setForeground(Color.GREEN);
        } catch (Exception e) {
            labelMensagem.setText("Erro: " + e.getMessage());
            labelMensagem.setForeground(Color.RED);
        }
    }

    private void debitar() {
        try {
            String cpf = textFieldCpf.getText();
            String senha = textFieldSenha.getText();
            double valor = Double.parseDouble(textFieldValor.getText());
            int idConta = Integer.parseInt(textFieldIdConta.getText());
            Fachada.debitarValor(idConta, cpf, senha, valor);
            labelMensagem.setText("Débito de R$" + valor + " realizado com sucesso na conta ID " + idConta);
            labelMensagem.setForeground(Color.GREEN);
        } catch (Exception e) {
            labelMensagem.setText("Erro: " + e.getMessage());
            labelMensagem.setForeground(Color.RED);
        }
    }

    private void transferir() {
        try {
            String cpf = textFieldCpf.getText();
            String senha = textFieldSenha.getText();
            double valor = Double.parseDouble(textFieldValor.getText());
            int idContaOrigem = Integer.parseInt(textFieldIdConta.getText());
            int idContaDestino = Integer.parseInt(textFieldContaDestino.getText());
            Fachada.transferirValor(idContaOrigem, cpf, senha, valor, idContaDestino);
            labelMensagem.setText("Transferência de R$" + valor + " realizada com sucesso da conta ID " + idContaOrigem + " para conta ID " + idContaDestino);
            labelMensagem.setForeground(Color.GREEN);
        } catch (Exception e) {
            labelMensagem.setText("Erro: " + e.getMessage());
            labelMensagem.setForeground(Color.RED);
        }
    }
}
