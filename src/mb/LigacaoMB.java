package mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LigacaoMB {
	private String partida = "";
	private String chegada = "";
	
	
	public String getPartida() {
		return partida;
	}
	public void setPartida(String partida) {
		this.partida = partida;
	}
	public String getChegada() {
		return chegada;
	}
	public void setChegada(String chegada) {
		this.chegada = chegada;
	}
}
