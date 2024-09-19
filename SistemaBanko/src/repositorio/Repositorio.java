package repositorio;

import java.io.File;
import java.io.FileWriter;
//Imports do pack MODELO
import java.util.ArrayList;

import modelo.Conta;
import modelo.ContaEspecial;
import modelo.Correntista;


//Class Repositorio -Responsavel por ler e gravar os objetos
	public class Repositorio {
		private ArrayList<Correntista> correntistas = new ArrayList<>();
		private ArrayList<Conta> contas = new ArrayList<> ();
		
	//Metodos para realizar o armazenamento das informações
		
	//Add
	public void adicionarCorrentista(Correntista correntista) {
		correntistas.add(correntista);
	}
		
	public void adicionarConta(Conta x) {
		contas.add(x);
	}
	
	//Getters
	
	public ArrayList<Correntista> getCorrentistas() {
		return correntistas;
	}
	
	public ArrayList<Conta> getContas(){
		return contas;
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

	public int gerarIdConta() {
		return getContas().size()+1;
	}

	// feito, por hora
	public void	salvarObjetos()  {
		try	{
			File f = new File( new File(".\\contas.csv").getCanonicalPath())  ;
			FileWriter arquivo1 = new FileWriter(f); 
			for(Conta c : contas) 	{	
				if (c instanceof ContaEspecial e) {
					arquivo1.write("CONTA ESPECIAL;"+e.getId()+";"+e.getData()+";"+e.getSaldo()+";"+e.getLimite()+"\n");
				}
				else {
					arquivo1.write("CONTA SIMPLES;"+c.getId()+";"+c.getData()+";"+c.getSaldo()+"\n");
				}	
			} 
			arquivo1.close();
		}
		catch(Exception e){
			throw new RuntimeException("problema na cria��o do arquivo contas "+e.getMessage());
		}
		
		
		try	{
			File f = new File( new File(".\\correntistas.csv").getCanonicalPath())  ;
			FileWriter arquivo2 = new FileWriter(f) ; 
			ArrayList<String> lista ;
			String listaId;
			for(Correntista c : correntistas) {
				lista = new ArrayList<>();
				for(Conta x : c.getContas()) {
					lista.add(x.getId()+"");
				}
				listaId = String.join(",", lista);

				arquivo2.write(c.getCpf() +";"+ c.getNome()+";" 
						+c.getSenha()+";"+ listaId +"\n");	

			} 
			arquivo2.close();
		}
		catch (Exception e) {
			throw new RuntimeException("problema na cria��o do arquivo  correntista "+e.getMessage());
		}
		
	}


}
