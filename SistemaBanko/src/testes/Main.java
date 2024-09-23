package testes;

import java.util.Scanner;
import regras_negocio.Fachada;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        Fachada.getRepositorio().carregarObjetos();

        System.out.println("=== BANKO ===");
        
        while (continuar) {
            System.out.println("\nMenu de Opções:");
            System.out.println("1. Criar Correntista");
            System.out.println("2. Criar Conta");
            System.out.println("3. Criar Conta Especial");
            System.out.println("4. Listar Correntistas");
            System.out.println("5. Listar Contas");
            System.out.println("6. Creditar em Conta");
            System.out.println("7. Debitar de Conta");
            System.out.println("8. Remover Correntista");
            System.out.println("9. Apagar Conta");
            System.out.println("10. Inserir Correntista em Conta");
            System.out.println("11. Sair");

            System.out.print("\nEscolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        System.out.println("\n== Criar Correntista ==");
                        System.out.print("Digite o CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Digite o nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Digite a senha: ");
                        String senha = scanner.nextLine();

                        Fachada.criarCorrentista(cpf, nome, senha);
                        System.out.println("Correntista criado com sucesso!");
                        break;

                    case 2:
                        System.out.println("\n== Criar Conta ==");
                        System.out.print("Digite o CPF do correntista: ");
                        cpf = scanner.nextLine();
                        
                        Fachada.criarConta(cpf);
                        break;

                    case 3:
                        System.out.println("\n== Criar Conta Especial ==");
                        System.out.print("Digite o CPF do correntista: ");
                        cpf = scanner.nextLine();
                        System.out.print("Digite o limite especial: ");
                        double limite = scanner.nextDouble();
                        
                        Fachada.criarContaEspecial(cpf, limite);
                        System.out.println("Conta Especial criada com sucesso!");
                        break;

                    case 4:
                        System.out.println("\n== Listar Correntistas ==");
                        Fachada.listarCorrentistas().forEach(c -> {
                            System.out.println("CPF: " + c.getCpf() + ", Nome: " + c.getNome());
                        });
                        break;

                    case 5:
                        System.out.println("\n== Listar Contas ==");
                        Fachada.listarContas().forEach(c -> {
                            System.out.println("ID: " + c.getId() +" Data: "+ c.getData()+", Saldo: " + c.getSaldo());
                        });
                        break;

                    case 6:
                        System.out.println("\n== Creditar em Conta ==");
                        System.out.print("Digite o ID da conta: ");
                        int idContaCreditar = scanner.nextInt();
                        scanner.nextLine(); 
                        System.out.print("Digite o CPF: ");
                        cpf = scanner.nextLine();
                        System.out.print("Digite a senha: ");
                        senha = scanner.nextLine();
                        System.out.print("Digite o valor a creditar: ");
                        double valorCreditar = scanner.nextDouble();
                        
                        Fachada.creditarValor(idContaCreditar, cpf, senha, valorCreditar);
                        break;

                    case 7:
                        System.out.println("\n== Debitar de Conta ==");
                        System.out.print("Digite o ID da conta: ");
                        int idContaDebitar = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite o CPF: ");
                        cpf = scanner.nextLine();
                        System.out.print("Digite a senha: ");
                        senha = scanner.nextLine();
                        System.out.print("Digite o valor a debitar: ");
                        double valorDebitar = scanner.nextDouble();

                        Fachada.debitarValor(idContaDebitar, cpf, senha, valorDebitar);
                        System.out.println("Valor debitado com sucesso!");
                        break;

                    case 8:
                        System.out.println("\n== Remover Correntista ==");
                        System.out.print("Digite o ID da conta: ");
                        int idContaRemover = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite o CPF: ");
                        cpf = scanner.nextLine();

                        try {
                            Fachada.removerCorrentista(idContaRemover, cpf);
                            System.out.println("Correntista removido com sucesso!");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 9:
                        System.out.println("\n== Apagar Conta ==");
                        System.out.print("Digite o ID da conta: ");
                        int idContaApagar = scanner.nextInt();
                        scanner.nextLine();
                        
                        try {
                            Fachada.apagarConta(idContaApagar);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 10:
                        System.out.println("\n== Inserir Correntista em Conta ==");
                        System.out.print("Digite o ID da conta: ");
                        int idContaInserir = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite o CPF do correntista a ser inserido: ");
                        String cpfInserir = scanner.nextLine();
                        
                        try {
                            Fachada.inserirCorrentista(idContaInserir, cpfInserir);
                            System.out.println("Correntista inserido com sucesso!");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 11:
                        System.out.println("Saindo do sistema...");
                        continuar = false;
                        break;
                    case 12:
                        System.out.println("Digite o id da sua conta");
                        int id1 = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Digite seu cpf");
                        String cpf1 = scanner.nextLine();
                        System.out.println("Digite sua senha");
                        String senha1 = scanner.nextLine();
                        System.out.println("informe o valor");
                        double valor = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println("informe a conta de destino");
                        int id2 = scanner.nextInt();
                        Fachada.transferirValor(id1, cpf1, senha1, valor, id2);
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        Fachada.getRepositorio().salvarObjetos();
        scanner.close();
    }
}
