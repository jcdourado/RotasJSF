package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Rota;

public class RotaDAO {

	public void adicionar(Rota rota) throws SQLException, ClassNotFoundException {
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "INSERT INTO ROTA VALUES(?,?)";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setString(1, rota.getNome());
		pS.setInt(2, rota.getTempoTotal());
		pS.executeUpdate();
	}
	
	public void remover(Rota rota) throws ClassNotFoundException, SQLException{
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "DELETE FROM ROTA WHERE ID = ?";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setInt(1, rota.getId());
		pS.executeUpdate();
	}
	
	public void alterar(Rota rota) throws ClassNotFoundException, SQLException{
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "UPDATE ROTA SET NOME = ?, TEMPO = ? WHERE ID = ?";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setString(1, rota.getNome());
		pS.setInt(2, rota.getTempoTotal());
		pS.setInt(3, rota.getId());
		pS.executeUpdate();
	}
	
	public List<Rota> consultar(String nome) throws SQLException, ClassNotFoundException{
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "SELECT * FORM ROTA WHERE NOME  LIKE ?";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setString(1, "%"+nome+"%");
		ResultSet rS = pS.executeQuery();
		List<Rota> rotas = new ArrayList<Rota>();
		if(rS.next()){
			Rota rota = new Rota();
			rota.setId(rS.getInt("id"));
			rota.setNome(rS.getString("nome"));
			rota.setTempoTotal(rS.getInt("tempo"));
			rotas.add(rota);
		}
		return rotas;
	}
	
}
