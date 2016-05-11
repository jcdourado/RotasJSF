package mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Ponto;
import services.CEP;
import services.CalculadorPontos;

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
	
	public String buscarCEP(){
		if(ponto.getCep() != null){
			CEP pegaCep = new CEP();
			String enderecoCompleto = pegaCep.buscarCEP(ponto.getCep());
			String[] arrJSON = enderecoCompleto.split(",");
			int i = 0;
			if(arrJSON.length > 2){
				for(String aux : arrJSON){
					arrJSON[i] = aux.split(":")[1].replace("\"", "");
					i++;
				}
				ponto.setRua(arrJSON[1]);
				ponto.setCidade(arrJSON[4]);
				ponto.setEstado(arrJSON[5]);
			}
		}
		return "";
	}
	
	public String gerar(){
		CalculadorPontos calc = new CalculadorPontos();
		pontos = calc.calcularDiferencas(pontos);
		return "";
	}
	
	public String salvar(){
		pontos.add(ponto.getId() - 1 , ponto);
		pontos.remove(ponto.getId());
		ponto = new Ponto();
		return "";
	}
	
	public String editar(Ponto p){
		ponto.setId(p.getId());
		ponto.setCep(p.getCep());
		ponto.setCidade(p.getCep());
		ponto.setEstado(p.getEstado());
		ponto.setNumero(p.getNumero());
		ponto.setRua(p.getRua());
		return "";
	}
	
	public String excluir(Ponto p){
		pontos.remove(p);
		return "";
	}
	
	public Ponto getPonto() {
		return ponto;
	}

	public void setPonto(Ponto ponto) {
		this.ponto = ponto;
	}
	public void adicionarPonto(){
		ponto.setId(pontos.size() +1 );
		this.pontos.add(ponto);
	}

	public List<Ponto> getPontos() {
		return pontos;
	}

	public void setPontos(List<Ponto> pontos) {
		this.pontos = pontos;
	}
	
}
