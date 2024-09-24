package appjavaswing;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import regras_negocio.Fachada;
import modelo.Correntista;
import java.util.List;

public class TelaCorrentista {

    private JFrame frame;
    private JTextArea textArea;
    private JTextField cpfField, nomeField, senhaField;

    public TelaCorrentista() {
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame("Correntistas");
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel label = new JLabel("Correntistas:");
        label.setBounds(10, 10, 100, 20);
        frame.getContentPane().add(label);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBounds(10, 40, 360, 100);
        frame.getContentPane().add(textArea);

        JButton listButton = new JButton("Listar Correntistas");
        listButton.setBounds(10, 150, 160, 25);
        frame.getContentPane().add(listButton);

        listButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarCorrentistas();
            }
        });

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setBounds(10, 180, 50, 20);
        frame.getContentPane().add(cpfLabel);

        cpfField = new JTextField();
        cpfField.setBounds(60, 180, 100, 20);
        frame.getContentPane().add(cpfField);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(10, 210, 50, 20);
        frame.getContentPane().add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setBounds(60, 210, 100, 20);
        frame.getContentPane().add(nomeField);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(170, 180, 50, 20);
        frame.getContentPane().add(senhaLabel);

        senhaField = new JTextField();
        senhaField.setBounds(220, 180, 100, 20);
        frame.getContentPane().add(senhaField);

        JButton criarButton = new JButton("Criar Correntista");
        criarButton.setBounds(170, 210, 160, 25);
        frame.getContentPane().add(criarButton);

        criarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criarCorrentista();
            }
        });
    }

    private void listarCorrentistas() {
        List<Correntista> correntistas = Fachada.listarCorrentistas();
        textArea.setText("");
        for (Correntista c : correntistas) {
            textArea.append(c.getCpf() + " - " + c.getNome() + "\n");
        }
    }

    private void criarCorrentista() {
        try {
            String cpf = cpfField.getText();
            String nome = nomeField.getText();
            String senha = senhaField.getText();
            Fachada.criarCorrentista(cpf, nome, senha);
            JOptionPane.showMessageDialog(frame, "Correntista criado com sucesso!");
            listarCorrentistas();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
