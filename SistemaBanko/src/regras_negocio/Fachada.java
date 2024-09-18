package regras_negocio;

import modelo.Correntista;
import repositorio.Repositorio;

public class Fachada {
	private Fachada () {} 
	private static Repositorio repositorio = new Repositorio (); 
	
	public static void criarCorrentista(String cpf, String nome, String senha) throws Exception {
		cpf = cpf.trim();
		nome = nome.trim();
		
		//localizar correntista no repositorio, usando o cpf
		for (Correntista c : repositorio.getCorrentistas()) {
			if(c.getCpf().equals(cpf)) {
				throw new Exception ("Já existe um correntista com o cpf: " + cpf);
			}
		}
		//Criar um novo correntista
		Correntista novoCorrentista = new Correntista(cpf, nome, senha);
		
		//Adicionar o novo correntista no repositório
		repositorio.adicionarCorrentista(novoCorrentista);
		
	}
	public static Repositorio getRepositorio() {
        return repositorio;
    }

}


