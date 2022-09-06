package br.com.crud.aplicacao;

import java.util.Date;

import br.com.crud.dao.UsuarioDAO;
import br.com.crud.model.Usuario;

public class Main {

	public static void main (String[] args) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();
	
		usuario.setNome("Rafael");
		usuario.setIdade(30);
		usuario.setCPF(1000203002);
		usuario.setTelefone(999999999);
		usuario.setDataCadastro(new Date());
	
		usuarioDAO.save(usuario);
	
		Usuario user1 = new Usuario();
	
		user1.setId(2);
		user1.setNome("Lukas");
		user1.setIdade(22);
		user1.setCPF(00000004004);
		user1.setTelefone(999909909);
		user1.setDataCadastro(new Date());
	
		usuarioDAO.update(user1);
	

		
		for(Usuario c : usuarioDAO.getUsuarios()) {
		
			System.out.println("Nome:" + c.getNome());
		}
	}
}
