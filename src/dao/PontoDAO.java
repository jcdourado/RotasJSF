package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Ponto;

public class PontoDAO {

	public void adicionar(Ponto p, int id_rota) throws SQLException, ClassNotFoundException {
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "INSERT INTO PONTO(NUMERO, RUA, CIDADE, ESTADO, CEP, IDROTA) VALUES (?,?,?,?,?,?)";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setString(1, p.getNumero());
		pS.setString(2, p.getRua());
		pS.setString(3, p.getCidade());
		pS.setString(4, p.getEstado());
		pS.setString(5, p.getCep());
		pS.setInt(6, id_rota);
		pS.executeUpdate();
	}
	
	public void remover(Ponto p) throws ClassNotFoundException, SQLException{
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "DELETE FROM PONTO WHERE ID = ?";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setInt(1, p.getId());
		pS.executeUpdate();
	}
	
	public void remover(int id_rota) throws ClassNotFoundException, SQLException{
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "DELETE FROM PONTO WHERE IDROTA = ?";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setInt(1, id_rota);
		pS.executeUpdate();
	}
	
	public void alterar(Ponto p) throws ClassNotFoundException, SQLException{
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "UPDATE PONTO SET NUMERO = ?, RUA = ?, CIDADE = ?, ESTADO = ?, CEP = ? WHERE ID = ?";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setString(1, p.getNumero());
		pS.setString(2, p.getRua());
		pS.setString(3, p.getCidade());
		pS.setString(4, p.getEstado());
		pS.setString(5, p.getCep());
		pS.setInt(6, p.getId());
		pS.executeUpdate();
	}
	
	public List<Ponto> consultar(int id_rota) throws SQLException, ClassNotFoundException{
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "SELECT * FROM PONTO WHERE IDROTA = ?";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setInt(1, id_rota);
		ResultSet rS = pS.executeQuery();
		List<Ponto> pontos = new ArrayList<Ponto>();
		while(rS.next()){
			Ponto p = new Ponto();
			p.setNumero(rS.getString("numero"));
			p.setCep(rS.getString("cep"));
			p.setCidade(rS.getString("cidade"));
			p.setEstado(rS.getString("estado"));
			p.setRua(rS.getString("rua"));
			p.setId(rS.getInt("id"));
			pontos.add(p);
		}
		return pontos;
	}
	
}
