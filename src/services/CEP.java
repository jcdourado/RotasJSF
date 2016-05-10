package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;

public class CEP {
	
	public String buscarCEP(String cep){
		try {
			URL conexao = new URL("HTTP","viacep.com.br","/ws/"+cep+"/json/");
			InputStream input = conexao.openConnection().getInputStream();
			InputStreamReader reader = new InputStreamReader(input);
			BufferedReader buffer = new BufferedReader(reader);
			StringBuffer texto = new StringBuffer();
			while(buffer.ready()){
				texto.append(URLDecoder.decode(buffer.readLine(),"UTF-8"));
			}
			System.out.println(texto);
			return texto.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
		
	}

}
