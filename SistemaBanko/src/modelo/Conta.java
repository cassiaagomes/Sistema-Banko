package modelo;

import java.util.ArrayList;

public class Conta {
	protected int id;
	protected String data;
	protected double saldo;
	private ArrayList<Correntista> correntista = new ArrayList<>();
	
	public Conta (int id, String data, double saldo) {
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

	public void setData(String data) {
		this.data = data;
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

	public void setCorrentista(ArrayList<Correntista> correntista) {
		this.correntista = correntista;
	}
	
	public void creditar(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    public void debitar(double valor) throws Exception {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
        } else {
            System.out.println("Pobre.");
        }
    }

    public void transferir(double valor, Conta destino) throws Exception {
        if (valor > 0 && saldo >= valor) {
            this.debitar(valor);
            destino.creditar(valor);
        } else {
            System.out.println("Saldo Baixo.");
        }
    }
	

}
