package br.com.crud.dao;

import	java.sql.Connection;
import	java.sql.Date;
import	java.sql.PreparedStatement;
import	java.sql.ResultSet;
import	java.util.ArrayList;
import	java.util.List;

import	br.com.crud.factory.ConnectionFactory;
import	br.com.crud.model.Usuario;

public class UsuarioDAO {
	
	public void save(Usuario usuario) {

	String sql = "INSERT INTO usuario(nome,idade,cpf,telefone,dataCadastro)" + "VALUES(?,?,?,?,?)";
	
	Connection conn = null;
	PreparedStatement pstm = null;
	
		try {
		
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
		
			pstm.setString(1, usuario.getNome());
			pstm.setInt(2, usuario.getIdade());
			pstm.setInt(3, usuario.getCPF());
			pstm.setInt(4, usuario.getTelefone());
			pstm.setDate(5, new Date(usuario.getDataCadastro().getTime()));
		
			pstm.execute();
		} catch (Exception e) {
		
			e.printStackTrace();
		
		} finally {
		
			try {
				if(pstm != null) {
					pstm.close();
				}
			
				if (conn != null) {
					conn.close();
				}
			
			} catch (Exception e) {
			
				e.printStackTrace();
			}
		}
	
	}
	
	public void removeById(int id) {
		
		String sql = "DELETE FROM usuario WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			pstm.execute();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (pstm != null) {
					
					pstm.close();
					
				}
				
				if (conn != null) {
					
					conn.close();
					
				}
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}
		}
	}
	
	public void update(Usuario usuario) {
		
		String sql = "UPDATE usuario SET nome = ?, idade = ?, cpf = ?, telefone = ?, dataCadastro = ?" + "WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, usuario.getNome());
			pstm.setInt(2, usuario.getIdade());
			pstm.setInt(3, usuario.getCPF());
			pstm.setInt(4, usuario.getTelefone());
			pstm.setDate(5, new Date(usuario.getDataCadastro().getTime()));
			
			pstm.setInt(6, usuario.getId());
			
			pstm.execute();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (pstm != null) {
					
					pstm.close();
					
				}
				
				if (conn != null ) {
					
					conn.close();
					
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public List<Usuario> getUsuarios() {
		
		String sql = "SELECT * FROM usuario";
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Usuario usuario = new Usuario();
				
				usuario.setId(rset.getInt("id"));
				usuario.setNome(rset.getString("nome"));
				usuario.setIdade(rset.getInt("idade"));
				usuario.setCPF(rset.getInt("cpf"));
				usuario.setTelefone(rset.getInt("telefone"));
				usuario.setDataCadastro(rset.getDate("dataCadastro"));
				
				usuarios.add(usuario);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (rset != null) {
					
					rset.close();
					
				}
				
				if (pstm != null) {
					
					pstm.close();
					
				}
				
				if (conn != null) {
					
					conn.close();
					
				}
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}
		}
		
		return usuarios;
	}
}
