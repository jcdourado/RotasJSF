package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CEP {
	
	public String buscarCEP(String cep){
		try {
			URL conexao = new URL("HTTP","viacep.com.br","/ws/"+cep+"/json/");
			HttpURLConnection urlConnection = (HttpURLConnection) conexao.openConnection();
			
			urlConnection.setConnectTimeout(250000);
			urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			
			InputStream input = urlConnection.getInputStream();
			InputStreamReader reader = new InputStreamReader(input,"UTF-8");
			BufferedReader buffer = new BufferedReader(reader);
			StringBuffer texto = new StringBuffer();
			
			while(buffer.ready()){
				texto.append(buffer.readLine());
			}
			
			buffer.close();
			urlConnection.disconnect();
			return texto.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
		
	}

}
