package repositorio;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import modelo.Conta;
import modelo.ContaEspecial;
import modelo.Correntista;

public class Repositorio {
    private List<Correntista> correntistas = new ArrayList<>();
    private List<Conta> contas = new ArrayList<>();

    public void adicionarCorrentista(Correntista correntista) {
        correntistas.add(correntista);
        Collections.sort(correntistas);
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public List<Correntista> getCorrentistas() {
        return correntistas;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public Correntista localizarCorrentista(String cpf) {
        for (Correntista c : correntistas) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    public Conta localizarConta(int id) {
        for (Conta c : contas) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public void removerConta(int id) throws Exception {
        Conta conta = localizarConta(id);

        if (conta == null) {
            throw new Exception("Conta não encontrada com o ID: " + id);
        }
        contas.remove(conta);
    }

    public int gerarIdConta() {
        if (contas.isEmpty())
            return 1;
        else
            return this.getContas().size() + 1;
    }

    public void carregarObjetos() {
        try {
            File f1 = new File(new File(".\\contas.csv").getCanonicalPath());
            File f2 = new File(new File(".\\correntistas.csv").getCanonicalPath());
            if (!f1.exists() || !f2.exists()) {
                FileWriter arquivo1 = new FileWriter(f1);
                arquivo1.close();
                FileWriter arquivo2 = new FileWriter(f2);
                arquivo2.close();
                return;
            }
        } catch (Exception ex) {
            throw new RuntimeException("Criação dos arquivos vazios: " + ex.getMessage());
        }

        // Carregar Contas
        try {
            String linha;
            String[] partes;
            Conta co;
            File f = new File(new File(".\\contas.csv").getCanonicalPath());
            Scanner arquivo1 = new Scanner(f);

            while (arquivo1.hasNextLine()) {
                linha = arquivo1.nextLine().trim();
                if (linha.isEmpty()) {
                    continue; // Pula linhas vazias
                }
                partes = linha.split(";");
                if (partes.length < 4) {
                    continue; // Verifica se há pelo menos 4 partes
                }

                String tipo = partes[0];
                String id = partes[1];
                String data = partes[2];
                String saldo = partes[3];

                if (tipo.equals("CONTA_ESPECIAL") && partes.length >= 5) {
                    String limite = partes[4];
                    co = new ContaEspecial(Integer.parseInt(id), data, Double.parseDouble(saldo), Double.parseDouble(limite));
                    this.adicionarConta(co);
                } else {
                    co = new Conta(Integer.parseInt(id), data, Double.parseDouble(saldo));
                    this.adicionarConta(co);
                }
            }
            arquivo1.close();
        } catch (Exception ex) {
            throw new RuntimeException("Erro na leitura do arquivo de contas: " + ex.getMessage());
        }

        // Carregar Correntistas
        try {
            String linha;
            String[] partes;
            Correntista c;
            File f = new File(new File(".\\correntistas.csv").getCanonicalPath());
            Scanner arquivo2 = new Scanner(f);

            while (arquivo2.hasNextLine()) {
                linha = arquivo2.nextLine().trim();
                if (linha.isEmpty()) {
                    continue; // Pula linhas vazias
                }
                partes = linha.split(";");
                if (partes.length < 3) {
                    continue; // Verifica se há pelo menos 3 partes
                }

                String cpf = partes[0];
                String nome = partes[1];
                String senha = partes[2];
                String ids = partes.length >= 4 ? partes[3] : ""; // Verifica se ids existem

                c = new Correntista(cpf, nome, senha);
                this.adicionarCorrentista(c);

                if (!ids.isEmpty()) {
                    for (String idconta : ids.split(",")) {
                        Conta co = this.localizarConta(Integer.parseInt(idconta));
                        if (co != null) {
                            co.adicionar(c);
                            c.adicionar(co);
                        }
                    }
                }
            }
            arquivo2.close();
        } catch (Exception ex) {
            throw new RuntimeException("Erro na leitura do arquivo de correntistas: " + ex.getMessage());
        }
    }

    public void salvarObjetos() {
        try {
            File f = new File(new File(".\\contas.csv").getCanonicalPath());
            FileWriter arquivo1 = new FileWriter(f);

            for (Conta c : contas) {
                if (c instanceof ContaEspecial e) {
                    arquivo1.write("CONTA_ESPECIAL;" + e.getId() + ";" + e.getData() + ";" + e.getSaldo() + ";" + e.getLimite() + "\n");
                } else {
                    arquivo1.write("CONTA_SIMPLES;" + c.getId() + ";" + c.getData() + ";" + c.getSaldo() + "\n");
                }
            }
            arquivo1.close();
        } catch (Exception e) {
            throw new RuntimeException("Problema na criação do arquivo de contas: " + e.getMessage());
        }

        try {
            File f = new File(new File(".\\correntistas.csv").getCanonicalPath());
            FileWriter arquivo2 = new FileWriter(f);
            ArrayList<String> lista;
            String listaId;

            for (Correntista c : correntistas) {
                lista = new ArrayList<>();
                for (Conta x : c.getContas()) {
                    lista.add(x.getId() + "");
                }
                listaId = String.join(",", lista);

                arquivo2.write(c.getCpf() + ";" + c.getNome() + ";" + c.getSenha() + ";" + listaId + "\n");
            }
            arquivo2.close();
        } catch (Exception e) {
            throw new RuntimeException("Problema na criação do arquivo de correntistas: " + e.getMessage());
        }
    }
}
