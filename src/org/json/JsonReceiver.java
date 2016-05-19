package org.json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class JsonReceiver {
	public int calcular(String origem, String destino) {
		int tempo = -1;
		try {
			origem ="Brasil" + URLEncoder.encode(origem, "UTF-8");
			destino ="Brasil" + URLEncoder.encode(destino, "UTF-8");
			URL url = new URL(
			        "https://maps.googleapis.com/maps/api/distancematrix/json?origins="
			                + origem + "&destinations=" + destino
			                + "&sensor=false");
			tempo = mostraJson(url.toString());
		} catch (UnsupportedEncodingException | MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return tempo;
     }
	
	public int mostraJson(String url){
		JSONObject json = null;
		try {
			json = new JSONObject(readUrl(url));
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] jsonCompleto = json.toString().split(":");
		String[] jsonHorasMinutos = jsonCompleto[5].split(" ");
		
		int tempoMinutos = 0;
		int tempoHoras = 0;
		
		try{
			if(jsonHorasMinutos.length > 2){
				tempoHoras = Integer.parseInt(jsonHorasMinutos[0].substring(1));
				tempoMinutos = Integer.parseInt(jsonHorasMinutos[2]);
			}
			else{
				tempoMinutos = Integer.parseInt(jsonHorasMinutos[0].substring(1));
			}
		}
		catch(NumberFormatException ex){}
		
		return (tempoHoras*60) + tempoMinutos;
	}
	
	private String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
}
