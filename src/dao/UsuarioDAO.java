package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;

public class UsuarioDAO {
	
	public boolean adicionar(Usuario u) throws SQLException, ClassNotFoundException{
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "INSERT INTO USUARIO(USUARIO,NOME,EMAIL,SENHA) VALUES (?,?,?,?)";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setString(1, u.getUsuario());
		pS.setString(2, u.getNome());
		pS.setString(3, u.getEmail());
		pS.setString(4, u.getSenha());
		return pS.execute();
	}
	
	public boolean remover(String usuario) throws ClassNotFoundException, SQLException{
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "DELETE FROM USUARIO WHERE USUARIO = ?";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setString(1, usuario);
		return pS.execute();
	}
	
	public void alterar(Usuario usuario) throws ClassNotFoundException, SQLException{
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "UPDATE USUARIO SET NOME = ?, EMAIL = ?, SENHA = ? WHERE USUARIO = ?";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setString(1, usuario.getNome());
		pS.setString(2, usuario.getEmail());
		pS.setString(3, usuario.getSenha());
		pS.setString(4, usuario.getUsuario());
		pS.executeUpdate();
	}
	
	public Usuario consultar(Usuario usuarioLogar) throws ClassNotFoundException, SQLException{
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "SELECT * FROM USUARIO WHERE USUARIO = ? AND SENHA = ?";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setString(1, usuarioLogar.getUsuario());
		pS.setString(2, usuarioLogar.getSenha());
		ResultSet rS = pS.executeQuery();
		if(rS.next()){
			usuarioLogar.setEmail(rS.getString("email"));
			usuarioLogar.setNome(rS.getString("nome"));
			return usuarioLogar;
		}
		return usuarioLogar;
	}
}
