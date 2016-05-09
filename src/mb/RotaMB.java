package mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.RotaDAO;
import model.Rota;
import model.Usuario;

@ManagedBean
@SessionScoped
public class RotaMB {
	private Rota rota = new Rota();
	private List<Rota> rotas = new ArrayList<Rota>();
	private Usuario usuario;
	
	public RotaMB() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Application app = ctx.getApplication();
		usuario = app.evaluateExpressionGet(ctx, "#{loginMB}", LoginMB.class).getUsuario();
	}

	public String pesquisar() throws ClassNotFoundException, SQLException {
		RotaDAO dao = new RotaDAO();
		rotas = dao.consultar(rota.getNome(),usuario.getUsuario());
		return "rotas";
	}

	public String adicionar() {
		rota = new Rota();
		return "addrota";
	}

	public String voltar() {
		return "rotas";
	}

	public String adicionarNovo() throws ClassNotFoundException, SQLException {
		RotaDAO dao = new RotaDAO();
		dao.adicionar(rota,usuario.getUsuario());
		rota = new Rota();
		rotas = dao.consultar("",usuario.getUsuario());
		return "rotas";
	}

	public List<Rota> getRotas() {
		return rotas;
	}

	public void setRotas(List<Rota> rotas) {
		this.rotas = rotas;
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}