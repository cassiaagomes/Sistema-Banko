package repositorio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//Imports do pack MODELO
import java.util.ArrayList;
import java.util.Scanner;

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
		
	//organizar por cpf!!!!!!!!!!!
	public void adicionarConta(Conta conta) {
		
		contas.add(conta);
	}
	
	
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

	public ArrayList<ContaEspecial> getContasEspeciais() {
		return contasEspeciais;
	}

	public int gerarIdConta() {
		if(contas.isEmpty())
			return 1;
		else
		return this.getContas().size()+1;
	}
	
	public void carregarObjetos() {
		try {
			File file1 = new File(new File(".\\contas.csv").getCanonicalPath());
			File file2 = new File(new File(".\\correntistas.csv").getCanonicalPath());
			if (!file1.exists() || !file2.exists() ) {
				FileWriter arquivo1 = new FileWriter(file1); arquivo1.close();
				FileWriter arquivo2 = new FileWriter(file2); arquivo2.close();
				return;
			}
		} catch (IOException ex) {
			throw new RuntimeException("criacao dos arquivos vazios:"+ex.getMessage());
		}
		
		String linha;	
		String[] partes;	
		Conta co;
		Correntista c;
		
		try	{
			String tipo, id, data, saldo, limite ;
			File f = new File( new File(".\\contas.csv").getCanonicalPath() )  ;
			Scanner arquivo1 = new Scanner(f);	 
			while(arquivo1.hasNextLine()) 	{
				linha = arquivo1.nextLine().trim();		
				partes = linha.split(";");
				tipo = partes[0];
				if(tipo.equals("CONTA_ESPECIAL")) {
					id = partes[1];
					data = partes[2];
					saldo = partes[3];
					limite = partes[4];
					co = new ContaEspecial(Integer.parseInt(id), data, Double.parseDouble(saldo),Double.parseDouble(limite));
					this.adicionarConta(co);
				}
				else {
					id = partes[1];
					data = partes[2];
					saldo = partes[3];
					co = new Conta(Integer.parseInt(id), data, Double.parseDouble(saldo));
					this.adicionarConta(co);
				}
			} 
			arquivo1.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("Erro na leitura arquivo de contas:"+ex.getMessage());
		}
		//prestar atenção na assinatura dos metodos
		try	{
			String cpf,nome, senha, ids;
			File f = new File( new File(".\\correntistas.csv").getCanonicalPath())  ;
			Scanner arquivo2 = new Scanner(f);	 
			while(arquivo2.hasNextLine()) 	{
				linha = arquivo2.nextLine().trim();	
				partes = linha.split(";");
				cpf = partes[0];
				nome = partes[1];
				senha = partes[2];
				ids="";
				c = new Correntista(cpf,nome,senha);
				this.adicionarCorrentista(c);
				ids = partes[4];

				if(!ids.isEmpty()) {	
					for(String idconta : ids.split(",")){	
						co = this.localizarConta(Integer.parseInt(idconta));
						co.adicionar(c);
						c.adicionar(co);
					}
				}
			}
			arquivo2.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("Erro na leitura arquivo de Correntistas:"+ex.getMessage());
		}
	}

	
	
	
	
	// feito, por hora, irei ordenar dentro da lista os correntistas.
	public void	salvarObjetos()  {
		try	{
			File f = new File( new File(".\\contas.csv").getCanonicalPath())  ;
			FileWriter arquivo1 = new FileWriter(f); 
			for(Conta c : contas) 	{	
				if (c instanceof ContaEspecial e) {
					arquivo1.write("CONTA_ESPECIAL;"+e.getId()+";"+e.getData()+";"+e.getSaldo()+";"+e.getLimite()+"\n");
				}
				else {
					arquivo1.write("CONTA_SIMPLES;"+c.getId()+";"+c.getData()+";"+c.getSaldo()+"\n");
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
