package testes;

import java.util.Scanner;
import regras_negocio.Fachada;
import modelo.Correntista;
import repositorio.Repositorio;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Limpar o repositório antes do teste
        Repositorio repositorio = Fachada.getRepositorio();
        repositorio.getCorrentistas().clear(); // Limpa qualquer dado existente

        // Dados para o teste
        String cpf = "12345678900";
        String nome = "João da Silva";
        String senha;

        // Solicitar senha até que tenha exatamente 4 dígitos
        while (true) {
            System.out.print("Digite sua senha (exatamente 4 dígitos): ");
            senha = scanner.nextLine().trim();
            if (senha.length() == 4) {
                break;
            } else {
                System.out.println("Sua senha deve conter exatamente quatro (4) dígitos.");
            }
        }

        // Testar a criação do correntista
        try {
            Fachada.criarCorrentista(cpf, nome, senha);
            System.out.println("Correntista criado com sucesso!");

            // Verificar se o correntista foi realmente adicionado
            Correntista correntista = repositorio.getCorrentistas().get(0);
            System.out.println("Dados do correntista criado:");
            System.out.println("CPF: " + correntista.getCpf());
            System.out.println("Nome: " + correntista.getNome());
            System.out.println("Senha: " + correntista.getSenha());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        scanner.close();
    }
}


