package modelo;

import java.util.ArrayList;

public class Correntista {
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

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public ArrayList<Conta> getContas() {
		return contas;
	}

	public void setContas(ArrayList<Conta> contas) {
		this.contas = contas;
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
	

}
