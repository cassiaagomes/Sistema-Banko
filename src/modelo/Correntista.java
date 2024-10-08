package modelo;

import java.util.ArrayList;

public class Correntista implements Comparable<Correntista> {
	protected String cpf;
	protected String nome;
	protected String senha;
	private ArrayList<Conta> contas = new ArrayList<>();
	
	public Correntista (String cpf, String nome, String senha) {
		this.cpf = cpf;
		this.nome = nome; 
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}


	public String getSenha() {
		return senha;
	}

	public ArrayList<Conta> getContas() {
		return contas;
	}
	
	public double getSaldoTotal() {
        double saldoTotal = 0;
        for (Conta conta : contas) {
            saldoTotal += conta.getSaldo();
        }
        return saldoTotal;
    }

	public void adicionar(Conta conta) {
		if (conta != null && !contas.contains(conta)) {
	        contas.add(conta);	
		}
	}

	@Override
	public int compareTo(Correntista outro) {
		return this.cpf.compareTo(outro.getCpf());
	}
	
	@Override
	public String toString() {
	    return "Correntista [CPF=" + cpf + ", Nome=" + nome + "]";
	}

	

}