package appjavaswing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import regras_negocio.Fachada;
import modelo.Conta;
import modelo.ContaEspecial;
import java.util.List;

public class TelaConta {

    private JFrame frame;
    private JTextArea textArea;

    public TelaConta() {
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame("Contas");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel label = new JLabel("Contas:");
        label.setBounds(10, 10, 100, 20);
        frame.getContentPane().add(label);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBounds(10, 40, 400, 100);
        frame.getContentPane().add(textArea);

        JButton listButton = new JButton("Listar Contas");
        listButton.setBounds(10, 150, 125, 25);
        frame.getContentPane().add(listButton);

        listButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarContas();
            }
        });

        JButton criarButton = new JButton("Criar Conta");
        criarButton.setBounds(145, 150, 134, 25);
        frame.getContentPane().add(criarButton);

        criarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criarConta();
            }
        });

        JButton criarEspecialButton = new JButton("Criar Conta Especial");
        criarEspecialButton.setBounds(10, 190, 156, 25);
        frame.getContentPane().add(criarEspecialButton);

        criarEspecialButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criarContaEspecial();
            }
        });

        JButton removerButton = new JButton("Apagar Conta");
        removerButton.setBounds(289, 150, 125, 25);
        frame.getContentPane().add(removerButton);

        removerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removerConta();
            }
        });
    }

    private void listarContas() {
        List<Conta> contas = Fachada.listarContas();
        textArea.setText("");
        for (Conta c : contas) {
            if (c instanceof ContaEspecial) {
                ContaEspecial ce = (ContaEspecial) c;
                textArea.append("ID: " + ce.getId() + "- Data: "+ ce.getData()+" - Especial - Saldo: " + ce.getSaldo() + " - Limite: " + ce.getLimite() + "\n");
            } else {
                textArea.append("ID: " + c.getId() +  "- Data: "+ c.getData()+" - Simples - Saldo: " + c.getSaldo() + "\n");
            }
        }
    }

    private void criarConta() {
        String cpf = JOptionPane.showInputDialog("Digite o CPF do titular:");
        try {
            Fachada.criarConta(cpf);
            JOptionPane.showMessageDialog(frame, "Conta simples criada com sucesso!");
            listarContas();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void criarContaEspecial() {
        String cpf = JOptionPane.showInputDialog("Digite o CPF do titular:");
        String limiteStr = JOptionPane.showInputDialog("Digite o limite da conta especial:");
        try {
            double limite = Double.parseDouble(limiteStr);
            Fachada.criarContaEspecial(cpf, limite);
            JOptionPane.showMessageDialog(frame, "Conta especial criada com sucesso!");
            listarContas();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerConta() {
        String idStr = JOptionPane.showInputDialog("Digite o ID da conta a ser removida:");
        try {
            int id = Integer.parseInt(idStr);
            Fachada.apagarConta(id);
            JOptionPane.showMessageDialog(frame, "Conta removida com sucesso!");
            listarContas();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
