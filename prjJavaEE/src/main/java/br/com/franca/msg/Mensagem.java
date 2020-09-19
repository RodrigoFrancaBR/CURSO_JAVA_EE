package br.com.franca.msg;

import java.util.ResourceBundle;

public class Mensagem {
	private static ResourceBundle MSG_SISTEMA = ResourceBundle.getBundle("br.com.franca.msg.config_pt_BR");

	public static String getMessage(String chave) {
		
		if (!MSG_SISTEMA.containsKey(chave))
			throw new RuntimeException("Chave inválida");
		
		return MSG_SISTEMA.getString(chave);
	}
}
