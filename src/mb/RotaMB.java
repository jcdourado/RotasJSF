package mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
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
		rotas = dao.consultar(rota.getNome(), usuario.getUsuario());
		return "rotas?faces-redirect=true";
	}

	public String adicionar() {
		rota = new Rota();
		return "addrota?faces-redirect=true";
	}

	public String voltar() {
		return "rotas?faces-redirect=true";
	}

	public String adicionarNovo() {
		RotaDAO dao = new RotaDAO();
		try {
			dao.adicionar(rota, usuario.getUsuario());
			rotas = dao.consultar("", usuario.getUsuario());
			rota.setId(rotas.get(rotas.size()-1).getId());
			return "";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "N�o foi poss�vel registrar essa rota!",
				"N�o foi poss�vel registrar");
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage("formBody:msg", msg);
		rota.setId(0);
		return "";
	}
	
	public String excluir(Rota rota){
		RotaDAO dao = new RotaDAO();
		try {
			dao.remover(rota);
			return pesquisar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "";
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
