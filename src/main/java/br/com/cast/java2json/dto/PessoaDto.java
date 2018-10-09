package br.com.cast.java2json.dto;

import br.com.cast.java2json.entidade.Endereco;

public class PessoaDto {

	private String cpf;
	private String nome;
	private String email;
	private EnderecoDto enderecoDto;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EnderecoDto getEnderecoDto() {
		return enderecoDto;
	}

	public void setEnderecoDto(EnderecoDto enderecoDto) {
		this.enderecoDto = enderecoDto;
	}

}
