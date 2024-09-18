package modelo;

public class ContaEspecial extends Conta{
	
	protected double limite;
	
	public ContaEspecial(int id, String data, double saldo, double limite) {
		super(id, data, saldo);
		this.limite = limite;
	}
	
	@Override
	public void debitar (double valor) throws Exception{
		if (this.getSaldo() + this.getLimite() >= valor) {
			this.setSaldo(this.getSaldo() - valor);
		} else {
			throw new Exception ("Transferência excede o limite de saldo especial da conta");
		}
	}

	private double getLimite() {
		return limite;
	}

}
