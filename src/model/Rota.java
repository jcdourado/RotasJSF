package model;

import java.util.ArrayList;
import java.util.List;

public class Rota {
	private int id;
	private List<Ponto> pontos = new ArrayList<Ponto>();
	private String nome;
	private int tempoTotal;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Ponto> getPontos() {
		return pontos;
	}
	public void setPontos(List<Ponto> pontos) {
		this.pontos = pontos;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getTempoTotal() {
		return tempoTotal;
	}
	public void setTempoTotal(int tempoTotal) {
		this.tempoTotal = tempoTotal;
	}
}
