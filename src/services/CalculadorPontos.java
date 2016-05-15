package services;

import java.util.ArrayList;
import java.util.List;

import org.json.JsonReceiver;

import model.Ponto;

public class CalculadorPontos {
	private JsonReceiver calculator = new JsonReceiver();
	private int tempo;
	
	public List<Ponto> calcularDiferencas(List<Ponto> pontos) {
		String[] enderecos = new String[pontos.size()];
		for(int i = 0; i < enderecos.length ; i++){
			Ponto p = pontos.get(i);
			enderecos[i] = p.getCep() + ",  " + p.getRua() + ",  " + p.getNumero() + ",  " + p.getCidade() + ",  " + p.getEstado();
			p.setEnderecoCompleto(enderecos[i]);
		}
		List<Ponto> pontosCalculados = transformarAuxPonto(calcularCaminho(enderecos), pontos);
		System.out.println(pontosCalculados);
		return pontosCalculados;
	}

	public List<Ponto> transformarAuxPonto(Auxiliar[] aux, List<Ponto> pontosRecebidos){
		List<Ponto> pontos = new ArrayList<Ponto>();
		pontos.add(pontosRecebidos.get(0));
		for(Auxiliar a : aux){

			for(Ponto pAuxiliar : pontosRecebidos){
			
				if(pAuxiliar.getEnderecoCompleto() == a.endereco){
					pontos.add(pAuxiliar);
				}
			
			}
			System.out.println(pontos.size());
			
		}
		return pontos;
	}
	
	
	
	public Auxiliar[] calcularCaminho(String[] enderecos){
		Auxiliar[] auxiliar = new Auxiliar[enderecos.length - 1];
		
		for(int i = 0; i < auxiliar.length ; i ++){
			auxiliar[i] = new Auxiliar("",0);
		}

		// ta funfando
		for(int i = 1 ; i < enderecos.length ; i++){
			int tempo = calculator.calcular(enderecos[0], enderecos[i]);			
			if(tempo < auxiliar[0].tempo || auxiliar[0].tempo == 0){
				auxiliar[0].tempo = tempo;
				auxiliar[0].endereco = enderecos[i]; 
				String aux = enderecos[i];
				enderecos[i] = enderecos[1];
				enderecos[1] = aux;
			}
		}
				
		int x = 2;
		
		for(int i = 1; i < auxiliar.length ; i++){
			// colocando o endereco menor na proxima posicao do auxiliar
			for(int j = x ; j < enderecos.length ; j++){
				int tempo = calculator.calcular(auxiliar[i-1].endereco, enderecos[j]);
				if(tempo < auxiliar[i].tempo || auxiliar[i].tempo == 0){
					auxiliar[i].tempo = tempo;
					auxiliar[i].endereco = enderecos[j];
				}
			}
			
			for(int j = 1 ; j < enderecos.length ; j++){
				if(auxiliar[i].endereco.equals(enderecos[j])){
					String aux = enderecos[j];
					for(int k = j ; k > 2 ; k--){
						enderecos[k] = enderecos[k-1];
					}
					enderecos[1] = aux;
				}
			}
			x++;
		}
		
		for(int i = 0; i < auxiliar.length ; i++){
			tempo += auxiliar[i].tempo;
		}
	
		return auxiliar;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
}
