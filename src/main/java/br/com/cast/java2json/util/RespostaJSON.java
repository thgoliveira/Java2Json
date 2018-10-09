package br.com.cast.java2json.util;

public class RespostaJSON {

	private boolean sucesso;
	private String mensagem;
	
	public void addInfo(String msg) {
		this.sucesso = true;
		this.mensagem = msg;
	}
	
	public void addErro(String msg) {
		this.sucesso = false;
		this.mensagem = msg;
	}

	public boolean isSucesso() {
		return sucesso;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
}