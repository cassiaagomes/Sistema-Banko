package regras_negocio;

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

		if (!cpf.matches("\\d{11}")) {
			throw new Exception("O CPF deve conter exatamente 11 dígitos.");
		}

		if (!senha.matches("\\d{4}")) {
			throw new Exception("A senha deve conter exatamente 4 dígitos numéricos.");
		}

		for (Correntista c : repositorio.getCorrentistas()) {
			if (c.getCpf().equals(cpf)) {
				throw new Exception("Já existe um correntista com o CPF: " + cpf);
			}
		}

		// Criar um novo correntista
		Correntista novoCorrentista = new Correntista(cpf, nome, senha);

		// Adicionar o novo correntista no repositório
		repositorio.adicionarCorrentista(novoCorrentista);

		// Salvar no repositório
		repositorio.salvarObjetos();
	}

	public static void criarConta(String cpf) throws Exception {
		cpf = cpf.trim();

		// Verificação se o cpf se encontra no repositorio
		Correntista c = repositorio.localizarCorrentista(cpf);

		if (c == null) {
			throw new Exception("O Correntista de cpf: " + cpf + "não foi encontrado");
		}

		if (c.getContas().isEmpty()) {

			int idConta = repositorio.gerarIdConta();
			String dataAtual = java.time.LocalDateTime.now().toString();
			Conta novaConta = new Conta(idConta, dataAtual, 0.0);
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
			String dataAtual = java.time.LocalDateTime.now().toString();
			Conta novaConta = new Conta(idConta, dataAtual, 0.0);
			c.getContas().add(novaConta);
			repositorio.adicionarConta(novaConta);
			repositorio.salvarObjetos();
		}
		System.out.println("Conta Criada com Sucesso. ");
	}

	public static void criarContaEspecial(String cpf, double limite) throws Exception {
		cpf = cpf.trim();

		// Verificação se o cpf se encontra no repositorio
		Correntista c = repositorio.localizarCorrentista(cpf);

		if (c == null) {
			throw new Exception("O Correntista de cpf: " + cpf + "não foi encontrado");
		}

		int idConta = repositorio.gerarIdConta();

		String dataAtual = java.time.LocalDateTime.now().toString();

		ContaEspecial contaEspecial = new ContaEspecial(idConta, dataAtual, 0.0, limite);

		c.getContas().add(contaEspecial);
		repositorio.adicionarConta(contaEspecial);
		repositorio.salvarObjetos();

	}

	public static void creditarValor(int id, String cpf, String senha, double valor) {
		try {
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
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		repositorio.salvarObjetos();
	}

	public static void debitarValor(int id, String cpf, String senha, double valor) throws Exception {
		Correntista correntista = repositorio.localizarCorrentista(cpf);
		if (correntista == null) {
			throw new Exception("Não foi encontrado um correntista de cpf: " + cpf);
		}

		if (!correntista.getSenha().equals(senha)) {
			throw new Exception("Senha incorreta.");
		}

		Conta conta = repositorio.localizarConta(id);
		if (conta == null) {
			throw new Exception("Conta não existe");
		}

		if (valor <= 0) {
			throw new Exception("Conta não encontrada");
		}
		boolean deu_certo =false;
		for (Conta ct : correntista.getContas()) {
			if (ct.getId() == conta.getId()) {
				conta.debitar(valor);
				repositorio.salvarObjetos();
				deu_certo = true;
				break;
			}	 
		}
		if (deu_certo == false) throw new Exception("Você não está relacionado a esta conta. Operação abortada.");

	}

	public static void inserirCorrentista(int id, String cpf) throws Exception {

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

	public static void removerCorrentista(int id, String cpf) throws Exception {

		Correntista correntista = repositorio.localizarCorrentista(cpf);

		if (correntista == null) {
			throw new Exception("Não foi encontrado um correntista de cpf: " + cpf);
		}

		Conta conta = repositorio.localizarConta(id);

		if (conta == null) {
			throw new Exception("Conta não existe");
		}
		boolean existe = false;
		for (Conta ct: correntista.getContas()) {
			if(ct.getId() == conta.getId()) {
				existe = true;
				break;
			}
			if(existe == false) throw new Exception ("O correntista não está relacionado a esta conta. Operação abortada.");
		}

		ArrayList<Correntista> correntistas = conta.getCorrentista();
		System.out.println(correntistas.size());
		for (int i = 0; i < correntistas.size(); i++) {

			if (correntistas.get(0).getCpf().equals(cpf)) {
				throw new Exception("Não é possível remover o titular!");
			}
			if (correntistas.get(i).getCpf().equals(cpf)) {
				correntistas.remove(i);

				ArrayList<Conta> contasCorrentistas = correntistas.get(i).getContas();

				for (int j = 0; j < contasCorrentistas.size(); j++) {

					if (contasCorrentistas.get(j).getId() == id) {
						contasCorrentistas.remove(j);
					}
				}
			}
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

	public static void transferirValor(int id1, String cpf, String senha, double valor, int id2) {
		try {
			Correntista correntista = repositorio.localizarCorrentista(cpf);
			if (correntista == null) {
				throw new Exception("O CPF informado não pertence a nehum correntista");
			}
			if (valor <= 0) {
				throw new Exception("Valor inválido");
			}
			if (!correntista.getSenha().equals(senha)) {
				throw new Exception("Senha incorreta");
			}
			Conta conta1 = repositorio.localizarConta(id1);
			if (conta1 == null) {
				throw new Exception ("Conta inexistente.");
			}
			Conta conta2 = repositorio.localizarConta(id2);
			if (conta2 == null) {
				throw new Exception ("Conta inexistente.");
			};
			boolean deu_certo =false;
			for (Conta ct : correntista.getContas()) {
				if (ct.getId() == conta1.getId()) {
					conta1.transferir(valor, conta2);
					deu_certo = true;
					break;
				}	 
			}
			if (deu_certo == false) throw new Exception("Você não está relacionado a esta conta. Operação abortada.");
		} catch (Exception f) {
			System.out.println(f.getMessage());
		}
	}

	public static Repositorio getRepositorio() {
		return repositorio;
	}

}
