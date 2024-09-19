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

	//
	
	public ArrayList<ContaEspecial> contasEspeciais(){
		return contasEspeciais;
	}

	public Correntista localizarCorrentista(String cpf) {
		for (Correntista c : correntistas) {
			if (c.getCpf().equals(cpf)) {
				return c;
			}
		}
		return null;
	}
	
	public Conta localizarConta (int id) {
		for (Conta c : contas) {
			if(c.getId()==id) {
				return c;
			}
		}
		return null;
	}

	public ArrayList<ContaEspecial> getContasEspeciais() {
		return contasEspeciais;
	}

	public int gerarIdConta() {
		return getContas().size()+1;
	}

	public void salvarObjetos() {
		
	}


}
