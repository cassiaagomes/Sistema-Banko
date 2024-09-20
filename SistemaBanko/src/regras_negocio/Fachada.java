package regras_negocio;

import java.util.List;

import modelo.Conta;
import modelo.ContaEspecial;
import modelo.Correntista;
import repositorio.Repositorio;

public class Fachada {
	private Fachada () {} 
	private static Repositorio repositorio = new Repositorio ();
	
	public static List<Correntista> listarCorrentistas(){
		return repositorio.getCorrentistas();	
	}
	
	public static List<Conta> listarContas(){
		return repositorio.getContas();	
	}
	
	public static void creditarValor(int id, String cpf, String senha, double valor) {
		try {
			Correntista correntista = repositorio.localizarCorrentista(cpf);
			if(correntista == null) {
				throw new Exception("Correntista inexistente.");
			}
			Conta conta = repositorio.localizarConta(id);
			if(conta == null) {
				throw new Exception("Conta inexistente.");
			}
			if(valor <= 0) {
				throw new Exception("Valor inválido.");
			}
			if(!correntista.getSenha().equals(senha)) {
				throw new Exception("Senha incorreta.");
			}
			else
				conta.creditar(valor);		
			} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	public static void criarCorrentista(String cpf, String nome, String senha) throws Exception {
		cpf = cpf.trim();
		nome = nome.trim();
		
		//localizar correntista no repositorio, usando o cpf
		for (Correntista c : repositorio.getCorrentistas()) {
			if(c.getCpf().equals(cpf)) {
				throw new Exception ("Já existe um correntista com o cpf: " + cpf);
			}
		}
		//Criar um novo correntista
		Correntista novoCorrentista = new Correntista(cpf, nome, senha);
		
		//Adicionar o novo correntista no repositório
		repositorio.adicionarCorrentista(novoCorrentista);
		
	}
	
	public static void criarConta (String cpf) throws Exception {
		cpf = cpf.trim();
		
		//Verificação se o cpf se encontra no repositorio
		Correntista c = repositorio.localizarCorrentista(cpf);
		
		if (c==null) {
			throw new Exception ("O Correntista de cpf: " + cpf + "não foi encontrado");
		}
		
		//Gerar ID para a conta
		int idConta = repositorio.gerarIdConta();
		
		//Data Atual
		String dataAtual = java.time.LocalDateTime.now().toString();
		
		//criar nova conta
		Conta novaConta = new Conta(idConta, dataAtual, 0.0);
		
		c.getContas().add(novaConta);
		
		repositorio.adicionarConta(novaConta);
		
		System.out.println("Conta Criada com Sucesso. ID da Conta: " + idConta );
	}
	
	public static void criarContaEspecial (String cpf, double limite) throws Exception {
		cpf = cpf.trim();
		
		//Verificação se o cpf se encontra no repositorio
		Correntista c = repositorio.localizarCorrentista(cpf);
		
		if (c==null) {
			throw new Exception ("O Correntista de cpf: " + cpf + "não foi encontrado");
		}
		
		int idConta = repositorio.gerarIdConta();
		
		String dataAtual = java.time.LocalDateTime.now().toString();
		
		ContaEspecial contaEspecial = new ContaEspecial (idConta , dataAtual, 0.0, limite);
		
		c.getContas().add(contaEspecial);
		
		repositorio.adicionarContaEspecial(contaEspecial);
		
	}
	
	public static Repositorio getRepositorio() {
        return repositorio;
    }

}





