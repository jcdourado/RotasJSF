package mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Ponto;

@ManagedBean
@SessionScoped
public class PontoMB {
	private Ponto ponto = new Ponto();
	private List<Ponto> pontos = new ArrayList<Ponto>();

	public String adicionar(){
		adicionarPonto();
		ponto = new Ponto();
		return "";
	}
	
	public String gerar(){
		FacesContext ctx = FacesContext.getCurrentInstance();
		Application app = ctx.getApplication();
		RotaMB rota = app.evaluateExpressionGet(ctx, "#{rotaMB}", RotaMB.class);
		rota = new RotaMB();
		try {
			rota.getRota().setId(0);
			pontos = new ArrayList<Ponto>();
			return rota.pesquisar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public Ponto getPonto() {
		return ponto;
	}

	public void setPonto(Ponto ponto) {
		this.ponto = ponto;
	}
	public void adicionarPonto(){
		this.pontos.add(ponto);
	}

	public List<Ponto> getPontos() {
		return pontos;
	}

	public void setPontos(List<Ponto> pontos) {
		this.pontos = pontos;
	}
	
}
