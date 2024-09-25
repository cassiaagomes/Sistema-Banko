package modelo;

import java.util.ArrayList;

public class Conta  {
	protected int id;
	protected String data;
	protected double saldo;
	private ArrayList<Correntista> correntista = new ArrayList<>();

	public Conta(int id, String data, double saldo) {
		this.id = id;
		this.data = data;
		this.saldo = saldo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public ArrayList<Correntista> getCorrentista() {
		return correntista;
	}

	public void creditar(double valor) {
		saldo += valor;
	}

	public void debitar(double valor) throws Exception {
		if (valor <= 0) {
			throw new Exception("O valor a debitar deve ser positivo.");
		}

		if (this instanceof ContaEspecial) {
			ContaEspecial especial = (ContaEspecial) this;
			double limiteDisponivel = saldo + especial.getLimite();
			if (valor > limiteDisponivel) {
				throw new Exception("Saldo insuficiente, excedendo o limite disponível.");
			}
		} else {
			if (saldo < valor) {
				throw new Exception("Saldo insuficiente.");
			}
		}

		saldo -= valor;
		System.out.println("Foi debitado: " + valor + " da conta.");
	}

	public void transferir(double valor, Conta destino) throws Exception {
		if (saldo >= valor) {
			this.debitar(valor);
			destino.creditar(valor);
		} else if (this instanceof ContaEspecial) {
			ContaEspecial especial = (ContaEspecial) this;
			double limiteDisponivel = saldo + especial.getLimite();
			if (valor > limiteDisponivel) {
				throw new Exception("Saldo insuficiente, excedendo o limite disponível.");
			} else {
				this.debitar(valor);
				destino.creditar(valor);
			}
		} else
			throw new Exception("Saldo Baixo.");
	}

	public void adicionar(Correntista c) {
		if (c != null && !correntista.contains(c)) {
			correntista.add(c);
		}
	}

}