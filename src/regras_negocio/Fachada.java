package regras_negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import modelo.Conta;
import modelo.ContaEspecial;
import modelo.Correntista;
import repositorio.Repositorio;

public class Fachada {
	private Fachada() {
	}

	private static Repositorio repositorio = new Repositorio();

	public static List<Correntista> listarCorrentistas() {
		return repositorio.getCorrentistas();
	}

	public static List<Conta> listarContas() {
		return repositorio.getContas();
	}

	public static void criarCorrentista(String cpf, String nome, String senha) throws Exception {
		cpf = cpf.trim();
		nome = nome.trim();
		senha = senha.trim();

		if (!senha.matches("\\d{4}")) {
			throw new Exception("A senha deve conter exatamente 4 dígitos numéricos.");
		}

		for (Correntista c : repositorio.getCorrentistas()) {
			if (c.getCpf().equals(cpf)) {
				throw new Exception("Já existe um correntista com o CPF: " + cpf);
			}
		}

		Correntista novoCorrentista = new Correntista(cpf, nome, senha);

		repositorio.adicionarCorrentista(novoCorrentista);

		repositorio.salvarObjetos();
	}

	public static void criarConta(String cpf) throws Exception {
		cpf = cpf.trim();

		Correntista c = repositorio.localizarCorrentista(cpf);

		if (c == null) {
			throw new Exception("O Correntista de cpf: " + cpf + "não foi encontrado");
		}

		if (c.getContas().isEmpty()) {

			int idConta = repositorio.gerarIdConta();
			DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd/MM/yy");
			LocalDate dataatual = LocalDate.now();
			String data = dataatual.format(f1);
			Conta novaConta = new Conta(idConta, data, 0.0);
			c.getContas().add(novaConta);
			novaConta.adicionar(c);

			repositorio.adicionarConta(novaConta);
			repositorio.salvarObjetos();

		} else {
			ArrayList<Conta> contas = c.getContas();
			for (int i = 0; i < contas.size(); i++) {
				if (contas.get(i).getCorrentista().get(0).getCpf().equals(cpf)) {
					throw new Exception("Este correntista já é titular de outra conta");
				}
			}
			int idConta = repositorio.gerarIdConta();
			DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd/MM/yy");
			LocalDate dataatual = LocalDate.now();
			String data = dataatual.format(f1);
		
			Conta novaConta = new Conta(idConta, data, 0.0);
			c.getContas().add(novaConta);
			repositorio.adicionarConta(novaConta);
			repositorio.salvarObjetos();
		}
		System.out.println("Conta Criada com Sucesso. ");
	}

	public static void criarContaEspecial(String cpf, double limite) throws Exception {
		cpf = cpf.trim();

		Correntista c = repositorio.localizarCorrentista(cpf);

		if (c == null) {
			throw new Exception("O Correntista de cpf: " + cpf + "não foi encontrado");
		}

		int idConta = repositorio.gerarIdConta();

		DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd/MM/yy");
		LocalDate dataatual = LocalDate.now();
		String data = dataatual.format(f1);

		ContaEspecial contaEspecial = new ContaEspecial(idConta, data, 0.0, limite);

		c.getContas().add(contaEspecial);
		repositorio.adicionarConta(contaEspecial);
		repositorio.salvarObjetos();

	}

	public static void creditarValor(int id, String cpf, String senha, double valor) throws Exception {
			Correntista correntista = repositorio.localizarCorrentista(cpf);
			if (correntista == null) {
				throw new Exception("Correntista inexistente.");
			}
			Conta conta = repositorio.localizarConta(id);
			if (conta == null) {
				throw new Exception("Conta inexistente.");
			}
			if (valor <= 0) {
				throw new Exception("Valor inválido.");
			}
			if (!correntista.getSenha().equals(senha)) {
				throw new Exception("Senha incorreta.");
			} else
				conta.creditar(valor);
			repositorio.salvarObjetos();
	}

	public static void debitarValor(int id, String cpf, String senha, double valor) throws Exception {
	    Correntista correntista = repositorio.localizarCorrentista(cpf);
	    if (correntista == null) {
	        throw new Exception("Não foi encontrado um correntista de CPF: " + cpf);
	    }

	    if (!correntista.getSenha().equals(senha)) {
	        throw new Exception("Senha incorreta.");
	    }

	    Conta conta = repositorio.localizarConta(id);
	    if (conta == null) {
	        throw new Exception("Conta não existe.");
	    }

	    if (valor <= 0) {
	        throw new Exception("Valor inválido.");
	    }

	    conta.debitar(valor);
	    repositorio.salvarObjetos(); 

	    System.out.println("Débito realizado com sucesso.");
	}

	public static void inserirCorrentistaConta(int id, String cpf) throws Exception {

		Correntista correntista = repositorio.localizarCorrentista(cpf);

		if (correntista == null) {
			throw new Exception("Não foi encontrado um correntista de cpf: " + cpf);
		}

		Conta conta = repositorio.localizarConta(id);

		if (conta == null) {
			throw new Exception("Conta não existe");
		}

		ArrayList<Correntista> correntistas = conta.getCorrentista();

		for (Correntista c : correntistas) {

			if (c.getCpf().equals(cpf)) {
				throw new Exception("Esse correntista já foi cadastrado nesta conta");
			}

		}

		conta.adicionar(correntista);
		correntista.adicionar(conta);
		repositorio.salvarObjetos();

	}

	public static void removerCorrentistaConta(int id, String cpf) throws Exception {
	    Correntista correntista = repositorio.localizarCorrentista(cpf);

	    if (correntista == null) {
	        throw new Exception("Não foi encontrado um correntista de cpf: " + cpf);
	    }

	    Conta conta = repositorio.localizarConta(id);

	    if (conta == null) {
	        throw new Exception("Conta não existe");
	    }

	    ArrayList<Correntista> correntistas = conta.getCorrentista();

	    if (!correntistas.isEmpty() && correntistas.get(0).getCpf().equals(cpf)) {
	        throw new Exception("Não é possível remover o titular!");
	    }

	    boolean remover = correntistas.removeIf(c -> c.getCpf().equals(cpf));

	    if (!remover) {
	        throw new Exception("Cotitular não encontrado na conta.");
	    }
	    for (Correntista c : correntistas) {
	        ArrayList<Conta> contasCorrentistas = c.getContas();
	        contasCorrentistas.removeIf(contaC -> contaC.getId() == id);
	    }

	    repositorio.salvarObjetos();
	}

	public static void apagarConta(int id) throws Exception {
		Conta conta = repositorio.localizarConta(id);

		if (conta == null) {
			throw new Exception("Conta não encontrada com o ID: " + id);
		}

		if (conta.getSaldo() != 0) {
			throw new Exception("A conta não pode ser apagada, pois o saldo não é zero.");
		}

		ArrayList<Correntista> correntistas = conta.getCorrentista();

		for (Correntista correntista : correntistas) {
			ArrayList<Conta> contasDoCorrentista = correntista.getContas();
			for (int i = 0; i < contasDoCorrentista.size(); i++) {
				if (contasDoCorrentista.get(i).getId() == id) {
					contasDoCorrentista.remove(i);
					break;
				}
			}
		}

		repositorio.removerConta(id);

		repositorio.salvarObjetos();

		System.out.println("Conta apagada com sucesso.");
	}

	public static void transferirValor(int id1, String cpf, String senha, double valor, int id2) throws Exception {

	    Correntista correntista = repositorio.localizarCorrentista(cpf);
	    if (correntista == null) {
	        throw new Exception("O CPF informado não pertence a nenhum correntista.");
	    }
	    
	    if (valor <= 0) {
	        throw new Exception("Valor inválido.");
	    }
	    
	    if (!correntista.getSenha().equals(senha)) {
	        throw new Exception("Senha incorreta.");
	    }
	    
	    Conta conta1 = repositorio.localizarConta(id1);
	    if (conta1 == null) {
	        throw new Exception("Conta inexistente.");
	    }
	    
	    Conta conta2 = repositorio.localizarConta(id2);
	    if (conta2 == null) {
	        throw new Exception("Conta inexistente.");
	    }

	    conta1.transferir(valor, conta2);
	    repositorio.salvarObjetos(); 

	    System.out.println("Transferência realizada com sucesso.");
	}

	
	public static Repositorio getRepositorio() {
		return repositorio;
	}

}
