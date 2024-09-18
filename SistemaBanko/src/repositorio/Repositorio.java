package repositorio;

//Imports do pack MODELO
import java.util.ArrayList;
import modelo.Correntista;
import modelo.Conta;
import modelo.ContaEspecial;


//Class Repositorio -Responsavel por ler e gravar os objetos
	public class Repositorio {
		private ArrayList<Correntista> correntistas = new ArrayList<>();
		private ArrayList<Conta> contas = new ArrayList<> ();
		private ArrayList<ContaEspecial> contasEspeciais = new ArrayList<>();
		
		
	//Metodos para realizar o armazenamento das informações
		
	//Add
	public void adicionarCorrentista(Correntista correntista) {
		correntistas.add(correntista);
	}
		
	public void adicionarConta(Conta x) {
		contas.add(x);
	}
	
	public void adicionarContaEspecial(ContaEspecial contaEspecial) {
		contasEspeciais.add(contaEspecial);
	}
	
	//Getters
	
	public ArrayList<Correntista> getCorrentistas() {
		return correntistas;
	}
	
	public ArrayList<Conta> getContas(){
		return contas;
	}
	
	public ArrayList<ContaEspecial> contasEspeciais(){
		return contasEspeciais;
	}

	

}
